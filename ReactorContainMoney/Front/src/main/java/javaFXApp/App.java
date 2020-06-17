package javaFXApp;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class App extends Application {

	public static void main(String[] args) {
		launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		FrontPage fp = new FrontPage();
		Scene s = new Scene(fp, 800, 460);
		
		
		s.getStylesheets().add(getClass().getResource("/app.css").toExternalForm());
		
		primaryStage.sizeToScene();
		primaryStage.setTitle("Enjoy your confinement !");
		primaryStage.getIcons().add(new Image("/money-bag.png"));
		primaryStage.setScene(s);
		primaryStage.show();
		
	}

}
