package ui;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Game;

public class AuthenticationController {

	private Game game = new Game();
	// Login

	@FXML
	private TextField txtEmail;

	@FXML
	private PasswordField txtPass;

	@FXML
	private javafx.scene.control.Button butLogin;

	@FXML
	void validateLogin(ActionEvent event)throws IOException {

		if (game.validateLogin(txtEmail.getText(), txtPass.getText())) {

			// Close thisWindow
			Stage thisStage = (Stage) butLogin.getScene().getWindow();
			thisStage.close();

			// Open game
			try {
				Parent register_parent = FXMLLoader.load(getClass().getResource("Agario.fxml"));
		        Scene register_scene = new Scene(register_parent);
		        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
				
		        app_stage.hide(); //optional
	            app_stage.setScene(register_scene);
	            app_stage.show();
	            app_stage.centerOnScreen();

			} catch (Exception e) {
				System.out.println("Can't load Agar.io window");
			}

		} else {
			// Send message
			System.out.println("Incorrect Login");
			//Alert alert = new Alert(AlertType.INFORMATION);
			//alert.setTitle("Alert");
			//alert.setHeaderText("Incorrect Login");
		}
	}

	@FXML
	void openCreateAccount(ActionEvent event)throws IOException {
		try {
			
			Parent register_parent = FXMLLoader.load(getClass().getResource("Register.fxml"));
	        Scene register_scene = new Scene(register_parent);
	        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			
	        app_stage.hide(); //optional
            app_stage.setScene(register_scene);
            app_stage.show();
            app_stage.centerOnScreen();

		} catch (Exception e) {
			System.out.println("Can't load registrer window");
		}

	}

	// Registrer

	@FXML
	private TextField txtRegistrerUser;

	@FXML
	private TextField txtRegistrerEmail;

	@FXML
	private PasswordField txtRegistrerPass;

	@FXML
	private javafx.scene.control.Button butCreateAccount;

	@FXML
	void createAccount(ActionEvent event)throws IOException {
		// create account
			game.registerUser(txtRegistrerUser.getText(), txtRegistrerPass.getText(), txtRegistrerEmail.getText());

		// Close this window
		Stage thisStage = (Stage) butCreateAccount.getScene().getWindow();
		thisStage.close();
		// Open window Login
		try {
			Parent register_parent = FXMLLoader.load(getClass().getResource("Login.fxml"));
	        Scene register_scene = new Scene(register_parent);
	        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			
	        app_stage.hide(); //optional
            app_stage.setScene(register_scene);
            app_stage.show();
            app_stage.centerOnScreen();

		} catch (Exception e) {
			System.out.println("Can't load Login window");
		}
		}
	
}
