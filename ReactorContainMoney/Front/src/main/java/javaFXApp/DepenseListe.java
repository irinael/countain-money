package javaFXApp;
import java.sql.SQLException;

import dataAccessApi.IDataAccess;
import entitiesJDBC.Depense;
import entitiesJDBC.Lieu;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.util.Callback;
import jdbcImpl.DaoJDBC;

public class DepenseListe extends Pane {

	private TableView<Depense> table = new TableView<Depense>();
	//private TableColumn<Depense, Integer> colId = new TableColumn<>("ID");
	private TableColumn<Depense, Double> colMontant = new TableColumn<>("Montant");
	private TableColumn<Depense, String> colLieu = new TableColumn<>("Lieu");
	private TableColumn<Depense, String> colDate = new TableColumn<>("Date");

	private ObservableList<Depense> depenses;
	private IDataAccess dao = new DaoJDBC();


	public DepenseListe() {
		try {
			

			depenses = FXCollections.observableArrayList(dao.getAllDepense());
			table = new TableView<>(depenses);
			table.setFixedCellSize(45.0);
			
			

			colMontant.setCellValueFactory(new PropertyValueFactory<Depense, Double>("montant"));
			colMontant.setResizable(false);
			colMontant.prefWidthProperty().bind(table.widthProperty().multiply(0.33));
			
			
			colDate.setCellValueFactory(new PropertyValueFactory<Depense, String>("date"));
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
						
						e.printStackTrace();
					}
					return new SimpleStringProperty(lieu.getNom());
				}
			});

			table.getColumns().addAll(colMontant, colLieu, colDate);
			

			this.getChildren().add(table);
			this.setPrefWidth(450);
			table.setPrefWidth(450);
			table.setMinHeight(460);
			table.prefHeightProperty().bind(new SimpleIntegerProperty(depenses.size()).multiply(table.getFixedCellSize()).add(30));

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












	public TableColumn<Depense, String> getColLieu() {
		return colLieu;
	}












	public void setColLieu(TableColumn<Depense, String> colLieu) {
		this.colLieu = colLieu;
	}












	public TableColumn<Depense, String> getColDate() {
		return colDate;
	}












	public void setColDate(TableColumn<Depense, String> colDate) {
		this.colDate = colDate;
	}




}
