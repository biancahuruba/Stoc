package exemplu.functionalitati.stoc;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.DocumentListener;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumn;

import exemplu.common.models.GenericRowModel;
import exemplu.common.models.GenericTableModel;
import exemplu.common.models.MyTableModel;
import javafx.application.Platform;
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
	private transient JTextField textFieldProdus;
	/** text field for Categorie. */
	private transient JTextField textFieldCategor;
	/** text field for Pret. */
	private transient JTextField textFieldPret;
	/** text field for Cod. */
	private transient JTextField textFieldCod;
	/** check Aprobat. */
	private transient JCheckBox checkBoxAprobat;
	/** choose date. */
	private javafx.scene.control.DatePicker datePicker;

	public static final String FIELD_PRODUS = "Produs";
	public static final String FIELD_KEY = "FieldName";
	public static final String FIELD_CATEGORIE = "Categorie";
	public static final String FIELD_PRET = "Pret";
	public static final String FIELD_COD = "Cod";
	public static final String FIELD_DATA = "Data";

	/**
	 * constructor.
	 */
	public StocView(final DocumentListener documentListener, final ActionListener actionListener) {
		super();
		initContainer();
		initTitle();
		initFields(documentListener, actionListener);
		

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
		initButtons(actionListener);
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

	private void initFields(final DocumentListener documentListener, final ActionListener actionListener) {
		initFirstColumn(documentListener);
		initSecondColumne(actionListener);
	}

	private void initFirstColumn(final DocumentListener documentListener) {
		final JLabel labelProdus = new JLabel("Produs");
		final JLabel labelCategorie = new JLabel("Categorie");
		final JLabel labelPret = new JLabel("Pret");
		final JLabel labelCod = new JLabel("Cod");

		textFieldProdus = new JTextField(20);
		textFieldCategor = new JTextField(20);
		textFieldPret = new JTextField(20);
		textFieldCod = new JTextField(15);

		add(labelProdus, constrains(0, 1));
		add(labelCategorie, constrains(0, 2));
		add(labelPret, constrains(0, 3));
		add(labelCod, constrains(0, 4));

		textFieldProdus.getDocument().putProperty(FIELD_KEY, FIELD_PRODUS);
		textFieldCategor.getDocument().putProperty(FIELD_KEY, FIELD_CATEGORIE);
		textFieldPret.getDocument().putProperty(FIELD_KEY, FIELD_PRET);
		textFieldCod.getDocument().putProperty(FIELD_KEY, FIELD_COD);

		add(textFieldProdus, constrains(1, 1));
		add(textFieldCategor, constrains(1, 2));
		add(textFieldPret, constrains(1, 3));
		add(textFieldCod, constrains(1, 4));
		textFieldProdus.getDocument().addDocumentListener(documentListener);
		textFieldCategor.getDocument().addDocumentListener(documentListener);
		textFieldPret.getDocument().addDocumentListener(documentListener);
		textFieldCod.getDocument().addDocumentListener(documentListener);

	}

	private void initSecondColumne(final ActionListener actionListener) {
		final JLabel labelData = new JLabel("Data");
		final JLabel labelAprobat = new JLabel("Aprobat");

		add(labelData, constrains(2, 1, INSET_SPACE));
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
		add(checkBoxAprobat, constrains(3, 2));
		checkBoxAprobat.addActionListener(actionListener);
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

	private void initButtons(final ActionListener actionListener) {
		final JButton buttonSalvare = new JButton("Salvare");
		final JButton buttonEditare = new JButton("Editare");
		final JButton buttonStergere = new JButton("Stergere");

		buttonEditare.addActionListener(actionListener);
		buttonSalvare.addActionListener(actionListener);
		buttonStergere.addActionListener(actionListener);

		add(buttonSalvare, buttonConstrains());
		add(buttonEditare, buttonConstrainsEast());
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
			return datePicker.getAccessibleText();
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
			datePicker.setAccessibleText(value);
			break;
		default:
			break;
		}
	}

	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}
	
	

}
