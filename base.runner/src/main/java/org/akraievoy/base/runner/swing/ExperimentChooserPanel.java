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

package org.akraievoy.base.runner.swing;

import javax.swing.*;
import java.awt.*;

public class ExperimentChooserPanel {
  protected JButton launchButton;
  protected JTable experimentTable;
  protected JPanel rootPanel;
  protected JTextPane outputPane;
  protected JComboBox confCombo;
  protected JTable runsTable;
  protected JTextField chainTextField;
  protected JButton chainButton;
  protected JLabel expNameLabel;
  protected JTable axisTable;
  protected JTable keyTable;
  protected JTable valueTable;
  protected JComboBox exportDestCombo;
  protected JComboBox exportFormatCombo;
  protected JScrollPane axisScrollPane;
  protected JScrollPane keyScrollPane;
  protected JScrollPane valueScrollPane;

  public JPanel getRootPanel() {
    return rootPanel;
  }

  public JTable getExperimentTable() {
    return experimentTable;
  }

  public JButton getLaunchButton() {
    return launchButton;
  }

  public JTextPane getOutputPane() {
    return outputPane;
  }

  public JComboBox getConfCombo() {
    return confCombo;
  }

  public JButton getChainButton() {
    return chainButton;
  }

  public JTextField getChainTextField() {
    return chainTextField;
  }

  public JTable getRunsTable() {
    return runsTable;
  }

  public JLabel getExpNameLabel() {
    return expNameLabel;
  }

  public JTable getKeyTable() {
    return keyTable;
  }

  public JTable getValueTable() {
    return valueTable;
  }

  public JTable getAxisTable() {
    return axisTable;
  }

  public JComboBox getExportDestCombo() {
    return exportDestCombo;
  }

  public JComboBox getExportFormatCombo() {
    return exportFormatCombo;
  }

  {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
    $$$setupUI$$$();
  }

