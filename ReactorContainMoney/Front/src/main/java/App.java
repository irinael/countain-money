

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

@SuppressWarnings("restriction")
public class App extends Application {

	public static void main(String[] args) {
		launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		FrontPage fp = new FrontPage();
		Scene s = new Scene(fp, 800, 400);
		
		primaryStage.sizeToScene();
		primaryStage.setTitle("Enjoy your confinement !");
		primaryStage.setScene(s);
		primaryStage.show();
		
	}

}
