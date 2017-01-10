package exemplu.common.interfaces;

import java.util.List;

import exemplu.functionalitati.stoc.StocModel;

public interface StocDAO {

	public void insertData(StocModel stoc);

	public List<StocModel> readData();

	public void editData(StocModel stoc,String columnName, String columnValue);

	public void updateDatabase();
	
	public void deleteData(StocModel stoc);

}
