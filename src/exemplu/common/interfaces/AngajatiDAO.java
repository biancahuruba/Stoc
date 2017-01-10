package exemplu.common.interfaces;

import java.util.List;

import exemplu.functionalitati.angajati.AngajatiModel;

public interface AngajatiDAO {

	public void insertData(AngajatiModel angajat);

	public List<AngajatiModel> readData();

	public void editData(AngajatiModel angajat, String columnName, String columnValue, int id);

	public void updateDatabase();

	public void deleteData(int id);

}
