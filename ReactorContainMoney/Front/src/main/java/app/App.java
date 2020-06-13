package app;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.stage.Stage;

@SuppressWarnings("restriction")
public class App extends Application {

	public static void main(String[] args) {
		launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		System.out.println(Font.loadFont(app.App.class.getResourceAsStream("/Poppins-SemiBold.otf"), 20));
		
		System.out.println(Font.getFontNames("Poppins SemiBold"));
		FrontPage fp = new FrontPage();
		System.out.println(fp);
		Scene s = new Scene(fp, 800, 460);
		
		
		s.getStylesheets().add(getClass().getResource("/app.css").toExternalForm());
		
		primaryStage.sizeToScene();
		primaryStage.setTitle("Enjoy your confinement !");
		primaryStage.getIcons().add(new Image("/money-bag.png"));
		primaryStage.setScene(s);
		primaryStage.show();
		
	}

}
