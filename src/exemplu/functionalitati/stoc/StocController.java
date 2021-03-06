package exemplu.functionalitati.stoc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableColumn;

import exemplu.common.interfaces.ControllerInterface;
import exemplu.common.models.Attribute;
import exemplu.common.models.GenericTableModel;
import exemplu.common.models.RowMeta;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class StocController
		implements ControllerInterface, ActionListener, DocumentListener, ChangeListener<LocalDate> {
	private StocView view;
	private StocModel model;
	private StocDAOImpl dao;

	public StocController() {
		view = new StocView();
		model = new StocModel();
		dao = new StocDAOImpl();
		setListeners();
		setMockData();

	}

	private void setListeners() {
		view.setActionListener(this);
		view.setDocumentListener(this);
		view.setChangeListener(this);
	}

	public void setMockData() {
		final List<DistributieMagazinModel> tabelModel = model.getTableList();
		final DistributieMagazinModel row = new DistributieMagazinModel();
		tabelModel.add(row);
		final RowMeta metadata = model.getRowMeta();
		metadata.setColumnNames(columnNames());
		view.setTableModel(new GenericTableModel<>(tabelModel, metadata));
		TableColumn col = view.getTable().getColumnModel().getColumn(1);
		col.setCellEditor(new DefaultCellEditor(comboBox()));
	}

	private List<String> columnNames() {
		final List<String> columnNames = new ArrayList<>();
		columnNames.add("Magazin");
		columnNames.add("Localitate");
		columnNames.add("Cantitate");
		return columnNames;
	}

	private JComboBox<String> comboBox() {
		final JComboBox<String> comboBox = new JComboBox<>();
		final String choices[] = { "Arad", "Almas", "Fantanele", "Hunedoara", "Luna", "Negreni", "Rosiori", "Tasnad",
				"Zegujani" };
		for (int i = 0; i < choices.length; i++) {
			comboBox.addItem(choices[i]);
		}
		return comboBox;
	}

	@Override
	public JPanel getView() {
		return view;
	}

	public void setView(StocView view) {
		this.view = view;
	}

	public StocModel getModel() {
		return model;
	}

	public void setModel(StocModel model) {
		this.model = model;
	}

	@Override
	public void actionPerformed(final ActionEvent event) {
		if (Commands.ENABLE.toString().equals(event.getActionCommand())) {
			view.setAprobat();
		}
		if (event.getActionCommand().equals("Salvare")) {
			view.stopEditing();
			dao.insertData(model);
			System.err.println("Inserted into database.");
		}
		if (event.getActionCommand().equals("Editare")) {
			int size = dao.listOfId().size();
			int id = dao.listOfId().get(size - 1);
			if (model.getProdus().isChanged()) {
				dao.editData("produs", model.getProdus().getValue(), id);
			}
			if (model.getCategorie().isChanged()) {
				dao.editData("categorie", model.getCategorie().getValue(), id);
			}
			if (model.getPret().isChanged()) {
				dao.editData("pret", model.getPret().getValue(), id);
			}
			if (model.getCod().isChanged()) {
				dao.editData("cod", model.getCod().getValue(), id);
			}
			if (model.getData().isChanged()) {
				dao.editData("data", model.getData().getValue(), id);
			}

			List<DistributieMagazinModel> list = model.getTableList();
			for (int i = 0; i < 3; i++) {
				if (list.get(0).getAttribute(i).isChanged()) {
					dao.editData(attributeName(i), list.get(0).getAttribute(i).getValue(), id);
				}
			}
		}
		if (event.getActionCommand().equals("Stergere")) {
			int size = dao.listOfId().size();
			int id = dao.listOfId().get(size - 1);
			dao.deleteData(id);
		}
	}

	private String attributeName(int i) {
		switch (i) {
		case 0:
			return "magazin";
		case 1:
			return "localitate";
		case 2:
			return "cantitate";
		}
		return null;
	}

	@Override
	public void insertUpdate(final DocumentEvent event) {
		updateModelForTextFields(event);
	}

	@Override
	public void removeUpdate(final DocumentEvent event) {
		updateModelForTextFields(event);
	}

	@Override
	public void changedUpdate(final DocumentEvent event) {
		updateModelForTextFields(event);
	}

	private void updateModelForTextFields(final DocumentEvent event) {
		final String fieldName = String.valueOf(event.getDocument().getProperty(StocView.FIELD_KEY));
		switch (fieldName) {
		case StocView.FIELD_PRODUS:
			updateField(model.getProdus(), fieldName);
			break;
		case StocView.FIELD_CATEGORIE:
			updateField(model.getCategorie(), fieldName);
			break;
		case StocView.FIELD_PRET:
			updateField(model.getPret(), fieldName);
			break;
		case StocView.FIELD_COD:
			updateField(model.getCod(), fieldName);
			break;
		case StocView.FIELD_DATA:
			updateField(model.getData(), fieldName);
			break;
		default:
			break;
		}
	}

	private void updateField(final Attribute targetField, final String fieldKey) {
		final String newValue = view.getFieldValue(fieldKey);
		if (!targetField.getValue().equals(newValue)) {
			targetField.setValue(newValue);
			targetField.setChanged(true);
		}
	}

	@Override
	public void changed(ObservableValue<? extends LocalDate> observable, LocalDate oldValue, LocalDate newValue) {
		updateField(model.getData(), StocView.FIELD_DATA);
	}

}
