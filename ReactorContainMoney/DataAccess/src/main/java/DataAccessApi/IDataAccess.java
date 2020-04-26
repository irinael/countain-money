package DataAccessApi;

import java.sql.SQLException;
import java.util.List;

import entitiesForJDBC.Depense;

public interface IDataAccess {
	
	Depense ajouterDepense(double montant) throws SQLException;
	List<Depense> getAll() throws SQLException;

}
