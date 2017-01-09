package exemplu.common.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.table.AbstractTableModel;

@SuppressWarnings("serial")
public class MyTableModel extends AbstractTableModel {

	Map<Integer, List<Attribute>> model;
	private int columnCount;

	public MyTableModel() {
		model = new HashMap<>();
		columnCount = 0;
	}

	@Override
	public int getRowCount() {
		return model.size();
	}

	@Override
	public int getColumnCount() {
		return columnCount;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		boolean isValidRow = rowIndex >= 0 && rowIndex < model.size();
		boolean isValidColumn = columnIndex >= 0 && columnIndex <= columnCount;
		if (isValidRow && isValidColumn) {
			return model.get(rowIndex).get(columnIndex);
		}

		return null;
	}

	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return true;
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		setValueAt(String.valueOf(aValue), rowIndex, columnIndex);
	}

	public void setValueAt(String value, int rowIndex, int columnIndex) {
		Attribute input = new Attribute();
		input.setValue(value);
		(model.get(rowIndex)).set(columnIndex, input);
		fireTableCellUpdated(rowIndex, columnIndex);
	}

	public void addNewRow() {
		List<Attribute> row = new ArrayList<>();
		for (int i = 0; i < columnCount; i++) {
			row.add(new Attribute());
		}
		model.put(getRowCount(), row);
		fireTableRowsInserted(0, 0);
	}

	public void setColumnCount(int value) {
		if (value < 0) {
			return;
		}
		if (value < columnCount) {
			for (int i = 0; i < getRowCount(); i++) {
				for (int j = value; j < columnCount; j++) {
					model.get(i).remove(j);
				}
			}
		} else {
			for (int i = 0; i < getRowCount(); i++) {
				for (int j = 0; j < value - columnCount; j++) {
					model.get(i).add(new Attribute());
				}
			}

		}
		columnCount = value;
	}

}
