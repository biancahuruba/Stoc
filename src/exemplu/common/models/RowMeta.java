package exemplu.common.models;

import java.util.List;

public class RowMeta {
	private List<String> columnNames;

	public int getColumnCount() {
		return columnNames.size();
	}

	public List<String> getColumnNames() {
		return columnNames;
	}

	public void setColumnNames(final List<String> columnNames) {
		this.columnNames = columnNames;
	}
}
