import java.sql.SQLException;

import DataAccessApi.IDataAccess;
import entitiesForJDBC.Depense;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import jdbc.DaoJDBC;
import javafx.scene.control.cell.PropertyValueFactory;

@SuppressWarnings("restriction")
public class DepenseListe extends AnchorPane {

	private TableView<Depense> table = new TableView<Depense>();
	private TableColumn<Depense, Integer> colId = new TableColumn<>("ID");
	private TableColumn<Depense, Double> colMontant = new TableColumn<>("Montant");
	private ObservableList<Depense> depenses;
	private IDataAccess dao = new DaoJDBC();
	
	public DepenseListe() {
		try {
			depenses = FXCollections.observableArrayList(dao.getAll());
			table = new TableView<>(depenses);
			
			colId.setCellValueFactory(new PropertyValueFactory<Depense, Integer>("id"));
			colMontant.setCellValueFactory(new PropertyValueFactory<Depense, Double>("montant"));
			
			table.getColumns().addAll(colId, colMontant);
			
			this.getChildren().add(table);
		
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	
	
	
}
