import org.junit.Test;

import DataAccessApi.IDataAccess;
import entitiesForJDBC.Depense;
import jdbc.DaoJDBC;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

import org.junit.Assert;


public class TestGetAllDepenses {
	private static IDataAccess dao = new DaoJDBC();
	private static final int EXPECTED_NB_DEPENSES = 9;
	
	@Test
	public void testNbDepenses() {
		try {
			Assert.assertEquals(EXPECTED_NB_DEPENSES, dao.getAllDepense().size());
		} catch (SQLException e) {
			
			e.printStackTrace();
		}

	}
	@Test
	public void testAttributsDepenses() {
		try {
			for (Depense d : dao.getAllDepense()) {
				
				Assert.assertNotNull(d.getDate());
				Assert.assertNotNull(d.getMontant());
				Assert.assertNotNull(d.getIdLieu());
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	@Test
	public void testTypeAttribut() {
		try {
			for (Depense d : dao.getAllDepense()) {
				
				Object actual = new Object();
				actual = d.getMontant();
				Assert.assertTrue(actual instanceof Double);
				
				Assert.assertTrue(d.getDate() instanceof LocalDate);
				
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
}
