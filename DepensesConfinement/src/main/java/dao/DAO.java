package dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class DAO {

	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/containmoney";
	static final String USER = "root";
	static final String PWD = "";


	public void ajouterDepense() throws SQLException {

		Connection con = null;
		
		try {
			Class.forName(JDBC_DRIVER);
			con = DriverManager.getConnection(DB_URL, USER, PWD);
			Statement s = con.createStatement();
			s.executeUpdate("INSERT INTO depense (id, montant) VALUES (1, 200)");
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		}

		con.close();

	}
}
