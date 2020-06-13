import java.sql.SQLException;

import DataAccessApi.IDataAccess;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import jdbc.DaoJDBC;

@SuppressWarnings("restriction")
public class DepenseForm extends VBox {

	private Label lblDepense = new Label("Ajouter une dépense ?");
	private TextField depense = new TextField();
	private Button addDepense = new Button("Allez, encore une !");
	private IDataAccess dao = new DaoJDBC();
	private Label message = new Label();
	private Label message2 = new Label();



	public DepenseForm() {

		//ajout et agencement des éléments

		this.getChildren().addAll(lblDepense, depense, addDepense, message, message2);
		this.setPadding(new Insets(100, 60, 30, 60));
		this.setSpacing(20);

		//text field
		depense.setPromptText("Encore une ?");
		depense.setMaxWidth(200);


		addDepense.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				message.setText("");

				try {
					if (!depense.getText().equals("") && depense.getText().matches("\\d+")) {

						dao.ajouterDepense(Double.parseDouble(depense.getText())).getId();
						System.out.println(depense.getText());
						

					}
					else {
						message.setText("Rentre un chiffre enfin!");
					}

					depense.clear();

				} catch (SQLException e) {

					message.setText("Quelque chose ne va pas avec ta requête...");
				}
				catch (NumberFormatException n) {
					n.printStackTrace();
				}

			}


		});			

	}


	public TextField getDepense() {
		return depense;
	}


	public void setDepense(TextField depense) {
		this.depense = depense;
	}


	public Button getAddDepense() {
		return addDepense;
	}


	public void setAddDepense(Button addDepense) {
		this.addDepense = addDepense;
	}


	public Label getLblDepense() {
		return lblDepense;
	}


	public void setLblDepense(Label lblDepense) {
		this.lblDepense = lblDepense;
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






}
