package exemplu.common.interfaces;

import java.util.List;

import exemplu.functionalitati.angajati.AngajatiModel;

public interface StocDAO {

	public void insertData(AngajatiModel angajat);

	public List<AngajatiModel> readData();

	public void editData(AngajatiModel angajat,String columnName, String columnValue);

	public void updateDatabase();
	
	public void deleteData(AngajatiModel angajat);

}
