package app;
import java.sql.SQLException;
import java.time.ZoneId;
import java.util.Date;

import DataAccessApi.IDataAccess;
import entitiesForJDBC.Depense;
import entitiesForJDBC.Lieu;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.util.StringConverter;
import jdbc.DaoJDBC;

@SuppressWarnings("restriction")
public class DepenseForm extends VBox {

	private Label lblDepense = new Label("Ajouter une dépense ?");
	private TextField montant = new TextField();
	private ChoiceBox<Lieu> lieu = new ChoiceBox<>();
	private DatePicker date = new DatePicker();
	private Button addDepense = new Button("Allez, encore une !");
	private IDataAccess dao = new DaoJDBC();
	private Label message = new Label();
	private Label message2 = new Label();
	private Depense depense = new Depense();
	
	public DepenseForm() {

		//ajout et agencement des éléments

		this.getChildren().addAll(lblDepense, montant, lieu, date, addDepense, message, message2);
		this.setPadding(new Insets(100, 60, 30, 60));
		this.setSpacing(20);

		//text field
		montant.setPromptText("Encore une ?");
		montant.setMaxWidth(200);
		
		//choice box
		try {
			lieu.setItems(FXCollections.observableArrayList(dao.getAllLieu()));
			
			lieu.setConverter(new StringConverter<Lieu>() {
				
				@Override
				public String toString(Lieu object) {
					// TODO Auto-generated method stub
					return object.getNom();
				}
				
				@Override
				public Lieu fromString(String string) {
					// TODO Auto-generated method stub
					return lieu.getItems().stream().filter(ap ->
					ap.getNom().equals(string)).findFirst().orElse(null);
				}
			});
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		lieu.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Lieu>() {

			@Override
			public void changed(ObservableValue<? extends Lieu> observable, Lieu oldValue, Lieu newValue) {
				depense.setIdLieu(lieu.getValue().getId());
			}
			
		});


		addDepense.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				message.setText("");
				
				
				try {
					if (!montant.getText().equals("") && montant.getText().matches("\\d+")) {
						depense.setMontant(Double.parseDouble(montant.getText()));
						depense.setDate(date.getValue());
						//depense.setIdLieu(lieu.getSelectionModel().);
						dao.ajouterDepense(depense);
						FrontPage root = (FrontPage)DepenseForm.this.getScene().getRoot();
						root.getDepenseList().getTable().setItems(FXCollections.observableArrayList(dao.getAllDepense()));
					}
					else {
						message.setText("Rentre un chiffre enfin!");
					}

					montant.clear();

				} catch (SQLException e) {
e.printStackTrace();
					//message.setText("Quelque chose ne va pas avec ta requête, faut revoir ton code !");
				}
				catch (NumberFormatException n) {
					n.printStackTrace();
				}
				

			}


		});			

	}




	public Label getLblDepense() {
		return lblDepense;
	}




	public void setLblDepense(Label lblDepense) {
		this.lblDepense = lblDepense;
	}




	public TextField getMontant() {
		return montant;
	}




	public void setMontant(TextField montant) {
		this.montant = montant;
	}




	




	public ChoiceBox<Lieu> getLieu() {
		return lieu;
	}




	public void setLieu(ChoiceBox<Lieu> lieu) {
		this.lieu = lieu;
	}




	public DatePicker getDate() {
		return date;
	}




	public void setDate(DatePicker date) {
		this.date = date;
	}




	public Button getAddDepense() {
		return addDepense;
	}




	public void setAddDepense(Button addDepense) {
		this.addDepense = addDepense;
	}




	public IDataAccess getDao() {
		return dao;
	}




	public void setDao(IDataAccess dao) {
		this.dao = dao;
	}




	public Label getMessage() {
		return message;
	}




	public void setMessage(Label message) {
		this.message = message;
	}




	public Label getMessage2() {
		return message2;
	}




	public void setMessage2(Label message2) {
		this.message2 = message2;
	}




	public Depense getDepense() {
		return depense;
	}




	public void setDepense(Depense depense) {
		this.depense = depense;
	}


	





}