  /**
   * Method generated by IntelliJ IDEA GUI Designer
   * >>> IMPORTANT!! <<<
   * DO NOT edit this method OR call it in your code!
   *
   * @noinspection ALL
   */
  private void $$$setupUI$$$() {
    rootPanel = new JPanel();
    rootPanel.setLayout(new BorderLayout(0, 0));
    final JSplitPane splitPane1 = new JSplitPane();
    splitPane1.setContinuousLayout(false);
    splitPane1.setDividerLocation(275);
    splitPane1.setDividerSize(5);
    splitPane1.setOneTouchExpandable(true);
    splitPane1.setOrientation(0);
    splitPane1.setResizeWeight(0.5);
    rootPanel.add(splitPane1, BorderLayout.CENTER);
    final JScrollPane scrollPane1 = new JScrollPane();
    scrollPane1.setBackground(new Color(-3355444));
    scrollPane1.setVerticalScrollBarPolicy(22);
    splitPane1.setRightComponent(scrollPane1);
    outputPane = new JTextPane();
    outputPane.setBackground(new Color(-16777216));
    outputPane.setFont(new Font("Lucida Sans Typewriter", outputPane.getFont().getStyle(), 12));
    outputPane.setMinimumSize(new Dimension(320, 21));
    outputPane.setPreferredSize(new Dimension(320, 21));
    outputPane.setText("");
    outputPane.setToolTipText("Experiment output");
    scrollPane1.setViewportView(outputPane);
    final JSplitPane splitPane2 = new JSplitPane();
    splitPane2.setContinuousLayout(false);
    splitPane2.setDividerLocation(193);
    splitPane2.setDividerSize(5);
    splitPane2.setOneTouchExpandable(true);
    splitPane2.setResizeWeight(0.3);
    splitPane1.setLeftComponent(splitPane2);
    final JSplitPane splitPane3 = new JSplitPane();
    splitPane3.setDividerLocation(319);
    splitPane3.setDividerSize(5);
    splitPane3.setOneTouchExpandable(true);
    splitPane3.setPreferredSize(new Dimension(400, 449));
    splitPane3.setResizeWeight(0.9);
    splitPane2.setRightComponent(splitPane3);
    final JPanel panel1 = new JPanel();
    panel1.setLayout(new GridBagLayout());
    splitPane3.setRightComponent(panel1);
    axisScrollPane = new JScrollPane();
    GridBagConstraints gbc;
    gbc = new GridBagConstraints();
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.weightx = 0.2;
    gbc.weighty = 1.0;
    gbc.fill = GridBagConstraints.BOTH;
    gbc.insets = new Insets(2, 2, 1, 1);
    panel1.add(axisScrollPane, gbc);
    axisTable = new JTable();
    axisScrollPane.setViewportView(axisTable);
    keyScrollPane = new JScrollPane();
    gbc = new GridBagConstraints();
    gbc.gridx = 1;
    gbc.gridy = 0;
    gbc.weightx = 0.2;
    gbc.weighty = 1.0;
    gbc.fill = GridBagConstraints.BOTH;
    gbc.insets = new Insets(2, 1, 1, 1);
    panel1.add(keyScrollPane, gbc);
    keyTable = new JTable();
    keyScrollPane.setViewportView(keyTable);
    valueScrollPane = new JScrollPane();
    gbc = new GridBagConstraints();
    gbc.gridx = 2;
    gbc.gridy = 0;
    gbc.weightx = 0.4;
    gbc.weighty = 1.0;
    gbc.fill = GridBagConstraints.BOTH;
    gbc.insets = new Insets(2, 1, 1, 2);
    panel1.add(valueScrollPane, gbc);
    valueTable = new JTable();
    valueScrollPane.setViewportView(valueTable);
    final JPanel panel2 = new JPanel();
    panel2.setLayout(new GridBagLayout());
    gbc = new GridBagConstraints();
    gbc.gridx = 2;
    gbc.gridy = 1;
    gbc.weightx = 0.4;
    gbc.fill = GridBagConstraints.BOTH;
    panel1.add(panel2, gbc);
    exportDestCombo = new JComboBox();
    gbc = new GridBagConstraints();
    gbc.gridx = 1;
    gbc.gridy = 0;
    gbc.weightx = 1.0;
    gbc.anchor = GridBagConstraints.SOUTH;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.insets = new Insets(2, 2, 2, 2);
    panel2.add(exportDestCombo, gbc);
    exportFormatCombo = new JComboBox();
    gbc = new GridBagConstraints();
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.weightx = 0.5;
    gbc.anchor = GridBagConstraints.SOUTH;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.insets = new Insets(2, 2, 2, 2);
    panel2.add(exportFormatCombo, gbc);
    final JPanel panel3 = new JPanel();
    panel3.setLayout(new GridBagLayout());
    splitPane3.setLeftComponent(panel3);
    final JScrollPane scrollPane2 = new JScrollPane();
    gbc = new GridBagConstraints();
    gbc.gridx = 0;
    gbc.gridy = 1;
    gbc.weightx = 1.0;
    gbc.weighty = 1.0;
    gbc.fill = GridBagConstraints.BOTH;
    panel3.add(scrollPane2, gbc);
    runsTable = new JTable();
    runsTable.setPreferredScrollableViewportSize(new Dimension(240, 180));
    scrollPane2.setViewportView(runsTable);
    final JPanel panel4 = new JPanel();
    panel4.setLayout(new GridBagLayout());
    gbc = new GridBagConstraints();
    gbc.gridx = 0;
    gbc.gridy = 2;
    gbc.fill = GridBagConstraints.BOTH;
    panel3.add(panel4, gbc);
    final JLabel label1 = new JLabel();
    label1.setText("Cache Chaning:");
    gbc = new GridBagConstraints();
    gbc.gridx = 0;
    gbc.gridy = 1;
    gbc.anchor = GridBagConstraints.EAST;
    gbc.insets = new Insets(2, 2, 2, 2);
    panel4.add(label1, gbc);
    chainTextField = new JTextField();
    chainTextField.setColumns(12);
    chainTextField.setMinimumSize(new Dimension(96, 21));
    gbc = new GridBagConstraints();
    gbc.gridx = 1;
    gbc.gridy = 1;
    gbc.weightx = 1.0;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.insets = new Insets(2, 2, 2, 2);
    panel4.add(chainTextField, gbc);
    chainButton = new JButton();
    chainButton.setText("Chain");
    chainButton.setToolTipText("Chain on selected run data");
    gbc = new GridBagConstraints();
    gbc.gridx = 2;
    gbc.gridy = 1;
    gbc.gridwidth = 3;
    gbc.weightx = 0.5;
    gbc.anchor = GridBagConstraints.WEST;
    gbc.insets = new Insets(2, 2, 2, 2);
    panel4.add(chainButton, gbc);
    final JLabel label2 = new JLabel();
    label2.setText("Config:");
    gbc = new GridBagConstraints();
    gbc.gridx = 0;
    gbc.gridy = 2;
    gbc.anchor = GridBagConstraints.EAST;
    gbc.insets = new Insets(2, 4, 2, 2);
    panel4.add(label2, gbc);
    confCombo = new JComboBox();
    gbc = new GridBagConstraints();
    gbc.gridx = 1;
    gbc.gridy = 2;
    gbc.gridwidth = 4;
    gbc.weightx = 2.0;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.insets = new Insets(2, 2, 2, 2);
    panel4.add(confCombo, gbc);
    launchButton = new JButton();
    launchButton.setActionCommand("launch");
    launchButton.setEnabled(false);
    launchButton.setFont(new Font(launchButton.getFont().getName(), Font.BOLD, launchButton.getFont().getSize()));
    launchButton.setLabel("Launch");
    launchButton.setText("Launch");
    launchButton.setToolTipText("Launch the selected configuration");
    gbc = new GridBagConstraints();
    gbc.gridx = 1;
    gbc.gridy = 3;
    gbc.gridwidth = 4;
    gbc.weightx = 0.5;
    gbc.anchor = GridBagConstraints.SOUTHEAST;
    gbc.insets = new Insets(2, 2, 2, 2);
    panel4.add(launchButton, gbc);
    final JLabel label3 = new JLabel();
    label3.setText("Experiment:");
    gbc = new GridBagConstraints();
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.anchor = GridBagConstraints.EAST;
    gbc.insets = new Insets(4, 4, 2, 2);
    panel4.add(label3, gbc);
    expNameLabel = new JLabel();
    expNameLabel.setHorizontalAlignment(2);
    expNameLabel.setHorizontalTextPosition(2);
    expNameLabel.setRequestFocusEnabled(true);
    expNameLabel.setText(" ");
    gbc = new GridBagConstraints();
    gbc.gridx = 1;
    gbc.gridy = 0;
    gbc.gridwidth = 4;
    gbc.weightx = 2.0;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.insets = new Insets(4, 2, 2, 2);
    panel4.add(expNameLabel, gbc);
    final JPanel panel5 = new JPanel();
    panel5.setLayout(new GridBagLayout());
    splitPane2.setLeftComponent(panel5);
    final JScrollPane scrollPane3 = new JScrollPane();
    gbc = new GridBagConstraints();
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.weightx = 1.0;
    gbc.weighty = 1.0;
    gbc.fill = GridBagConstraints.BOTH;
    panel5.add(scrollPane3, gbc);
    experimentTable = new JTable();
    experimentTable.setPreferredScrollableViewportSize(new Dimension(240, 180));
    scrollPane3.setViewportView(experimentTable);
  }

  /**
   * @noinspection ALL
   */
  public JComponent $$$getRootComponent$$$() { return rootPanel; }
}
