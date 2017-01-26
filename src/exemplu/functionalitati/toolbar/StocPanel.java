package exemplu.functionalitati.toolbar;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentListener;

@SuppressWarnings("serial")
public class StocPanel extends JPanel {
	public static final Insets INSETS = new Insets(4, 4, 4, 4);
	public static final String FIELD_PRENUME = "Prenume";
	public static final String FIELD_KEY = "FieldName";
	public static final String FIELD_NUME = "Nume";
	private JTextField textFieldNume;
	private JTextField textFieldPrenume;
	private JButton search;
	private JButton clear;
	private JButton advanced;

	public StocPanel(DocumentListener listener, ActionListener actionListener) {
		super();
		setLayout(new GridBagLayout());
		initFields();
		textFieldNume.getDocument().addDocumentListener(listener);
		textFieldPrenume.getDocument().addDocumentListener(listener);
		addButtons(actionListener);

	}

	private void initFields() {
		final JLabel jLabelNume = new JLabel("Nume:   ");
		add(jLabelNume, getConstraints(1, 0));
		textFieldNume = new JTextField(20);
		textFieldNume.getDocument().putProperty(FIELD_KEY, FIELD_NUME);

		add(textFieldNume, getConstraints(1, 0));

		final JLabel jLabelPrenume = new JLabel("   Prenume:   ");
		add(jLabelPrenume, getConstraints(2, 0));
		textFieldPrenume = new JTextField(20);
		textFieldPrenume.getDocument().putProperty(FIELD_KEY, FIELD_PRENUME);
		add(textFieldPrenume, getConstraints(2, 0));
	}

	private GridBagConstraints getConstraints(final int row, final int column) {
		final GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridx = column;
		constraints.gridy = row;
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets = INSETS;
		return constraints;
	}

	public void addButtons(ActionListener actionListener) {
		clear = new JButton("\u2713");
		clear.addActionListener(actionListener);
		add(clear);

		search = new JButton("\u2717");
		clear.addActionListener(actionListener);
		add(search);

		advanced = new JButton("Cautare avansata");
		advanced.addActionListener(actionListener);
		add(search);
	}

}
