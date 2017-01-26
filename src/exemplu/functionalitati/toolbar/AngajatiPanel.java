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
public class AngajatiPanel extends JPanel {

	public static final Insets INSETS = new Insets(4, 4, 4, 4);
	public static final String FIELD_PRODUS = "Produs";
	public static final String FIELD_KEY = "FieldName";
	public static final String FIELD_PRET = "Pret";
	private JTextField textFieldProdus;
	private JTextField textFieldPret;
	private JButton search;
	private JButton clear;
	private JButton advanced;
	
	public AngajatiPanel(ActionListener actionListener, DocumentListener documentListener) {
		super();
		setLayout(new GridBagLayout());
		initFields();
		textFieldProdus.getDocument().addDocumentListener(documentListener);
		textFieldPret.getDocument().addDocumentListener(documentListener);
		addButtons(actionListener);
	}
	
	private void initFields() {
		final JLabel jLabelNume = new JLabel("Produs:   ");
		add(jLabelNume, getConstraints(1, 0));
		textFieldProdus = new JTextField(20);
		textFieldProdus.getDocument().putProperty(FIELD_KEY, FIELD_PRODUS);

		add(textFieldProdus, getConstraints(1, 0));

		final JLabel jLabelPrenume = new JLabel("   Pret:   ");
		add(jLabelPrenume, getConstraints(2, 0));
		textFieldPret = new JTextField(20);
		textFieldPret.getDocument().putProperty(FIELD_KEY, FIELD_PRET);
		add(textFieldPret, getConstraints(2, 0));
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
