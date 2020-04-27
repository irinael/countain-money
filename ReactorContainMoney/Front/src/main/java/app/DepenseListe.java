package app;
import java.sql.SQLException;
import java.time.LocalDate;

import DataAccessApi.IDataAccess;
import entitiesForJDBC.Depense;
import entitiesForJDBC.Lieu;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.util.Callback;
import jdbc.DaoJDBC;
import javafx.beans.property.SimpleStringProperty;

@SuppressWarnings("restriction")
public class DepenseListe extends Pane {

	private TableView<Depense> table = new TableView<Depense>();
	//private TableColumn<Depense, Integer> colId = new TableColumn<>("ID");
	private TableColumn<Depense, Double> colMontant = new TableColumn<>("Montant");
	private TableColumn<Depense, String> colLieu = new TableColumn<>("Lieu");
	private TableColumn<Depense, LocalDate> colDate = new TableColumn<>("Date");

	private ObservableList<Depense> depenses;
	private IDataAccess dao = new DaoJDBC();

	public DepenseListe() {
		try {
			

			depenses = FXCollections.observableArrayList(dao.getAllDepense());
			table = new TableView<>(depenses);
			table.setFixedCellSize(45.0);
			
			

			//colId.setCellValueFactory(new PropertyValueFactory<Depense, Integer>("id"));

			colMontant.setCellValueFactory(new PropertyValueFactory<Depense, Double>("montant"));
			colMontant.setResizable(false);
			colMontant.prefWidthProperty().bind(table.widthProperty().multiply(0.33));

			colDate.setCellValueFactory(new PropertyValueFactory<Depense, LocalDate>("date"));
			colDate.setResizable(false);
			colDate.prefWidthProperty().bind(table.widthProperty().multiply(0.33));

			colLieu.setResizable(false);
			colLieu.prefWidthProperty().bind(table.widthProperty().multiply(0.33));
			colLieu.setCellValueFactory(new Callback<CellDataFeatures<Depense,String>,ObservableValue<String>>(){
				

				@Override
				public ObservableValue<String> call(CellDataFeatures<Depense, String> param) {
					Lieu lieu = new Lieu();
					try {
						lieu = dao.getLieuById(param.getValue().getIdLieu());

					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					return new SimpleStringProperty(lieu.getNom());
				}
			});

			table.getColumns().addAll(colMontant, colLieu, colDate);
			

			this.getChildren().add(table);
			this.setPrefWidth(400);
			table.setPrefWidth(400);
			
			
		} catch (SQLException e) {

			e.printStackTrace();
		}




	}












	public TableView<Depense> getTable() {
		return table;
	}

	public void setTable(TableView<Depense> table) {
		this.table = table;
	}

	public TableColumn<Depense, Double> getColMontant() {
		return colMontant;
	}

	public void setColMontant(TableColumn<Depense, Double> colMontant) {
		this.colMontant = colMontant;
	}

	public ObservableList<Depense> getDepenses() {
		return depenses;
	}

	public void setDepenses(ObservableList<Depense> depenses) {
		this.depenses = depenses;
	}

	public IDataAccess getDao() {
		return dao;
	}

	public void setDao(IDataAccess dao) {
		this.dao = dao;
	}




}
