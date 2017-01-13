package exemplu.functionalitati.stoc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import exemplu.common.interfaces.StocDAO;
import exemplu.common.models.Attribute;

public class StocDAOImpl implements StocDAO {

	@Override
	public void insertData(StocModel stoc) {
		Connection dbConnection = null;
		Statement stat = null;
		try {
			Class.forName("org.sqlite.JDBC");
			dbConnection = DriverManager.getConnection("jdbc:sqlite:Stoc/resources/database/test.db");
			dbConnection.setAutoCommit(false);
			System.out.println("Opened database successfully");

			stat = dbConnection.createStatement();
			
			String sql = "INSERT INTO STOC (PRODUS,CATEGORIE,PRET,COD,DATA, MAGAZIN, LOCALITATE, CANTITATE) VALUES('"
					+ stoc.getProdus().getValue() + "'" + "," + "'" + stoc.getCategorie().getValue() + "'" + "," + "'"
					+ stoc.getPret().getValue() + "'" + ",'" + stoc.getCod().getValue() + "','"
					+ "data" + "','" + stoc.getTableList().get(0).getAttribute(0).getValue() + "'"
					+ "," + "'" + stoc.getTableList().get(0).getAttribute(1).getValue() + "'" + ",'"
					+ stoc.getTableList().get(0).getAttribute(2).getValue() + "'" + ");";
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

		final List<StocModel> list = new ArrayList<>();

		try {
			Class.forName("org.sqlite.JDBC");
			dbConnection = DriverManager.getConnection("jdbc:sqlite:Stoc/resources/database/test.db");
			dbConnection.setAutoCommit(false);
			System.out.println("Opened database successfully");

			stat = dbConnection.createStatement();
			String sql = "SELECT * FROM STOC;";
			rs = stat.executeQuery(sql);

			while (rs.next()) {
				StocModel stoc = new StocModel();
				DistributieMagazinModel distributie = new DistributieMagazinModel();
				List<DistributieMagazinModel> row = new ArrayList<>();

				Attribute aCod = new Attribute();
				Attribute aProdus = new Attribute();
				Attribute aCategorie = new Attribute();
				Attribute aPret = new Attribute();
				Attribute aData = new Attribute();

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

				distributie.getAttribute(0).setValue(rs.getString("MAGAZIN"));
				distributie.getAttribute(1).setValue(rs.getString("LOCALITATE"));
				distributie.getAttribute(2).setValue(rs.getString("CANTITATE"));
				row.add(distributie);

				stoc.setTableList(row);

				list.add(stoc);

			}
			return list;
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
		return null;
	}

	@Override
	public void editData(String columnName, String columnValue, int id) {
		Connection dbConnection = null;
		Statement stat = null;

		try {
			Class.forName("org.sqlite.JDBC");
			dbConnection = DriverManager.getConnection("jdbc:sqlite:Stoc/resources/database/test.db");
			dbConnection.setAutoCommit(false);
			System.out.println("Opened database successfully");

			stat = dbConnection.createStatement();
			String sql = "UPDATE STOC SET " + columnName + " ='" + columnValue + "' WHERE ID=" + id + ";";

			stat.executeUpdate(sql);
			dbConnection.commit();

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
	public void deleteData(int id) {

		Connection dbConnection = null;
		Statement stat = null;

		try {
			Class.forName("org.sqlite.JDBC");
			dbConnection = DriverManager.getConnection("jdbc:sqlite:Stoc/resources/database/test.db");
			dbConnection.setAutoCommit(false);
			System.out.println("Opened database successfully");

			stat = dbConnection.createStatement();
			String sql = "DELETE FROM STOC WHERE ID=" + id + ";";

			stat.executeUpdate(sql);
			dbConnection.commit();

			System.out.println("Record is deleted from Stoc.  ");

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

	public List<Integer> listOfId() {
		Connection dbConnection = null;
		Statement stat = null;
		ResultSet rs = null;

		List<Integer> list = new ArrayList<>();

		try {
			Class.forName("org.sqlite.JDBC");
			dbConnection = DriverManager.getConnection("jdbc:sqlite:Stoc/resources/database/test.db");
			dbConnection.setAutoCommit(false);
			System.out.println("Opened database successfully");
			stat = dbConnection.createStatement();
			String sql = "SELECT * FROM STOC;";

			rs = stat.executeQuery(sql);

			while (rs.next()) {

				int id = Integer.parseInt(rs.getString("ID"));
				list.add(id);
			}
			return list;
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
		return null;
	}

}
