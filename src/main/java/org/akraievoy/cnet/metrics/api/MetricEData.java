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

package org.akraievoy.cnet.metrics.api;

import org.akraievoy.base.ref.Ref;
import org.akraievoy.holonet.exp.store.RefObject;
import org.akraievoy.cnet.net.vo.EdgeData;

public abstract class MetricEData extends Metric<EdgeData> {
  protected Ref<EdgeData> target = new RefObject<EdgeData>();

  public void setTarget(Ref<EdgeData> target) {
    this.target = target;
  }

  public Ref<EdgeData> getTarget() {
    return target;
  }
}