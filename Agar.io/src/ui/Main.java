package ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Game;

public class Main extends Application {

	@Override
	public void start(Stage stage) throws Exception {
	
		Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));

		Scene scene = new Scene(root);
		
		stage.setTitle("Agar.io");
	
		stage.setScene(scene);
	
//		stage.setFullScreen(true);
		
		
		stage.setMaximized(true);
		stage.setResizable(false);
		stage.show();
		stage.centerOnScreen();
	}

	public static void main(String[] args) {
		launch(args);

	}

}
