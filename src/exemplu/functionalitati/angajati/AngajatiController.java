package exemplu.functionalitati.angajati;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import exemplu.common.interfaces.ControllerInterface;
import exemplu.common.models.Attribute;
import exemplu.common.models.GenericTableModel;
import exemplu.common.models.RowMeta;

public class AngajatiController implements ControllerInterface, ActionListener, DocumentListener {
	private AngajatiView view;
	private AngajatiModel model;
	private AngajatiDAOImpl dao;

	public AngajatiController() {
		view = new AngajatiView();
		model = new AngajatiModel();
		dao = new AngajatiDAOImpl();
		setMockData();
		setListeners();
	}
	
	private void setListeners(){
		view.setActionListener(this);
		view.setDocumentListener(this);
	}

	private void setMockData() {
		final List<AngajatiRow> tabelModel = model.getTabeModel();
		final AngajatiRow row = new AngajatiRow();
		tabelModel.add(row);
		final RowMeta metadata = model.getRowMeta();
		metadata.setColumnNames(columnNames());
		view.setTableModel(new GenericTableModel<>(tabelModel, metadata));
	}

	private List<String> columnNames() {
		final List<String> columnNames = new ArrayList<>();
		columnNames.add("Produs");
		columnNames.add("Pret");
		columnNames.add("Cantitate");
		columnNames.add("Comision");
		return columnNames;

	}

	@Override
	public JPanel getView() {
		return view;
	}

	@Override
	public void actionPerformed(final ActionEvent event) {

		if (event.getActionCommand().equals("Salvare")) {
			view.stopEditing();
			dao.insertData(model);
			System.err.println("Inserted into database.");
		}
		if (event.getActionCommand().equals("Editare")) {
			int size = dao.listOfId().size();
			int id = dao.listOfId().get(size - 1);
			if (model.getNume().isChanged()) {
				dao.editData("nume", model.getNume().getValue(), id);
			}

			if (model.getPrenume().isChanged()) {
				dao.editData("prenume", model.getPrenume().getValue(), id);
			}

			List<AngajatiRow> list = model.getTabeModel();
			for (int i = 0; i < 4; i++) {
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
		final String fieldName = String.valueOf(event.getDocument().getProperty(AngajatiView.FIELD_KEY));
		switch (fieldName) {
		case AngajatiView.FIELD_NUME:
			updateField(model.getNume(), fieldName);
			break;
		case AngajatiView.FIELD_PRENUME:
			updateField(model.getPrenume(), fieldName);
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

	private String attributeName(int i) {
		switch (i) {
		case 0:
			return "produs";
		case 1:
			return "pret";
		case 2:
			return "cantitate";
		case 3:
			return "comision";
		}
		return null;
	}
}
