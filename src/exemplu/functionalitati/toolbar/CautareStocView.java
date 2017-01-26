package exemplu.functionalitati.toolbar;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import exemplu.common.models.GenericTableModel;

@SuppressWarnings("serial")
public class CautareStocView extends JPanel {

	private JTable table;
	private static final Insets INSETS_DEFAULT = new Insets(4, 4, 4, 4);

	public CautareStocView() {
		setLayout(new GridBagLayout());
		table = new JTable();
		add(new JScrollPane(table), getFillerConstraints(3, 0));
	}

	private GridBagConstraints getFillerConstraints(final int row, final int column) {
		final GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridx = column;
		constraints.gridy = row;
		constraints.anchor = GridBagConstraints.NORTH;
		constraints.insets = INSETS_DEFAULT;
		constraints.weightx = 1;
		constraints.weighty = 1;
		constraints.fill = GridBagConstraints.BOTH;
		constraints.gridwidth = GridBagConstraints.REMAINDER;
		return constraints;
	}
	
	public void setTableModel(final GenericTableModel<?> model) {
		table.setModel(model);
	}
	
	public void addRow(){
	}
}
