package org.antlr.intellij.plugin.profiler;

import com.intellij.openapi.ui.Splitter;
import com.intellij.ui.table.JBTable;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.atn.ParseInfo;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.LinkedHashMap;

public class ProfilerPanel {
	protected JPanel outerPanel;
	protected JTextArea inputDisplayPane;
	protected JBTable profilerDataTable;
	protected JPanel statsPanel;
	protected JLabel parseTimeField;
	protected JLabel predictionTimeField;
	protected JLabel lookaheadBurdenField;
	protected JLabel cacheMissRateField;
	protected JLabel inputSizeField;
	protected Splitter splitter;

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
		createUIComponents();
		outerPanel = new JPanel();
		outerPanel.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
		splitter = new Splitter();
		splitter.setLayout(new GridBagLayout());
		outerPanel.add(splitter, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
		final JScrollPane scrollPane1 = new JScrollPane();
		GridBagConstraints gbc;
		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		splitter.add(scrollPane1, gbc);
		profilerDataTable.setPreferredScrollableViewportSize(new Dimension(800, 400));
		scrollPane1.setViewportView(profilerDataTable);
		statsPanel = new JPanel();
		statsPanel.setLayout(new GridLayoutManager(6, 3, new Insets(0, 5, 0, 0), -1, -1));
		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		splitter.add(statsPanel, gbc);
		final JLabel label1 = new JLabel();
		label1.setText("Parse time (ms):");
		statsPanel.add(label1, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(130, 16), null, 0, false));
		final JLabel label2 = new JLabel();
		label2.setText("Prediction time (ms):");
		statsPanel.add(label2, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(130, 16), null, 0, false));
		final JLabel label3 = new JLabel();
		label3.setText("Lookahead burden:");
		statsPanel.add(label3, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(130, 16), null, 0, false));
		final JLabel label4 = new JLabel();
		label4.setText("DFA cache miss rate:");
		statsPanel.add(label4, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(130, 16), null, 0, false));
		final Spacer spacer1 = new Spacer();
		statsPanel.add(spacer1, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(-1, 14), null, 0, false));
		final Spacer spacer2 = new Spacer();
		statsPanel.add(spacer2, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
		parseTimeField = new JLabel();
		parseTimeField.setText("0");
		statsPanel.add(parseTimeField, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		predictionTimeField = new JLabel();
		predictionTimeField.setText("0");
		statsPanel.add(predictionTimeField, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		lookaheadBurdenField = new JLabel();
		lookaheadBurdenField.setText("0");
		statsPanel.add(lookaheadBurdenField, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		cacheMissRateField = new JLabel();
		cacheMissRateField.setText("0");
		statsPanel.add(cacheMissRateField, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		final JLabel label5 = new JLabel();
		label5.setText("Input size:");
		statsPanel.add(label5, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(130, 16), null, 0, false));
		inputSizeField = new JLabel();
		inputSizeField.setText("0");
		statsPanel.add(inputSizeField, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		splitter.setSecondComponent(statsPanel);
		splitter.setFirstComponent(scrollPane1);
	}

	/**
	 * @noinspection ALL
	 */
	public JComponent $$$getRootComponent$$$() {
		return outerPanel;
	}

	public static class ProfilerTableDataModel extends AbstractTableModel {
		public ParseInfo parseInfo;
		public LinkedHashMap<String, Integer> nameToColumnMap = new LinkedHashMap<String, Integer>();
		public static final String[] columnNames = {
			"Decision", "Invocations", "Time (ms)", "# DFA states", "Full context", "Total k", "Min k", "Max k",
			"DFA k", "SLL-ATN k", "LL-ATN k", "Predicates"
		};

		public static final String[] columnToolTips = {
			"Decision", "Invocations", "Time (ms)", "# DFA states", "Full context", "Total k", "Min k", "Max k",
			"DFA k", "SLL-ATN k", "LL-ATN k", "Predicates"
		};

		public ProfilerTableDataModel(ParseInfo parseInfo) {
			this.parseInfo = parseInfo;
			for (int i = 0; i < columnNames.length; i++) {
				nameToColumnMap.put(columnNames[i], i);
			}
		}


		public String getColumnName(int column) {
			return columnNames[column];
		}

		@Override
		public Class<?> getColumnClass(int columnIndex) {
			return Integer.class;
		}

		public int getRowCount() {
			return parseInfo.getDecisionInfo().length;
		}

		public int getColumnCount() {
			return columnNames.length;
		}

		public Object getValueAt(int row, int col) {
			int decision = row;
			switch (col) { // laborious but more efficient than reflection
				case 0:
					return parseInfo.getDecisionInfo()[decision].decision;
				case 1:
					return parseInfo.getDecisionInfo()[decision].invocations;
				case 2:
					return (int) (parseInfo.getDecisionInfo()[decision].timeInPrediction / 1000.0 / 1000.0);
				case 3:
					return parseInfo.getDFASize(decision);
				case 4:
					return parseInfo.getDecisionInfo()[decision].LL_Fallback;
				case 5:
					return parseInfo.getDecisionInfo()[decision].totalLook;
				case 6:
					return parseInfo.getDecisionInfo()[decision].minLook;
				case 7:
					return parseInfo.getDecisionInfo()[decision].maxLook;
				case 8:
					return parseInfo.getDecisionInfo()[decision].DFATransitions;
				case 9:
					return parseInfo.getDecisionInfo()[decision].SLL_ATNTransitions;
				case 10:
					return parseInfo.getDecisionInfo()[decision].LL_ATNTransitions;
				case 11:
					return parseInfo.getDecisionInfo()[decision].predicateEvals.size();
			}
			return "n/a";
		}
	}

	public JPanel getComponent() {
		return outerPanel;
	}

	public JTextArea getInputDisplayPane() {
		return inputDisplayPane;
	}

	public JBTable getProfilerDataTable() {
		return profilerDataTable;
	}

	public void setProfilerData(Parser parser,
								long parseTime_ns) {
		ParseInfo parseInfo = parser.getParseInfo();
		ProfilerTableDataModel model = new ProfilerTableDataModel(parseInfo);
		profilerDataTable.setModel(model);
		profilerDataTable.setRowSorter(new TableRowSorter<ProfilerTableDataModel>(model));
		long parseTimeMS = (long) (parseTime_ns / (1000.0 * 1000.0));
		parseTimeField.setText(String.valueOf(parseTimeMS));
		int predTimeMS = (int) (parseInfo.getTotalTimeInPrediction() / (1000.0 * 1000.0));
		predictionTimeField.setText(
			String.format("%d = %3.2f%%", predTimeMS, 100 * ((double) predTimeMS) / parseTimeMS)
		);
		TokenStream tokens = parser.getInputStream();
		int numTokens = tokens.size();
		Token lastToken = tokens.get(numTokens - 1);
		int numChar = lastToken.getStopIndex();
		int numLines = lastToken.getLine();
		if (lastToken.getType() == Token.EOF) {
			Token secondToLastToken = tokens.get(numTokens - 2);
			numLines = secondToLastToken.getLine();
		}
		inputSizeField.setText(String.format("%d char, %d tokens, %d lines",
											 numChar,
											 numTokens,
											 numLines));
		double look = parseInfo.getTotalLookaheadOps();
		lookaheadBurdenField.setText(
			String.format("%d/%d = %3.2f", (long) look, numTokens, look / numTokens)
		);
		double atnLook = parseInfo.getTotalATNLookaheadOps();
		cacheMissRateField.setText(
			String.format("%d/%d = %3.2f%%", (long) atnLook, (long) look, atnLook * 100.0 / look)
		);
	}

	private void createUIComponents() {
		profilerDataTable = new JBTable() {
			@Override
			protected JTableHeader createDefaultTableHeader() {
				return new JTableHeader(columnModel) {
					public String getToolTipText(MouseEvent e) {
						Point p = e.getPoint();
						int index = columnModel.getColumnIndexAtX(p.x);
						int realIndex =
							columnModel.getColumn(index).getModelIndex();
						return ProfilerTableDataModel.columnToolTips[realIndex];
					}
				};
			}
		};
		JTableHeader header = profilerDataTable.getTableHeader();
		header.setDefaultRenderer(new HeaderRenderer(profilerDataTable));
	}

	static class HeaderRenderer implements TableCellRenderer {

		DefaultTableCellRenderer renderer;

		public HeaderRenderer(JTable table) {
			renderer = (DefaultTableCellRenderer)
				table.getTableHeader().getDefaultRenderer();
			renderer.setHorizontalAlignment(JLabel.RIGHT);
		}

		@Override
		public Component getTableCellRendererComponent(
			JTable table, Object value, boolean isSelected,
			boolean hasFocus, int row, int col) {
			return renderer.getTableCellRendererComponent(
				table, value, isSelected, hasFocus, row, col);
		}
	}
}
