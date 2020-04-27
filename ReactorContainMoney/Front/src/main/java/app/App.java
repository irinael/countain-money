package app;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;

@SuppressWarnings("restriction")
public class App extends Application {

	public static void main(String[] args) {
		launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		System.out.println(Font.loadFont(app.App.class.getResourceAsStream("/Poppins-Light.otf"), 16));
		
		System.out.println(Font.getFontNames("Poppins Light"));
		FrontPage fp = new FrontPage();
		Scene s = new Scene(fp, 800, 400);
		
		s.getStylesheets().add(getClass().getResource("/app.css").toExternalForm());
		
		primaryStage.sizeToScene();
		primaryStage.setTitle("Enjoy your confinement !");
		primaryStage.setScene(s);
		primaryStage.show();
		
	}

}
