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
import entitiesForJDBC.Lieu;

public class DaoJDBC implements IDataAccess {

	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/containmoney";
	static final String USER = "root";
	static final String PWD = "";
	static final String INSERT_QUERY = "INSERT INTO depense (montant, date, id_lieu) VALUES (?, ?, ?)";
	static final int INSERT_MONTANT_PARAM_INDEX = 1;
	static final int INSERT_DATE_PARAM_INDEX = 2;
	static final int INSERT_ID_LIEU_PARAM_INDEX = 3;

	static final String GET_ALL_DEPENSE_QUERY = "SELECT id, montant, date, id_lieu FROM depense ORDER BY date DESC";
	static final String GET_ALL_LIEU_QUERY = "SELECT id, nom FROM lieu";
	static final String GET_LIEU_BY_ID_QUERY = "SELECT id, nom FROM lieu WHERE lieu.id=?";
	static final int SELECT_LIEU_BY_ID_INDEX = 1;
	private static final int GET_LIEU_BY_NAME_INDEX = 1;
	private static final String GET_LIEU_BY_NAME_QUERY = "SELECT id, nom FROM lieu WHERE lieu.nom=?";
	private static final String GET_DEPENSE_BY_LIEU_QUERY = "SELECT id, montant, date, id_lieu FROM depense WHERE id_lieu=?";
	private static final int GET_DEPENSE_BY_LIEU_QUERY_INDEX = 1;



	public Depense ajouterDepense(Depense depense) throws SQLException {

		//Depense dep = new Depense();


		try {
			Class.forName(JDBC_DRIVER);
			try (Connection con = DriverManager.getConnection(DB_URL, USER, PWD);
					PreparedStatement s = con.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS)) {
				s.setDouble(INSERT_MONTANT_PARAM_INDEX, depense.getMontant());


				s.setDate(INSERT_DATE_PARAM_INDEX, java.sql.Date.valueOf(depense.getDate()));
				s.setDouble(INSERT_ID_LIEU_PARAM_INDEX, depense.getIdLieu());

				int insertedId = s.executeUpdate();

			}
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		}

		return depense;
	}

	public List<Depense> getAllDepense() throws SQLException {

		List<Depense> depenses = new ArrayList<Depense>();
		try {
			Class.forName(JDBC_DRIVER);
			try (Connection con = DriverManager.getConnection(DB_URL, USER, PWD);
					PreparedStatement ps = con.prepareStatement(GET_ALL_DEPENSE_QUERY);
					ResultSet rs = ps.executeQuery();) {


				while (rs.next()) {
					Depense dep = new Depense();
					dep.setId(rs.getInt("id"));
					dep.setMontant(rs.getDouble("montant"));
					if (rs.getDate("date") != null) {
						dep.setDate(rs.getDate("date").toLocalDate());}

					dep.setIdLieu(rs.getInt("id_lieu"));
					depenses.add(dep);
				}
			}
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		}

		return depenses;
	}



	@Override
	public List<Lieu> getAllLieu() throws SQLException {
		List<Lieu> lieux = new ArrayList<Lieu>();
		try {
			Class.forName(JDBC_DRIVER);
			try (Connection con = DriverManager.getConnection(DB_URL, USER, PWD);
					PreparedStatement ps = con.prepareStatement(GET_ALL_LIEU_QUERY);
					ResultSet rs = ps.executeQuery();) {


				while (rs.next()) {
					Lieu lieu = new Lieu();
					lieu.setId(rs.getInt("id"));
					lieu.setNom(rs.getString("nom"));
					lieux.add(lieu);
				}}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return lieux;
	}

	@Override
	public Lieu getLieuById(int id) throws SQLException {

		Lieu lieu = new Lieu();

		try {
			Class.forName(JDBC_DRIVER);
			try (Connection con = DriverManager.getConnection(DB_URL, USER, PWD);
					PreparedStatement ps = con.prepareStatement(GET_LIEU_BY_ID_QUERY);) {
				ps.setInt(SELECT_LIEU_BY_ID_INDEX, id);
				try (ResultSet rs = ps.executeQuery()) {


					while (rs.next()) {
						lieu = new Lieu();
						lieu.setId(rs.getInt("id"));
						lieu.setNom(rs.getString("nom"));

					}}}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		return lieu;
	}

	@Override
	public Lieu getLieuByName(String name) throws SQLException {
		Lieu lieu = new Lieu();

		try {

			Class.forName(JDBC_DRIVER);
			try(Connection con = DriverManager.getConnection(DB_URL, USER, PWD);
					PreparedStatement ps = con.prepareStatement(GET_LIEU_BY_NAME_QUERY)) {
				ps.setString(GET_LIEU_BY_NAME_INDEX, name);
				try (ResultSet rs = ps.executeQuery()) {

					while (rs.next()) {
						lieu = new Lieu();
						lieu.setId(rs.getInt("id"));
						lieu.setNom(rs.getString("nom"));

					}}}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		return lieu;
	}

	@Override
	public List<Depense> getDepensesByLieu(Integer lieu) throws SQLException {
		List<Depense> depenses = new ArrayList<Depense>();
		try {
			Class.forName(JDBC_DRIVER);

			try(Connection con = DriverManager.getConnection(DB_URL, USER, PWD);PreparedStatement ps = con.prepareStatement(GET_DEPENSE_BY_LIEU_QUERY))
			{
				ps.setInt(GET_DEPENSE_BY_LIEU_QUERY_INDEX, lieu);
				try (ResultSet rs = ps.executeQuery()) {

					while (rs.next()) {
						Depense depense = new Depense();
						depense.setId(rs.getInt("id"));
						depense.setMontant(Double.parseDouble(rs.getString("montant")));
						depense.setIdLieu(rs.getInt("id_lieu"));
						if (rs.getDate("date") != null) {
							depense.setDate(rs.getDate("date").toLocalDate());
						}
						depenses.add(depense);
						System.out.println(depenses.size());
					}

				}
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return depenses;
	}
}


