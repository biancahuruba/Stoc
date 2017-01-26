package exemplu.functionalitati.toolbar;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToolBar;

@SuppressWarnings("serial")
public class ApplicationToolBar extends JToolBar {

	private JButton search;
	private JButton clear;
	private JButton advanced;
	public static final String COMBO_TIP = "Combo Tip";
	private JComboBox<String> combo;
	private JPanel container;

	public ApplicationToolBar(ActionListener actionListener) {
		setLayout(new GridBagLayout());
		setVisible(true);
		combo = new JComboBox<>(new String[] { "Stoc", "Angajati" });

		combo.addActionListener(actionListener);
		combo.setActionCommand(COMBO_TIP);
		GridBagConstraints ctCombo = new GridBagConstraints();
		ctCombo.anchor = GridBagConstraints.WEST;
		add(combo, ctCombo);
		GridBagConstraints ctContainer = new GridBagConstraints();
		ctContainer.weightx = 1;
		ctContainer.fill = GridBagConstraints.BOTH;
		container = new JPanel();
		container.setBorder(BorderFactory.createEmptyBorder());
		add(container, ctContainer);
		addButtons(actionListener);
	}

	public void addFieldsTipAngajat() {
		container.removeAll();
		addNewTextField("Nume");
		addNewTextField("Prenume");
		container.repaint();
		container.revalidate();
	}

	public void addFieldsTipStoc() {
		container.removeAll();
		addNewTextField("Produs");
		addNewTextField("Pret");
	}

	public void addNewTextField(String name) {
		JLabel label = new JLabel(name);
		container.add(label);
		
		JTextField textField = new JTextField(15);
		container.add(textField);
		container.validate();
		validate();
		repaint();
	}

	public void addButtons(ActionListener actionListener) {
		clear = new JButton("\u2713");
		clear.addActionListener(actionListener);
		add(clear);

		search = new JButton("\u2717");
		clear.addActionListener(actionListener);
		add(search);

		advanced = new JButton("\uDC40");
		advanced.addActionListener(actionListener);
		add(advanced);

	}

	public JComboBox<String> getCombo() {
		return combo;
	}

	public void setCombo(JComboBox<String> combo) {
		this.combo = combo;
	}

}
