package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import DataAccessApi.IDataAccess;
import entitiesForJDBC.Depense;

public class DaoJDBC implements IDataAccess {

	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/containmoney";
	static final String USER = "root";
	static final String PWD = "";
	static final String INSERT_QUERY = "INSERT INTO depense (montant) VALUES (?)";
	static final int INSERT_PARAM_INDEX = 1;
	static final String GET_ALL_QUERY = "SELECT id, montant FROM depense";


	public Depense ajouterDepense(double montant) throws SQLException {

		Depense dep = new Depense();
		Connection con = null;

		try {
			Class.forName(JDBC_DRIVER);
			con = DriverManager.getConnection(DB_URL, USER, PWD);
			PreparedStatement s = con.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS);
			s.setDouble(INSERT_PARAM_INDEX, montant);
			int insertedId = s.executeUpdate();

			dep.setId(insertedId);
			dep.setMontant(montant);

		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		}

		con.close();
		return dep;
	}

	public List<Depense> getAll() throws SQLException {

		List<Depense> depenses = new ArrayList<Depense>();
		Connection conn = null;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PWD);
			PreparedStatement ps = conn.prepareStatement(GET_ALL_QUERY);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				Depense dep = new Depense();
				dep.setId(rs.getInt("id"));
				dep.setMontant(rs.getInt("montant"));
				depenses.add(dep);
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return depenses;
	}
}


