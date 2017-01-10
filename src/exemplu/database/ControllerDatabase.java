package exemplu.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import exemplu.common.models.Attribute;
import exemplu.functionalitati.angajati.AngajatiDAOImpl;
import exemplu.functionalitati.angajati.AngajatiModel;
import exemplu.functionalitati.angajati.AngajatiRow;

public class ControllerDatabase {
	private static final String DATABASE_LOCATION = "jdbc:sqlite:Stoc/resources/database/test.db";

	public static void main(String args[]) {
		 createTables();
		// insertData();
		// readData();
//		AngajatiModel angajat = new AngajatiModel();
//		AngajatiRow angajatiRow = new AngajatiRow();
//		AngajatiDAOImpl dao = new AngajatiDAOImpl();
//
//		Attribute nume = new Attribute();
//		Attribute prenume = new Attribute();
//
//		List<AngajatiRow> list = new ArrayList<>();
//
//		nume.setValue("Andrica");
//		prenume.setValue("Andrei");
//
//		// Produsul
//		angajatiRow.getAttribute(0).setValue("cana");
//		// Pretul
//		angajatiRow.getAttribute(1).setValue("14");
//		// Cantitate
//		angajatiRow.getAttribute(2).setValue("4");
//		// Comision
//		angajatiRow.getAttribute(3).setValue("0.6");
//
//		list.add(angajatiRow);
//
//		angajat.setNume(nume);
//		angajat.setPrenume(prenume);
//		angajat.setTabelModel(list);
//
//		//dao.editData(angajat,"PRENUME", "Ilie",3);
//		 dao.insertData(angajat);

//		int row = dao.getDBRows();
//		int j = 1;
//		List<AngajatiModel> lAngajat = new ArrayList<AngajatiModel>();
//		lAngajat = dao.readData();
//		while (j <= row) {
//			for (AngajatiModel angajatFromL : lAngajat) {
//				System.out.print(j + ".");
//				System.out.println(angajatFromL.toString());
//				j++;
//			}
//		}
		
		//dao.deleteData(4);
		
	

	}

	public static void createTables() {
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection(DATABASE_LOCATION);
			System.out.println("Opened database successfully");

			stmt = c.createStatement();

			String sql = "CREATE TABLE ANGAJATI" + "(ID INTEGER PRIMARY KEY  AUTOINCREMENT   NOT NULL," + "NUME   VARCHAR(40),"
					+ "PRENUME     VARCHAR(40)," + "PRODUS    VARCHAR(40)," + "PRET   VARCHAR(40),"
					+ "CANTITATE   VARCHAR(40)," + "COMISION   VARCHAR(40))";
			stmt.executeUpdate(sql);

			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Table created successfully");

	}

	public static void insertData() {
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection(DATABASE_LOCATION);
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");

			stmt = c.createStatement();
			String sql = "INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY) "
					+ "VALUES (1, 'Paul', 32, 'California', 20000.00 );";
			stmt.executeUpdate(sql);

			sql = "INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY) " + "VALUES (2, 'Allen', 25, 'Texas', 15000.00 );";
			stmt.executeUpdate(sql);

			sql = "INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY) " + "VALUES (3, 'Teddy', 23, 'Norway', 20000.00 );";
			stmt.executeUpdate(sql);

			sql = "INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY) "
					+ "VALUES (4, 'Mark', 25, 'Rich-Mond ', 65000.00 );";
			stmt.executeUpdate(sql);

			sql = "INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY) " + "VALUES (5, 'Alex', 25, 'Norway ', 65000.00 );";
			stmt.executeUpdate(sql);

			stmt.close();
			c.commit();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Records created successfully");
	}

	public static void readData() {
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection(DATABASE_LOCATION);
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");

			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM COMPANY;");
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				int age = rs.getInt("age");
				String address = rs.getString("address");
				float salary = rs.getFloat("salary");
				System.out.println("ID = " + id);
				System.out.println("NAME = " + name);
				System.out.println("AGE = " + age);
				System.out.println("ADDRESS = " + address);
				System.out.println("SALARY = " + salary);
				System.out.println();
			}
			rs.close();
			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Operation done successfully");
	}
}
