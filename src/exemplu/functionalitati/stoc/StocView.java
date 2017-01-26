package exemplu.functionalitati.stoc;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableCellEditor;

import exemplu.common.models.GenericTableModel;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;

public class StocView extends JPanel {
	private static final long serialVersionUID = 1L;
	/** INSET_SPACE AT DATEPICKER. */
	private static final Insets INSET_SPACE = new Insets(4, 40, 4, 4);
	/** default insets. */
	public static final Insets INSETS = new Insets(4, 4, 4, 4);
	/** table used to Distributie Magazine. */
	private JTable table;
	/** text field for Produs. */
	private JTextField textFieldProdus;
	/** text field for Categorie. */
	private JTextField textFieldCategor;
	/** text field for Pret. */
	private JTextField textFieldPret;
	/** text field for Cod. */
	private JTextField textFieldCod;
	/** check Aprobat. */
	private JCheckBox checkBoxAprobat;
	/** choose date. */
	private javafx.scene.control.DatePicker datePicker;

	private DocumentListener documentListener;
	private ActionListener actionListener;
	private ChangeListener<?> changeListener;

	public static final String FIELD_PRODUS = "Produs";
	public static final String FIELD_KEY = "FieldName";
	public static final String FIELD_CATEGORIE = "Categorie";
	public static final String FIELD_PRET = "Pret";
	public static final String FIELD_COD = "Cod";
	public static final String FIELD_DATA = "Data";
	private JButton buttonSalvare;
	private JButton buttonEditare;
	private JButton buttonStergere;

	/**
	 * constructor.
	 */
	public StocView() {
		super();
		initContainer();
		initTitle();
		initFields();
		initTable();
		initButtons();
	}

	private void initContainer() {
		setLayout(new GridBagLayout());
		setMinimumSize(new Dimension(500, 500));
	}

	private void initTitle() {
		final JLabel labelTitle = new JLabel("Stoc");
		Font font = labelTitle.getFont();
		Font newFont = new Font(font.getName(), Font.BOLD, 18);
		labelTitle.setFont(newFont);

		final GridBagConstraints ctTitle = new GridBagConstraints();
		ctTitle.weightx = 1;
		ctTitle.gridx = 0;
		ctTitle.gridy = 0;
		ctTitle.gridwidth = GridBagConstraints.REMAINDER;
		add(labelTitle, ctTitle);
	}

	private void initFields() {
		initFirstColumn();
		initSecondColumne();
	}

	private void initFirstColumn() {
		final JLabel labelProdus = new JLabel("Produs");
		add(labelProdus, constrains(0, 1));
		textFieldProdus = new JTextField(20);
		textFieldProdus.getDocument().putProperty(FIELD_KEY, FIELD_PRODUS);
		textFieldProdus.getDocument().addDocumentListener(documentListener);
		add(textFieldProdus, constrains(1, 1));

		final JLabel labelCategorie = new JLabel("Categorie");
		add(labelCategorie, constrains(0, 2));
		textFieldCategor = new JTextField(20);
		textFieldCategor.getDocument().putProperty(FIELD_KEY, FIELD_CATEGORIE);
		textFieldCategor.getDocument().addDocumentListener(documentListener);
		add(textFieldCategor, constrains(1, 2));

		final JLabel labelPret = new JLabel("Pret");
		add(labelPret, constrains(0, 3));
		textFieldPret = new JTextField(20);
		textFieldPret.getDocument().putProperty(FIELD_KEY, FIELD_PRET);
		textFieldPret.getDocument().addDocumentListener(documentListener);
		add(textFieldPret, constrains(1, 3));

		final JLabel labelCod = new JLabel("Cod");
		textFieldCod = new JTextField(15);
		add(labelCod, constrains(0, 4));
		textFieldCod.getDocument().putProperty(FIELD_KEY, FIELD_COD);
		textFieldCod.getDocument().addDocumentListener(documentListener);
		add(textFieldCod, constrains(1, 4));

	}

