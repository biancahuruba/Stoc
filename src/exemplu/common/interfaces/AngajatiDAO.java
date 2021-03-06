package exemplu.common.interfaces;

import java.util.List;

import exemplu.functionalitati.angajati.AngajatiModel;

public interface AngajatiDAO {

	public void insertData(AngajatiModel angajat);

	public List<AngajatiModel> readData();

	public void editData(String columnName, String columnValue, int id);

	public void deleteData(int id);

	public List<AngajatiModel> search(String numeValue, String prenumeValue);

}
