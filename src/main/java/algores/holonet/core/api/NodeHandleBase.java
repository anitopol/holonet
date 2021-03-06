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

package algores.holonet.core.api;

/**
 * @author Anton Kraievoy
 */
public class NodeHandleBase implements NodeHandle {
  protected final Key nodeId;
  protected final Address address;

  public NodeHandleBase(Key nodeId, Address address) {
    this.nodeId = nodeId;
    this.address = address;
  }

  public Key getNodeId() {
    return nodeId;
  }

  public Address getAddress() {
    return address;
  }

  public Key getKey() {
    return nodeId;
  }

  public int hashCode() {
    return address.hashCode();
  }

  public boolean equals(Object obj) {
    if (obj instanceof algores.holonet.capi.NodeHandle) {
      final algores.holonet.capi.NodeHandle thatHandle = (algores.holonet.capi.NodeHandle) obj;
      return address.equals(thatHandle.getAddress());
    }
    return false;
  }

  public String toString() {
    return nodeId.toString() + "@" + address.toString();
  }
}