	private void initSecondColumne() {
		final JLabel labelData = new JLabel("Data");
		add(labelData, constrains(2, 1, INSET_SPACE));

		final JLabel labelAprobat = new JLabel("Aprobat");
		add(labelAprobat, constrains(2, 2, INSET_SPACE));

		JFXPanel panel = new JFXPanel();
		Platform.setImplicitExit(false);
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				initFX(panel);
			}
		});
		add(panel, constrains(3, 1));

		checkBoxAprobat = new JCheckBox();
		checkBoxAprobat.setActionCommand(Commands.ENABLE.toString());
		checkBoxAprobat.addActionListener(actionListener);
		add(checkBoxAprobat, constrains(3, 2));
	}

	private void initFX(JFXPanel fxPanel) {
		Dimension size = new Dimension(175, 25);
		fxPanel.setPreferredSize(size);
		fxPanel.setSize(size);
		fxPanel.setMaximumSize(size);
		Scene scene = createScene();
		fxPanel.setScene(scene);
	}

	private Scene createScene() {
		final FlowPane root = new FlowPane();
		root.setVgap(0);
		root.setHgap(0);
		final Scene scene = new Scene(root, Color.ALICEBLUE);
		datePicker = new DatePicker();
		root.getChildren().add(datePicker);
		return (scene);
	}

	private void initTable() {
		table = new JTable();

		final GridBagConstraints ctTabel = new GridBagConstraints();
		ctTabel.gridx = 0;
		ctTabel.gridy = 10;
		ctTabel.insets = INSETS;
		ctTabel.gridwidth = GridBagConstraints.REMAINDER;
		ctTabel.weightx = 1;
		ctTabel.weighty = 1;
		ctTabel.fill = GridBagConstraints.BOTH;
		add(new JScrollPane(table), ctTabel);

		final JLabel labelTitleTable = new JLabel("Distributie Magazine");
		add(labelTitleTable, constrains(0, 5));
	}

	public void setAprobat() {
		textFieldPret.setEnabled(!textFieldPret.isEnabled());
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

	private void initButtons() {
		buttonSalvare = new JButton("Salvare");
		add(buttonSalvare, buttonConstrains());

		buttonEditare = new JButton("Editare");
		add(buttonEditare, buttonConstrainsEast());

		buttonStergere = new JButton("Stergere");
		add(buttonStergere, buttonConstrainsEast());
	}

	private GridBagConstraints buttonConstrains() {
		GridBagConstraints ctbutton = new GridBagConstraints();
		ctbutton.anchor = GridBagConstraints.WEST;
		ctbutton.insets = INSETS;
		return ctbutton;
	}

	private GridBagConstraints buttonConstrainsEast() {
		GridBagConstraints ctbutton = new GridBagConstraints();
		ctbutton.anchor = GridBagConstraints.EAST;
		ctbutton.insets = INSETS;
		return ctbutton;
	}

	private GridBagConstraints constrains(int x, int y) {
		GridBagConstraints object = new GridBagConstraints();
		object.gridx = x;
		object.gridy = y;
		object.anchor = GridBagConstraints.WEST;
		object.insets = INSETS;
		return object;
	}

	private GridBagConstraints constrains(int x, int y, Insets insets) {
		GridBagConstraints object = new GridBagConstraints();
		object.gridx = x;
		object.gridy = y;
		object.anchor = GridBagConstraints.WEST;
		object.insets = insets;
		return object;
	}

	public String getFieldValue(final String fieldName) {
		switch (fieldName) {
		case FIELD_PRODUS:
			return textFieldProdus.getText();
		case FIELD_CATEGORIE:
			return textFieldCategor.getText();
		case FIELD_PRET:
			return textFieldPret.getText();
		case FIELD_COD:
			return textFieldCod.getText();
		case FIELD_DATA:
			return String.valueOf(datePicker.getValue());
		default:
			return null;
		}
	}

	public void setFieldValue(final String fieldName, final String value) {
		switch (fieldName) {
		case FIELD_PRODUS:
			textFieldProdus.setText(value);
			break;
		case FIELD_CATEGORIE:
			textFieldCategor.setText(value);
			break;
		case FIELD_PRET:
			textFieldPret.setText(value);
			break;
		case FIELD_COD:
			textFieldCod.setText(value);
			break;
		case FIELD_DATA:
			datePicker.setValue(LOCAL_DATE(value));
			break;
		default:
			break;
		}
	}

	public static final LocalDate LOCAL_DATE(String dateString) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDate localDate = LocalDate.parse(dateString, formatter);
		return localDate;
	}

	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}

	public DocumentListener getDocumentListener() {
		return documentListener;
	}

	public void setDocumentListener(DocumentListener documentListener) {
		this.documentListener = documentListener;
	}

	public ActionListener getActionListener() {
		return actionListener;
	}

	public void setActionListener(ActionListener newActionListener) {
		checkBoxAprobat.removeActionListener(actionListener);
		checkBoxAprobat.addActionListener(newActionListener);

		buttonEditare.removeActionListener(actionListener);
		buttonEditare.addActionListener(newActionListener);

		buttonSalvare.removeActionListener(actionListener);
		buttonSalvare.addActionListener(newActionListener);

		buttonStergere.removeActionListener(actionListener);
		buttonStergere.addActionListener(newActionListener);

		actionListener = newActionListener;
	}

	public ChangeListener<?> getChangeListener() {
		return changeListener;
	}

	@SuppressWarnings("unchecked")
	public void setChangeListener(ChangeListener<?> newchangeListener) {
		changeListener = newchangeListener;
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				datePicker.valueProperty().addListener((ChangeListener<? super LocalDate>) changeListener);
			}
		});
	}
}
