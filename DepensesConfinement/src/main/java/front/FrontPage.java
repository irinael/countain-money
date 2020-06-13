package front;


import java.sql.SQLException;

import dao.DAO;
import entities.Depense;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;


public class FrontPage extends VBox {
	
	private Label lblDepense = new Label("Ajouter une dépense ?");
	private TextField depense = new TextField();
	private Button addDepense = new Button("Allez, encore une !");
	
	private DAO dao = new DAO();
	

	public FrontPage() {
		
		//ajout et agencement des éléments
		
		this.getChildren().addAll(lblDepense, depense, addDepense);
		this.setPadding(new Insets(100, 60, 30, 60));
		this.setSpacing(20);
		
		//text field
		depense.setPromptText("Encore une ?");
		depense.setMaxWidth(200);
		
		addDepense.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				
				try {
					dao.ajouterDepense();
				} catch (SQLException e) {
					
					e.printStackTrace();
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
	
	

}
