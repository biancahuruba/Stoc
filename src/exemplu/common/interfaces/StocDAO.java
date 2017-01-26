package exemplu.common.interfaces;

import java.util.List;

import exemplu.functionalitati.stoc.StocModel;

public interface StocDAO {

	public void insertData(StocModel stoc);

	public List<StocModel> readData();

	public void editData(String columnName, String columnValue, int id);

	public void deleteData(int id);
	
	public List<StocModel> search(String produsValue, String pretValue);

}
