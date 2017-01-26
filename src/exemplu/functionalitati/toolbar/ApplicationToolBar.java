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
	public static final String COMBO_TIP = "Combo Tip";
	public static final String TBCLEAR ="\u2713";
	private JComboBox<String> combo;
	private JButton search;
	private JButton clear;
	private JButton advanced;
	private JPanel container;
	private ActionListener actionListener;
	private JTextField produs;
	private JTextField pret;
	private JTextField nume;
	private JTextField prenume;

	public ApplicationToolBar() {
		setLayout(new GridBagLayout());
		setVisible(true);
		initTextFields();
		initCombo();
		GridBagConstraints ctContainer = new GridBagConstraints();
		ctContainer.weightx = 1;
		ctContainer.fill = GridBagConstraints.BOTH;
		container = new JPanel();
		container.setBorder(BorderFactory.createEmptyBorder());
		add(container, ctContainer);
		addButtons();
	}
	private void initTextFields(){
		produs= new JTextField(15);
		pret= new JTextField(15);
		nume= new JTextField(15);
		prenume= new JTextField(15);;
	}

	private void initCombo() {
		combo = new JComboBox<>(new String[] { "Stoc", "Angajati" });
		combo.addActionListener(actionListener);
		combo.setActionCommand(COMBO_TIP);
		GridBagConstraints ctCombo = new GridBagConstraints();
		ctCombo.anchor = GridBagConstraints.WEST;
		add(combo, ctCombo);
	}

	public void addFieldsTipAngajat() {
		container.removeAll();
		addNewTextField("Nume", nume);
		addNewTextField("Prenume",prenume);
		container.repaint();
		container.revalidate();
	}

	public void addFieldsTipStoc() {
		container.removeAll();
		addNewTextField("Produs",produs);
		addNewTextField("Pret",pret);
	}

	public void addNewTextField(String name, JTextField textField) {
		JLabel label = new JLabel(name);
		container.add(label);
		container.add(textField);
		container.validate();
		validate();
		repaint();
	}

	public void addButtons() {
		search = new JButton("\u2713");
		search.addActionListener(actionListener);
		add(search);

		clear = new JButton("\u2717");
		clear.addActionListener(actionListener);
		clear.setActionCommand(TBCLEAR);
		add(clear);

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

	public ActionListener getActionListener() {
		return actionListener;
	}

	public void setActionListener(ActionListener newactionListener) {
		combo.removeActionListener(actionListener);
		combo.addActionListener(newactionListener);
		
		clear.removeActionListener(actionListener);
		clear.addActionListener(newactionListener);
		actionListener = newactionListener;
	}

	public JTextField getProdus() {
		return produs;
	}

	public void setProdus(JTextField produs) {
		this.produs = produs;
	}

	public JTextField getPret() {
		return pret;
	}

	public void setPret(JTextField pret) {
		this.pret = pret;
	}

	public JTextField getNume() {
		return nume;
	}

	public void setNume(JTextField nume) {
		this.nume = nume;
	}

	public JTextField getPrenume() {
		return prenume;
	}

	public void setPrenume(JTextField prenume) {
		this.prenume = prenume;
	}
	
}
