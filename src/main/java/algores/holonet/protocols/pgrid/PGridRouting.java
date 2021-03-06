/*
 Copyright 2011 Anton Kraievoy akraievoy@gmail.com
 This file is part of Holonet.

 Holonet is free software: you can redistribute it and/or modify
 it under the terms of the GNU General Public License as published by
 the Free Software Foundation, either version 3 of the License, or
 (at your option) any later version.

 Holonet is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 GNU General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with Holonet. If not, see <http://www.gnu.org/licenses/>.
 */

package algores.holonet.protocols.pgrid;

import algores.holonet.core.Node;
import algores.holonet.core.api.Range;
import algores.holonet.core.api.RangeBase;
import algores.holonet.core.api.tier0.routing.RoutingDistance;
import algores.holonet.core.api.tier0.routing.RoutingServiceBase;

import static algores.holonet.core.api.tier0.routing.Routing.*;

class PGridRouting extends RoutingServiceBase {
  public static final Flavor FLAVOR_REPLICA_SPEC = new Flavor("replica-specialized");
  public static final Flavor FLAVOR_REPLICA_GEN = new Flavor("replica-generalized");

  protected static final int MAX_SAME_PATHS = 5;
  protected static final int MAX_SAME_COMPLEMENT = 7;

  protected final TrivialDistance routingPreference = new TrivialDistance();

  public PGridRouting() {
    super();
  }

  public PGridRouting copy() {
    return new PGridRouting();
  }

  public void init(Node ownerNode) {
    super.init(ownerNode);

    routes.add(FLAVOR_OWNER, RoutingEntry.own(
        ownerNode.getKey(),
        ownerNode.getAddress(),
        ownerNode.getServices().getStorage().getEntryCount(), new RangeBase(ownerNode.getKey(), 0)
    ));

    setRedundancy(MAX_SAME_PATHS);
  }

  protected Flavor flavorize(RoutingEntry entry) {
    if (entry.getAddress().equals(getOwner().getAddress())) {
      return FLAVOR_OWNER;
    }

    final Range ownerRange = ownRoute().getRange();
    final Range otherRange = entry.getRange();

    if (ownerRange.isPrefixFor(otherRange, true)) {
      return FLAVOR_REPLICA_SPEC;
    }
    if (otherRange.isPrefixFor(ownerRange, false)) {
      return FLAVOR_REPLICA_GEN;
    }

    final int commonPrefixLen = ownerRange.getCommonPrefixLen(otherRange);
    return new Flavor("complement:" + commonPrefixLen);
  }

  public RoutingDistance getRoutingDistance() {
    return routingPreference;
  }

  public Range getPath() {
    return ownRoute().getRange();
  }

  public void setPath(Range newPath) {
    final RoutingEntry ownRoute = ownRoute();
    updateOwnRoute(RoutingEntry.own(newPath.getKey(), ownRoute.getAddress(), ownRoute.entryCount(), newPath));
  }

  public String toString() {
    return getPath().toString();
  }

  @Override
  protected void updateOnRecover(final Flavor refFlavor, final RoutingEntry recoveredRoute) {
    //  do nothing for now: the algo is broken and not covered with tests anyway
  }
}
