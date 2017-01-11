package exemplu.functionalitati.angajati;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableCellEditor;

import exemplu.common.models.GenericTableModel;

@SuppressWarnings("serial")
public class AngajatiView extends JPanel {
	public static final String FIELD_PRENUME = "Prenume";
	public static final String FIELD_KEY = "FieldName";
	public static final String FIELD_NUME = "Nume";
	private static final Insets INSETS_DEFAULT = new Insets(4, 4, 4, 4);
	private JTextField jTextFieldNume;
	private JTextField jTextFieldPrenume;
	private JTable table;

	public AngajatiView(final DocumentListener listener, final ActionListener actionListener) {
		setLayout(new GridBagLayout());

		initFieldNume();
		initFieldPrenume();
		initButton(actionListener);

		jTextFieldNume.getDocument().addDocumentListener(listener);
		jTextFieldPrenume.getDocument().addDocumentListener(listener);

		table = new JTable();
		add(new JScrollPane(table), getFillerConstraints(2, 0));

	}

	private void initFieldNume() {
		final JLabel jLabelNume = new JLabel("Nume:");
		add(jLabelNume, getConstraints(0, 0));
		jTextFieldNume = new JTextField(20);
		jTextFieldNume.getDocument().putProperty(FIELD_KEY, FIELD_NUME);
		add(jTextFieldNume, getConstraints(0, 1));

	}

	private void initFieldPrenume() {
		final JLabel jLabelPrenume = new JLabel("Prenume:");
		add(jLabelPrenume, getConstraints(1, 0));
		jTextFieldPrenume = new JTextField(20);
		jTextFieldPrenume.getDocument().putProperty(FIELD_KEY, FIELD_PRENUME);
		add(jTextFieldPrenume, getConstraints(1, 1));
	}

	private void initButton(final ActionListener actionListener) {
		final JButton jButtonSave = new JButton("Salvare");
		jButtonSave.addActionListener(actionListener);
		add(jButtonSave, getConstraints(3, 0));
		
		final JButton jButtonEdit= new JButton("Editare");
		jButtonEdit.addActionListener(actionListener);
		add(jButtonEdit, getConstraints(3, 1));
		
		final JButton jButtonDelete= new JButton("Stergere");
		jButtonDelete.addActionListener(actionListener);
		add(jButtonDelete, getConstraints(3, 1));

	}
	private GridBagConstraints getConstraints(final int row, final int column) {
		final GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridx = column;
		constraints.gridy = row;
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets = INSETS_DEFAULT;
		return constraints;
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

	public String getFieldValue(final String fieldName) {
		switch (fieldName) {
		case FIELD_NUME:
			return jTextFieldNume.getText();
		case FIELD_PRENUME:
			return jTextFieldPrenume.getText();
		default:
			return null;
		}
	}

	public void setFieldValue(final String fieldName, final String value) {
		switch (fieldName) {
		case FIELD_NUME:
			jTextFieldNume.setText(value);
			break;
		case FIELD_PRENUME:
			jTextFieldPrenume.setText(value);
			break;
		default:
			break;
		}
	}

	public void setTableModel(final GenericTableModel<?> model) {
		table.setModel(model);
	}

	public void stopEditing() {
		final TableCellEditor editor = table.getCellEditor();
		if (editor != null) {
			editor.stopCellEditing();
		}
	}
}
