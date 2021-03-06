package dataAccessApi;

import java.sql.SQLException;
import java.util.List;

import entitiesJDBC.Depense;
import entitiesJDBC.Lieu;


public interface IDataAccess {
	
	Depense ajouterDepense(Depense depense) throws SQLException;
	List<Depense> getAllDepense() throws SQLException;

	List<Lieu> getAllLieu() throws SQLException;
	Lieu getLieuById(int id) throws SQLException;
	
	Lieu getLieuByName(String name) throws SQLException;
	List<Depense> getDepensesByLieu(Integer lieu) throws SQLException;
}
