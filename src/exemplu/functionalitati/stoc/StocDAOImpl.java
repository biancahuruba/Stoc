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
					+ stoc.getData().getValue() + "','" + stoc.getTableList().get(0).getAttribute(0).getValue() + "'"
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
			try {
				if (stat != null) {
					stat.close();
				}
				if (dbConnection != null) {
					dbConnection.close();
				}

			} catch (SQLException e) {
				e.printStackTrace();
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

				Attribute aCod = new Attribute();
				aCod.setValue(rs.getString("COD"));
				stoc.setCod(aCod);

				Attribute aProdus = new Attribute();
				aProdus.setValue(rs.getString("PRODUS"));
				stoc.setProdus(aProdus);

				Attribute aCategorie = new Attribute();
				aCategorie.setValue(rs.getString("CATEGORIE"));
				stoc.setCategorie(aCategorie);

				Attribute aPret = new Attribute();
				aPret.setValue(rs.getString("PRET"));
				stoc.setPret(aPret);

				Attribute aData = new Attribute();
				aData.setValue(rs.getString("DATA"));
				stoc.setData(aData);

				DistributieMagazinModel distributie = new DistributieMagazinModel();
				distributie.getAttribute(0).setValue(rs.getString("MAGAZIN"));
				distributie.getAttribute(1).setValue(rs.getString("LOCALITATE"));
				distributie.getAttribute(2).setValue(rs.getString("CANTITATE"));

				List<DistributieMagazinModel> row = new ArrayList<>();
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
			try {
				if (stat != null) {
					stat.close();
				}
				if (dbConnection != null) {
					dbConnection.close();
				}
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
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
			try {
				if (stat != null) {
					stat.close();
				}
				if (dbConnection != null) {
					dbConnection.close();
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
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
			try {
				if (stat != null) {
					stat.close();
				}
				if (dbConnection != null) {
					dbConnection.close();
				}

			} catch (SQLException e) {
				e.printStackTrace();
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
			try {
				if (stat != null) {
					stat.close();
				}
				if (dbConnection != null) {
					dbConnection.close();
				}
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public List<StocModel> search(String produsValue, String pretValue) {
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
				if ((rs.getString("PRODUS").equals(produsValue) && (rs.getString("PRET").equals(pretValue)))) {
					StocModel stoc = new StocModel();

					Attribute aCod = new Attribute();
					aCod.setValue(rs.getString("COD"));
					stoc.setCod(aCod);

					Attribute aProdus = new Attribute();
					aProdus.setValue(rs.getString("PRODUS"));
					stoc.setProdus(aProdus);

					Attribute aCategorie = new Attribute();
					aCategorie.setValue(rs.getString("CATEGORIE"));
					stoc.setCategorie(aCategorie);

					Attribute aPret = new Attribute();
					aPret.setValue(rs.getString("PRET"));
					stoc.setPret(aPret);

					Attribute aData = new Attribute();
					aData.setValue(rs.getString("DATA"));
					stoc.setData(aData);

					DistributieMagazinModel distributie = new DistributieMagazinModel();
					distributie.getAttribute(0).setValue(rs.getString("MAGAZIN"));
					distributie.getAttribute(1).setValue(rs.getString("LOCALITATE"));
					distributie.getAttribute(2).setValue(rs.getString("CANTITATE"));

					List<DistributieMagazinModel> row = new ArrayList<>();
					row.add(distributie);

					stoc.setTableList(row);

					list.add(stoc);
				}
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stat != null) {
					stat.close();
				}
				if (dbConnection != null) {
					dbConnection.close();
				}
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}