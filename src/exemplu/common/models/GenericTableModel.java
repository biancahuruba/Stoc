package exemplu.common.models;

import java.util.List;

import javax.swing.table.AbstractTableModel;

@SuppressWarnings("serial")
public class GenericTableModel<T extends GenericRowModel> extends AbstractTableModel {
	private List<T> model;
	private RowMeta meta;

	public GenericTableModel(final List<T> tabelModel, final RowMeta metadata) {
		model = tabelModel;
		meta = metadata;
	}

	@Override
	public int getRowCount() {
		return model.size();
	}

	@Override
	public int getColumnCount() {
		return meta.getColumnCount();
	}

	@Override
	public String getColumnName(final int column) {
		return meta.getColumnNames().get(column);
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return model.get(rowIndex).getAttribute(columnIndex);
	}

	@Override
	public void setValueAt(final Object aValue, final int rowIndex, final int columnIndex) {
		model.get(rowIndex).setAttribute(columnIndex, String.valueOf(aValue));
		fireTableCellUpdated(rowIndex, columnIndex);
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return true;
	}
}
