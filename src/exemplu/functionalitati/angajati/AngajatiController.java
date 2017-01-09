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

	public AngajatiController() {
		view = new AngajatiView(this, this);
		model = new AngajatiModel();
		setMockData();
	}

	private void setMockData() {
		final List<AngajatiRow> tabelModel = model.getTabeModel();
		final AngajatiRow row = new AngajatiRow();
		tabelModel.add(row);
		final RowMeta metadata = model.getRowMeta();
		final List<String> columnNames = new ArrayList<>();
		columnNames.add("Produs");
		columnNames.add("Pret");
		columnNames.add("Cantitate");
		columnNames.add("Comision");
		metadata.setColumnNames(columnNames);
		view.setTableModel(new GenericTableModel<>(tabelModel, metadata));
	}

	@Override
	public JPanel getView() {
		return view;
	}

	@Override
	public void actionPerformed(final ActionEvent event) {
		view.stopEditing();
		System.err.println(model.toString());
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
}
