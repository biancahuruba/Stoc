package exemplu.functionalitati.stoc;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableColumn;

import exemplu.common.models.MyTableModel;

import com.github.lgooddatepicker.components.DatePicker;

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
	private DatePicker datePicker;

	private static final File file = new File("RowTable.txt");

	private TextFieldModel textModel;

	private ActionListener listenerAction;

	private TableModelListener listenerTable;

	private JButton button;

	public TableModelListener getListenerTable() {
		return listenerTable;
	}

	public void setListenerTable(TableModelListener newListener) {
		table.getModel().removeTableModelListener(listenerTable);
		this.listenerTable = newListener;
		table.getModel().removeTableModelListener(newListener);
		table.getModel().addTableModelListener(listenerTable);
	}

	public void display(TableModelEvent e) {
		table.getValueAt(e.getFirstRow(), e.getColumn());
		System.out.println(table.getValueAt(e.getFirstRow(), e.getColumn()));
		System.out.println(e.getType() + " " + e.getColumn() + " " + e.getFirstRow());
	}

	public ActionListener getListener() {
		return listenerAction;
	}

	public void setListener(ActionListener newListener) {
		checkBoxAprobat.removeActionListener(listenerAction);
		checkBoxAprobat.addActionListener(newListener);

		button.removeActionListener(listenerAction);
		button.addActionListener(newListener);

		this.listenerAction = newListener;
	}

	/**
	 * constructor.
	 */
	public StocView() {
		super();
		initContainer();
		initTitle();
		initFields();
		initTable();
		initButton();
	}

	private void initButton() {
		button = new JButton("Salvare");
		final GridBagConstraints ctbutton = new GridBagConstraints();

		ctbutton.anchor = GridBagConstraints.EAST;
		ctbutton.gridwidth = GridBagConstraints.REMAINDER;
		ctbutton.insets = INSETS;

		add(button, ctbutton);
	}

	private void initTable() {
		final JLabel labelTitleTable = new JLabel("Distributie Magazine");

		add(labelTitleTable, constrains(0, 5));

		final GridBagConstraints ctTabel = initializeTable();
		ctTabel.gridx = 0;
		ctTabel.gridy = 10;
		ctTabel.insets = INSETS;
		ctTabel.gridwidth = GridBagConstraints.REMAINDER;
		ctTabel.weightx = 1;
		ctTabel.weighty = 1;
		ctTabel.fill = GridBagConstraints.BOTH;
		add(new JScrollPane(table), ctTabel);
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
		final JLabel labelCategorie = new JLabel("Categorie");
		final JLabel labelPret = new JLabel("Pret");
		final JLabel labelCod = new JLabel("Cod");

		textFieldProdus = new JTextField(20);
		textFieldCategor = new JTextField(20);
		textFieldPret = new JTextField(20);
		textFieldCod = new JTextField(15);

		add(labelProdus, constrains(0, 1));

		final GridBagConstraints ctTextProdus = new GridBagConstraints();
		constrains(1, 1);
		add(textFieldProdus, ctTextProdus);

		add(labelCategorie, constrains(0, 2));

		add(textFieldCategor, constrains(1, 2));

		add(labelPret, constrains(0, 3));

		add(textFieldPret, constrains(1, 3));

		add(labelCod, constrains(0, 4));

		add(textFieldCod, constrains(1, 4));

	}

	private void initSecondColumne() {
		final JLabel labelData = new JLabel("Data");
		final JLabel labelAprobat = new JLabel("Aprobat");

		add(labelData, constrains(2, 1, INSET_SPACE));

		datePicker = new DatePicker();
		add(datePicker, constrains(3, 1));
		
		
		add(labelAprobat, constrains(2, 2, INSET_SPACE));

		checkBoxAprobat = new JCheckBox();
		checkBoxAprobat.setActionCommand(Commands.ENABLE.toString());
		add(checkBoxAprobat, constrains(3, 2));
	}

	private GridBagConstraints initializeTable() {
		table = new JTable();

		final JComboBox<String> comboBox = new JComboBox<>();
		final String choices[] = { "Arad", "Almas", "Fantanele", "Hunedoara", "Luna", "Negreni", "Rosiori", "Tasnad",
				"Zegujani" };
		for (int i = 0; i < choices.length; i++) {
			comboBox.addItem(choices[i]);
		}
		DefaultTableColumnModel colModel = getColModel();

		MyTableModel model = new MyTableModel();
		model.setColumnCount(colModel.getColumnCount());
		table.setModel(model);

		table.setColumnModel(colModel);
		table.setCellSelectionEnabled(true);
		addRow();
		final TableColumn localitateColumn = colModel.getColumn(1);
		localitateColumn.setCellEditor(new DefaultCellEditor(comboBox));

		return new GridBagConstraints();
	}

	public void addRow() {
		((MyTableModel) table.getModel()).addNewRow();
	}

	private DefaultTableColumnModel getColModel() {
		DefaultTableColumnModel colModel = new DefaultTableColumnModel();
		addColumn(colModel, 0, "Magazin");
		addColumn(colModel, 1, "Localitate");
		addColumn(colModel, 2, "Cantitate");
		return colModel;
	}

	private void addColumn(DefaultTableColumnModel colModel, int index, String name) {
		TableColumn aColumn = new TableColumn(index);
		aColumn.setHeaderValue(name);
		colModel.addColumn(aColumn);
	}

	public void setAprobat() {
		textFieldPret.setEnabled(!textFieldPret.isEnabled());
	}

	public void SaveDataFromTable() {
		int row = readRowNumber(file);
		for (int column = 0; column < table.getColumnCount(); column++) {
			table.getModel().setValueAt(table.getValueAt(row - 1, column), row - 1, column);
		}
		row++;
		saveRowNumeber(row, file);

	}

	public void documentListener() {
		textFieldCategor.getDocument().addDocumentListener(new DocumentListener() {

			public void insertUpdate(DocumentEvent e) {
				System.out.println("Insert");
			}

			public void removeUpdate(DocumentEvent e) {
				System.out.println("Remove");
			}

			public void changedUpdate(DocumentEvent e) {
				System.out.println("Change");
			}
		});
	}

	public void SaveDataFromFields() {
		String[] list = new String[4];
		list[0] = textFieldProdus.getText();
		list[1] = textFieldCategor.getText();
		list[2] = textFieldPret.getText();
		list[3] = textFieldCod.getText();

		textModel = new TextFieldModel();
		textModel.add(list);

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

	private GridBagConstraints constrains(int x, int y, Insets insets, int grid) {
		GridBagConstraints object = new GridBagConstraints();
		object.gridx = x;
		object.gridy = y;
		object.anchor = grid;
		object.insets = insets;
		return object;
	}

	private void saveRowNumeber(int row, File file) {
		FileWriter fileWriter;
		try {
			PrintWriter writ = new PrintWriter(file);
			writ.print("");
			writ.close();

			fileWriter = new FileWriter(file);
			fileWriter.write("" + row);
			fileWriter.flush();
			fileWriter.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private int readRowNumber(File file) {

		FileReader fileReader;
		try {
			fileReader = new FileReader(file);
			StringBuffer stringBuffer = new StringBuffer();
			int numCharsRead;
			char[] charArray = new char[1024];
			while ((numCharsRead = fileReader.read(charArray)) > 0) {
				stringBuffer.append(charArray, 0, numCharsRead);
			}
			fileReader.close();
			return Integer.parseInt(stringBuffer.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 0;

	}
}
