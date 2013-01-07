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

package algores.holonet.core.events;

import algores.holonet.core.CommunicationException;
import algores.holonet.core.Network;
import org.akraievoy.base.ref.Ref;
import org.akraievoy.base.runner.api.RefLong;
import org.akraievoy.cnet.gen.vo.EntropySource;

/**
 * Node failure event.
 */
public class EventNodeFail extends Event<EventNodeFail> {
  protected Ref<Long> count = new RefLong(1);

  public void setCountRef(Ref<Long> nodeCount) {
    this.count = nodeCount;
  }

  public EventNodeFail withCountRef(Ref<Long> nodeCountRef) {
    setCountRef(nodeCountRef);
    return this;
  }

  public void setCount(int nodeCount) {
    this.count.setValue((long) nodeCount);
  }

  public Result executeInternal(final Network targetNetwork, final EntropySource eSource) {
    final int countInt = count.getValue().intValue();

    if (targetNetwork.getAllNodes().size() > countInt) {
      try {
        targetNetwork.removeNodes(countInt, true, eSource);
      } catch (CommunicationException e) {
        return handleEventFailure(e, null);
      }
      return Result.SUCCESS;
    }

    return handleEventFailure(null, "Unsufficient number of nodes");
  }
}
