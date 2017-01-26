package exemplu.functionalitati.angajati;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import exemplu.common.interfaces.AngajatiDAO;
import exemplu.common.models.Attribute;

public class AngajatiDAOImpl implements AngajatiDAO {

	@Override
	public void insertData(AngajatiModel angajat) {
		Connection dbConnection = null;
		Statement stat = null;

		try {
			Class.forName("org.sqlite.JDBC");
			dbConnection = DriverManager.getConnection("jdbc:sqlite:Stoc/resources/database/test.db");
			dbConnection.setAutoCommit(false);
			System.out.println("Opened database successfully");

			stat = dbConnection.createStatement();

			String sql = "INSERT INTO ANGAJATI(NUME, PRENUME, PRODUS, PRET, CANTITATE, COMISION) VALUES('"
					+ angajat.getNume().getValue() + "'," + "'" + angajat.getPrenume().getValue() + "'" + "," + "'"
					+ angajat.getTabeModel().get(0).getAttribute(0).getValue() + "'" + "," + "'"
					+ angajat.getTabeModel().get(0).getAttribute(1).getValue() + "'" + ",'"
					+ angajat.getTabeModel().get(0).getAttribute(2).getValue() + "'," + "'"
					+ angajat.getTabeModel().get(0).getAttribute(3).getValue() + "'" + ");";
			stat.executeUpdate(sql);

			System.out.println("Record is insert into Employees table for employee: " + angajat.getNume() + " "
					+ angajat.getPrenume());
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
	public List<AngajatiModel> readData() {
		Connection dbConnection = null;
		Statement stat = null;
		ResultSet rs = null;

		List<AngajatiModel> list = new ArrayList<>();

		try {
			Class.forName("org.sqlite.JDBC");
			dbConnection = DriverManager.getConnection("jdbc:sqlite:Stoc/resources/database/test.db");
			dbConnection.setAutoCommit(false);
			System.out.println("Opened database successfully");

			stat = dbConnection.createStatement();

			String sql = "SELECT * FROM ANGAJATI;";

			rs = stat.executeQuery(sql);
			while (rs.next()) {
				AngajatiModel angajat = new AngajatiModel();

				Attribute aNume = new Attribute();
				aNume.setValue(rs.getString("NUME"));
				angajat.setNume(aNume);

				Attribute aPrenume = new Attribute();
				aPrenume.setValue(rs.getString("PRENUME"));
				angajat.setPrenume(aPrenume);

				AngajatiRow angajatiRow = new AngajatiRow();
				angajatiRow.getAttribute(0).setValue(rs.getString("PRODUS"));
				angajatiRow.getAttribute(1).setValue(rs.getString("PRET"));
				angajatiRow.getAttribute(2).setValue(rs.getString("CANTITATE"));
				angajatiRow.getAttribute(3).setValue(rs.getString("COMISION"));

				List<AngajatiRow> tabelModelAngajati = new ArrayList<>();
				tabelModelAngajati.add(angajatiRow);

				angajat.setTabelModel(tabelModelAngajati);

				list.add(angajat);
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

			String sql = "UPDATE ANGAJATI" + " SET " + columnName + "='" + columnValue + "' WHERE ID=" + id + ";";
			stat.executeUpdate(sql);

			dbConnection.commit();

			System.out.println("Record is edited.");
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

			String sql = "DELETE FROM ANGAJATI WHERE ID=" + id + "";
			stat.executeUpdate(sql);

			System.out.println("Record is deleted from Angajati.");

			dbConnection.commit();
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

			String sql = "SELECT * FROM ANGAJATI;";

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
	public List<AngajatiModel> search(String numeValue, String prenumeValue) {
		Connection dbConnection = null;
		Statement stat = null;
		ResultSet rs = null;

		List<AngajatiModel> list = new ArrayList<>();

		try {
			Class.forName("org.sqlite.JDBC");
			dbConnection = DriverManager.getConnection("jdbc:sqlite:Stoc/resources/database/test.db");
			dbConnection.setAutoCommit(false);
			System.out.println("Opened database successfully");

			stat = dbConnection.createStatement();

			String sql = "SELECT * FROM ANGAJATI;";

			rs = stat.executeQuery(sql);
			while (rs.next()) {
				if ((rs.getString("NUME").equals(numeValue) && rs.getString("PRENUME").equals(prenumeValue))) {
					AngajatiModel angajat = new AngajatiModel();

					Attribute aNume = new Attribute();
					aNume.setValue(rs.getString("NUME"));
					angajat.setNume(aNume);

					Attribute aPrenume = new Attribute();
					aPrenume.setValue(rs.getString("PRENUME"));
					angajat.setPrenume(aPrenume);

					AngajatiRow angajatiRow = new AngajatiRow();
					angajatiRow.getAttribute(0).setValue(rs.getString("PRODUS"));
					angajatiRow.getAttribute(1).setValue(rs.getString("PRET"));
					angajatiRow.getAttribute(2).setValue(rs.getString("CANTITATE"));
					angajatiRow.getAttribute(3).setValue(rs.getString("COMISION"));

					List<AngajatiRow> tabelModel = new ArrayList<>();
					tabelModel.add(angajatiRow);

					angajat.setTabelModel(tabelModel);

					list.add(angajat);
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
