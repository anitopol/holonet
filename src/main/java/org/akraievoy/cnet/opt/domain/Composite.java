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

package org.akraievoy.cnet.opt.domain;

import com.google.common.base.Optional;
import org.akraievoy.base.Format;
import org.akraievoy.base.runner.api.Context;
import org.akraievoy.cnet.gen.vo.EntropySource;
import org.akraievoy.cnet.gen.vo.WeightedEventModel;
import org.akraievoy.cnet.gen.vo.WeightedEventModelBase;

import java.util.ArrayList;
import java.util.List;

public abstract class Composite<Component> {
  protected final List<Component> elems = new ArrayList<Component>();
  protected final WeightedEventModel elemsModel =
      new WeightedEventModelBase(Optional.of("composite"));
  protected int[] elemFails;
  protected int[] elemUses;
  protected double[] elemRanks;

  public void setElems(List<Component> elems) {
    this.elems.clear();
    this.elems.addAll(elems);

    this.elemFails = new int[elems.size()];
    this.elemUses = new int[elems.size()];
    this.elemRanks = new double[elems.size()];
  }

  public void calibrate(Context ctx, String generationParamName) {
    elemsModel.clear();

    for (int i = 0; i < elems.size(); i++) {
      final Double ratio = ctx.get(getKeyRatio(i), Double.class, Context.offset(generationParamName, -1));
      elemsModel.add(i, ratio == null ? 1.0 : ratio);
    }
  }

  public void storeRatios(Context ctx) {
    for (int i = 0; i < elems.size(); i++) {
      final int fails = elemFails[i];
      final int uses = elemUses[i];
      final double rank = elemRanks[i];

      final double ratio = ratio(fails, uses, rank);

      ctx.put(getKeyRatio(i), ratio);
      ctx.put(getKeySuccess(i), ratio);
      ctx.put(getKeyUses(i), uses);
    }
  }

  protected String getKeyRatio(int i) {
    return getTitle() + "." + i;
  }

  protected String getKeySuccess(int i) {
    return getTitle() + ".success." + i;
  }

  protected String getKeyUses(int i) {
    return getTitle() + ".uses." + i;
  }

  protected double ratio(final int fails, final int uses, final double rank) {
    final double successFreq = (0.05 + uses - fails) / (1.0 + uses);

    if (uses > fails && rank > 0) {
      final double averageRank = rank / (uses - fails);
      return successFreq * averageRank;
    }

    return successFreq;
  }

  public void onFailure(Component failed) {
    if (failed instanceof Indexed) {
      elemFails[((Indexed) failed).getIndex()]++;
    }
  }

  public void rankFeedback(Component ranked, double rank) {
    if (ranked instanceof Indexed) {
      elemRanks[((Indexed) ranked).getIndex()] += rank;
    }
  }

  public Component select(EntropySource eSource) {
    final int index = elemsModel.generate(eSource, false, null);

    return wrap(index);
  }

  protected Component wrap(int index) {
    final Component wrapped = elems.get(index);

    return createReportingWrapper(index, wrapped);
  }

  protected String buildReport() {
    final StringBuilder missReport = new StringBuilder();

    missReport.append(getTitle()).append(": {");
    for (int i = 0, conditionsSize = elems.size(); i < conditionsSize; i++) {
      missReport.append("\n\t");
      missReport.append(elems.get(i).toString());
      missReport.append(" : ");
      missReport.append(elemUses[i] - elemFails[i]);
      missReport.append("/");
      missReport.append(elemUses[i]);
      if (elemRanks[i] > 0) {
        missReport.append("@");
        missReport.append(Format.format6(elemRanks[i]));
      }
      missReport.append(" = ");
      missReport.append(Format.format6(ratio(elemFails[i], elemUses[i], elemRanks[i])));

    }
    missReport.append("\n};");

    final String missReportStr = missReport.toString();

    return missReportStr;
  }

  protected abstract Component createReportingWrapper(int breederI, Component wrapped);

  public abstract String getTitle();

  public int size() {
    return elems.size();
  }
}