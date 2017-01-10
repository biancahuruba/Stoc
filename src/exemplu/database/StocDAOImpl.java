package exemplu.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import exemplu.common.interfaces.StocDAO;
import exemplu.common.models.Attribute;
import exemplu.functionalitati.stoc.StocModel;

public class StocDAOImpl implements StocDAO {

	@Override
	public void insertData(StocModel stoc) {
		Connection dbConnection = null;
		Statement stat = null;

		String sql = "INSERT INTO STOC(COD,PRODUS,CATEGORIE,PRET,DATA) VALUES('" + stoc.getCod().getValue() + "'," + "'"
				+ stoc.getProdus().getValue() + "'" + "," + "'" + stoc.getCategorie().getValue() + "'" + "," + "'"
				+ stoc.getPret().getValue() + "'" + ",'" + stoc.getData().getValue() + "'," + ");";
		try {
			Class.forName("org.sqlite.JDBC");
			dbConnection = DriverManager.getConnection("jdbc:sqlite:Stoc/resources/database/test.db");
			dbConnection.setAutoCommit(false);
			System.out.println("Opened database successfully");

			stat = dbConnection.prepareStatement(sql);
			stat.executeUpdate(sql);

			System.out.println("Record is insert into Stoc table.");
			dbConnection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (stat != null) {
				try {
					stat.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (dbConnection != null) {
				try {
					dbConnection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public List<StocModel> readData() {
		Connection dbConnection = null;
		Statement stat = null;
		ResultSet rs = null;

		List<StocModel> list = new ArrayList<>();

		try {
			Class.forName("org.sqlite.JDBC");
			dbConnection = DriverManager.getConnection("jdbc:sqlite:Stoc/resources/database/test.db");
			dbConnection.setAutoCommit(false);
			System.out.println("Opened database successfully");

			String sql = "SELECT * FROM STOC;";

			stat = dbConnection.prepareStatement(sql);
			rs = stat.executeQuery(sql);

			Attribute aCod = new Attribute();
			Attribute aProdus = new Attribute();
			Attribute aCategorie = new Attribute();
			Attribute aPret = new Attribute();
			Attribute aData = new Attribute();

			while (rs.next()) {
				StocModel stoc = new StocModel();

				aCod.setValue(rs.getString("COD"));
				stoc.setCod(aCod);

				aProdus.setValue(rs.getString("PRODUS"));
				stoc.setProdus(aProdus);

				aCategorie.setValue(rs.getString("CATEGORIE"));
				stoc.setCategorie(aCategorie);

				aPret.setValue(rs.getString("PRET"));
				stoc.setPret(aPret);

				aData.setValue(rs.getString("DATA"));
				stoc.setData(aData);

				list.add(stoc);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (stat != null) {
				try {
					stat.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (dbConnection != null) {
				try {
					dbConnection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return list;
	}

	@Override
	public void editData(StocModel stoc, String columnName, String columnValue) {
		Connection dbConnection = null;
		Statement stat = null;
		try {
			Class.forName("org.sqlite.JDBC");
			dbConnection = DriverManager.getConnection("jdbc:sqlite:Stoc/resources/database/test.db");
			dbConnection.setAutoCommit(false);
			System.out.println("Opened database successfully");

			String sql = "UPDATE STOC SET" + columnName.toUpperCase() + " =" + columnValue.toUpperCase()
					+ " WHERE COD='" + stoc.getCod().getValue() + "';";

			stat = dbConnection.prepareStatement(sql);
			stat.executeUpdate(sql);

			System.out.println("Record is edited for Stoc.");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (stat != null) {
				try {
					stat.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (dbConnection != null) {
				try {
					dbConnection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public void updateDatabase() {

	}

	@Override
	public void deleteData(StocModel stoc) {

		Connection dbConnection = null;
		Statement stat = null;
		try {
			Class.forName("org.sqlite.JDBC");
			dbConnection = DriverManager.getConnection("jdbc:sqlite:Stoc/resources/database/test.db");
			dbConnection.setAutoCommit(false);
			System.out.println("Opened database successfully");

			String sql = "DELETE FROM STOC WHERE COD='" + stoc.getCod().getValue() + "';";

			stat = dbConnection.prepareStatement(sql);
			stat.executeUpdate(sql);

			System.out.println("Record is deleted from Stoc for code  " + stoc.getCod().getValue() + ".");

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (stat != null) {
				try {
					stat.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (dbConnection != null) {
				try {
					dbConnection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
