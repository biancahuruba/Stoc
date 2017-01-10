package exemplu.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import exemplu.common.interfaces.AngajatiDAO;
import exemplu.common.models.Attribute;
import exemplu.functionalitati.angajati.AngajatiModel;

public class AngajatiDAOImpl implements AngajatiDAO {

	@Override
	public void insertData(AngajatiModel angajat) {
		Connection dbConnection = null;
		Statement stat = null;

		String sql = "INSERT INTO ANGAJATI(NUME, PRENUME, PRODUS, PRET, CANTITATE, COMISION) VALUES('"
				+ angajat.getNume() + "'," + "'" + angajat.getPrenume() + "'" + "," + "'"
				+ angajat.getTabeModel().get(0).getAttribute(0) + "'" + "," + "'"
				+ angajat.getTabeModel().get(0).getAttribute(1) + "'" + ",'"
				+ angajat.getTabeModel().get(0).getAttribute(2) + "'," + "'"
				+ angajat.getTabeModel().get(0).getAttribute(3) + "'" + ");";
		try {
			Class.forName("org.sqlite.JDBC");
			dbConnection = DriverManager.getConnection("jdbc:sqlite:resources/database/test.db");
			dbConnection.setAutoCommit(false);
			System.out.println("Opened database successfully");

			stat = dbConnection.prepareStatement(sql);
			stat.executeUpdate(sql);

			System.out.println("Record is insert into Employees table for employee: " + angajat.getNume() + " "
					+ angajat.getPrenume());
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
	public List<AngajatiModel> readData() {
		Connection dbConnection = null;
		Statement stat = null;
		ResultSet rs = null;

		List<AngajatiModel> list = new ArrayList<>();

		try {
			Class.forName("org.sqlite.JDBC");
			dbConnection = DriverManager.getConnection("jdbc:sqlite:resources/database/test.db");
			dbConnection.setAutoCommit(false);
			System.out.println("Opened database successfully");

			String sql = "SELECT * FROM ANGAJATI;";

			stat = dbConnection.prepareStatement(sql);
			rs = stat.executeQuery(sql);

			Attribute aNume = new Attribute();
			Attribute aPrenume = new Attribute();
			Attribute aProdus = new Attribute();
			Attribute aPret = new Attribute();
			Attribute aCantitate = new Attribute();
			Attribute aComision = new Attribute();

			while (rs.next()) {
				AngajatiModel angajat = new AngajatiModel();
				aNume.setValue(rs.getString("NUME"));
				angajat.setNume(aNume);

				aPrenume.setValue(rs.getString("PRENUME"));
				angajat.setNume(aPrenume);

				aProdus.setValue(rs.getString("PRODUS"));
				angajat.setNume(aProdus);

				aPret.setValue(rs.getString("PRET"));
				angajat.setNume(aPret);

				aPret.setValue(rs.getString("PRET"));
				angajat.setNume(aPret);

				aCantitate.setValue(rs.getString("CANTITATE"));
				angajat.setNume(aCantitate);

				aComision.setValue(rs.getString("COMISION"));
				angajat.setNume(aComision);

				list.add(angajat);

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
	public void editData(AngajatiModel angajat, String columnName, String columnValue) {
		Connection dbConnection = null;
		Statement stat = null;
		try {
			Class.forName("org.sqlite.JDBC");
			dbConnection = DriverManager.getConnection("jdbc:sqlite:resources/database/test.db");
			dbConnection.setAutoCommit(false);
			System.out.println("Opened database successfully");

			String sql = "UPDATE ANGAJATI SET" + columnName + " =" + columnValue + " WHERE NUME='"
					+ angajat.getNume().getValue() + "' AND PRENUME='" + angajat.getPrenume().getValue() + "';";

			stat = dbConnection.prepareStatement(sql);
			stat.executeUpdate(sql);

			System.out.println("Record is edited from Angajati for: " + angajat.getNume().getValue() + " "
					+ angajat.getPrenume().getValue() + ".");

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
	public void deleteData(AngajatiModel angajat) {
		Connection dbConnection = null;
		Statement stat = null;
		try {
			Class.forName("org.sqlite.JDBC");
			dbConnection = DriverManager.getConnection("jdbc:sqlite:resources/database/test.db");
			dbConnection.setAutoCommit(false);
			System.out.println("Opened database successfully");

			String sql = "DELETE FROM ANGAJATI WHERE NUME='" + angajat.getNume().getValue() + "' AND PRENUME='"
					+ angajat.getPrenume().getValue() + "';";

			stat = dbConnection.prepareStatement(sql);
			stat.executeUpdate(sql);

			System.out.println("Record is deleted from Angajati for: " + angajat.getNume().getValue() + " "
					+ angajat.getPrenume().getValue() + ".");

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
