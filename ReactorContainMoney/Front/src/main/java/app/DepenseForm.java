package app;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import DataAccessApi.IDataAccess;
import entitiesForJDBC.Depense;
import entitiesForJDBC.Lieu;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import jdbc.DaoJDBC;

@SuppressWarnings("restriction")
public class DepenseForm extends GridPane {

	private static final String CB_LABEL = "Où ça ?";
	private Label lblTotal = new Label("");
	private Label lblDepense = new Label("Ajouter une dépense ?");
	private TextField txtMontant = new TextField();
	private ChoiceBox<String> cbLieux = new ChoiceBox<>();
	private DatePicker dpDate = new DatePicker();
	private Button btnAdd = new Button("Allez, encore une !");
	private IDataAccess dao = new DaoJDBC();
	private Label messageMontant = new Label();
	private Label messageLieu = new Label();
	private Label messageDate = new Label();
	private Depense depense = new Depense();
	private List<String> lieuxObservableList = new ArrayList<String>();;
	private ObservableList<String> lieuxStringsList ;
	private String dateFormatPattern = "dd/MM/yyyy";
	private DateTimeFormatter myFormat = DateTimeFormatter.ofPattern(dateFormatPattern);
	private Double totalValue = 0.0;

	public DepenseForm() {

		//ajout et agencement des éléments
		this.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%, #00dbde, #fc00ff);");
		//this.getChildren().addAll(lblDepense, txtMontant, cbLieux, messageLieu, dpDate, btnAdd, message);

		this.addRow(1, lblDepense);
		this.addRow(2, txtMontant);
		this.addRow(3, messageMontant);
		this.addRow(4, cbLieux);
		this.addRow(5, messageLieu);
		this.addRow(6, dpDate);
		this.addRow(7, messageDate);
		this.addRow(8, btnAdd);
		this.addRow(9, lblTotal);
		this.setPadding(new Insets(100, 0, 30, 60));
		this.setVgap(5);
		this.setHgap(20);


		//messagesErreur
		messageMontant.setTextFill(Color.RED);
		messageLieu.setTextFill(Color.RED);

		//lbltotal
		lblTotal.setStyle("-fx-font-family: \"Poppins-Bold");
		lblTotal.setStyle("-fx-font-size: 23px;");
		lblTotal.setPadding(new Insets(20,0,0,0));

		countTotal();

		//label
		lblDepense.setStyle("-fx-font-family: \"Poppins-Bold");
		lblDepense.setStyle("-fx-font-size: 23px;");
		lblDepense.setPadding(new Insets(0, 0,20, 0));

		//text field
		txtMontant.setPromptText("Combien ?");
		txtMontant.setMaxWidth(200);

		txtMontant.textProperty().addListener((obs, old, newV) -> {
			if (!messageMontant.equals("")) messageMontant.setText("");
			if (txtMontant.getText().matches("^[0-9\\.\\,]+$")) {
				System.out.println("text" + txtMontant.getText());
				depense.setMontant(Double.parseDouble(txtMontant.getText()));
				System.out.println("dans dep form" + depense.getMontant());
			}

			if (!txtMontant.getText().equals("") && !txtMontant.getText().matches("^[0-9\\.\\,]+$")) messageMontant.setText("Rentre un chiffre enfin!");

		});


		//Date
		dpDate.setPromptText(LocalDate.now().format(myFormat).toString());

		//choice box
		try {
			for (Lieu l :FXCollections.observableArrayList(dao.getAllLieu())) {
				lieuxObservableList.add(l.getNom());
			}

			lieuxStringsList = FXCollections.observableArrayList(lieuxObservableList);
			lieuxStringsList.add(0, CB_LABEL);
			lieuxStringsList.add(1, "------------------");
			cbLieux.setItems(lieuxStringsList);
			cbLieux.setValue(lieuxStringsList.get(0));
			cbLieux.itemsProperty();

			cbLieux.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

				@Override
				public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
					try {
						if(messageLieu != null) messageLieu.setText("");
						if(cbLieux.getValue().equals(CB_LABEL)) {messageLieu.setText("Bah non, \"Où ça n'est pas un lieu, enfin !");}
						depense.setIdLieu(dao.getLieuByName(cbLieux.getValue()).getId());
					} catch (SQLException e) {

						e.printStackTrace();
					}
				}
			});
		} catch (SQLException e) {

			e.printStackTrace();
		}



		btnAdd.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				try {

					depense.setDate(dpDate.getValue());
					
					dao.ajouterDepense(depense);
					FrontPage root = (FrontPage)DepenseForm.this.getScene().getRoot();
					
					for(Depense d : dao.getAllDepense()) {System.out.println(d.getMontant());}
					
					root.getDepenseList().getTable().setItems(FXCollections.observableArrayList(dao.getAllDepense()));

					txtMontant.clear();
					dpDate.setValue(null);
					dpDate.setPromptText(LocalDate.now().format(myFormat).toString());
					countTotal();

				} catch (SQLException e) {

					messageMontant.setText("Quelque chose ne va pas avec ta requête, faut revoir ton code !");
				}
				catch (NumberFormatException n) {
					messageMontant.setText("Quelque chose ne va pas avec le format du montant, faut revoir ton code !");
				}
			}
		});			


	}

	public void countTotal() {
		try {
			for (Depense d : dao.getAllDepense()) {
				totalValue = totalValue + d.getMontant();	
			}
		} catch (SQLException e1) {
			messageMontant.setText("Quelque chose ne va pas avec ta requête, faut revoir ton code !");
		}
		String display = totalValue.toString();
		if ((totalValue - Math.floor(totalValue)) == 0.0) {
			Math.round(totalValue);
			display = Long.toString(Math.round(totalValue));
			}
			display = display.replace('.', ',');
			lblTotal.setText("Total : " + display + " euros");

		
	}


	public Label getLblDepense() {
		return lblDepense;
	}




	public void setLblDepense(Label lblDepense) {
		this.lblDepense = lblDepense;
	}




	public TextField getMontant() {
		return txtMontant;
	}




	public void setMontant(TextField montant) {
		this.txtMontant = montant;
	}


	public DatePicker getDate() {
		return dpDate;
	}




	public void setDate(DatePicker date) {
		this.dpDate = date;
	}




	public Button getAddDepense() {
		return btnAdd;
	}




	public void setAddDepense(Button addDepense) {
		this.btnAdd = addDepense;
	}




	public IDataAccess getDao() {
		return dao;
	}




	public void setDao(IDataAccess dao) {
		this.dao = dao;
	}




	public Label getMessage() {
		return messageMontant;
	}




	public void setMessage(Label message) {
		this.messageMontant = message;
	}

	public Depense getDepense() {
		return depense;
	}




	public void setDepense(Depense depense) {
		this.depense = depense;
	}



	public Label getLblTotal() {
		return lblTotal;
	}



	public void setLblTotal(Label lblTotal) {
		this.lblTotal = lblTotal;
	}



	public TextField getTxtMontant() {
		return txtMontant;
	}



	public void setTxtMontant(TextField txtMontant) {
		this.txtMontant = txtMontant;
	}



	public ChoiceBox<String> getCbLieux() {
		return cbLieux;
	}



	public void setCbLieux(ChoiceBox<String> cbLieux) {
		this.cbLieux = cbLieux;
	}



	public DatePicker getDpDate() {
		return dpDate;
	}



	public void setDpDate(DatePicker dpDate) {
		this.dpDate = dpDate;
	}



	public Button getBtnAdd() {
		return btnAdd;
	}



	public void setBtnAdd(Button btnAdd) {
		this.btnAdd = btnAdd;
	}



	public Label getMessageMontant() {
		return messageMontant;
	}



	public void setMessageMontant(Label messageMontant) {
		this.messageMontant = messageMontant;
	}



	public Label getMessageLieu() {
		return messageLieu;
	}



	public void setMessageLieu(Label messageLieu) {
		this.messageLieu = messageLieu;
	}



	public Label getMessageDate() {
		return messageDate;
	}



	public void setMessageDate(Label messageDate) {
		this.messageDate = messageDate;
	}



	public List<String> getLieuxObservableList() {
		return lieuxObservableList;
	}



	public void setLieuxObservableList(List<String> lieuxObservableList) {
		this.lieuxObservableList = lieuxObservableList;
	}



	public ObservableList<String> getLieuxStringsList() {
		return lieuxStringsList;
	}



	public void setLieuxStringsList(ObservableList<String> lieuxStringsList) {
		this.lieuxStringsList = lieuxStringsList;
	}



	public String getDateFormatPattern() {
		return dateFormatPattern;
	}



	public void setDateFormatPattern(String dateFormatPattern) {
		this.dateFormatPattern = dateFormatPattern;
	}



	public DateTimeFormatter getMyFormat() {
		return myFormat;
	}



	public void setMyFormat(DateTimeFormatter myFormat) {
		this.myFormat = myFormat;
	}



	public Double getTotalValue() {
		return totalValue;
	}



	public void setTotalValue(Double totalValue) {
		this.totalValue = totalValue;
	}



	public static String getCbLabel() {
		return CB_LABEL;
	}


}
