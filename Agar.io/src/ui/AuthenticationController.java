package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
<<<<<<< HEAD:Agar.io/src/ui/AuthenticationController.java
import javafx.scene.control.Button;
=======
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
>>>>>>> d5a7bf8c734075ac90efbba1f57b2b32334cec55:Agar.io/src/ui/Controller.java
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Game;

public class AuthenticationController {

	private Game game;
	// Login

	@FXML
	private TextField txtEmail;

	@FXML
	private PasswordField txtPass;

	@FXML
	private javafx.scene.control.Button butLogin;

	@FXML
	void validateLogin() {
<<<<<<< HEAD:Agar.io/src/ui/AuthenticationController.java
		if (game.validateLogin(txtEmail.getText(), txtPass.getText())) {
			// Open game
=======
		if(game.validateLogin(txtEmail.getText(), txtPass.getText())) {
			
			//Close thisWindow
			Stage thisStage = (Stage) butLogin.getScene().getWindow();
			thisStage.close();
			
			//Open game 
>>>>>>> d5a7bf8c734075ac90efbba1f57b2b32334cec55:Agar.io/src/ui/Controller.java
			try {
				FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Agario.fxml"));
				Parent root = (Parent) fxmlLoader.load();
				Stage stage = new Stage();
				stage.setTitle("Agar.io");
				stage.setScene(new Scene(root));
				stage.show();

			} catch (Exception e) {
				System.out.println("Can't load Agar.io window");
			}
<<<<<<< HEAD:Agar.io/src/ui/AuthenticationController.java

		} else {
			// Send message
=======
			
			
		}else {
			//Send message
			System.out.println("Incorrect Login");
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Alert.");
			alert.setHeaderText("Incorrect Login");
>>>>>>> d5a7bf8c734075ac90efbba1f57b2b32334cec55:Agar.io/src/ui/Controller.java
		}
	}

	@FXML
<<<<<<< HEAD:Agar.io/src/ui/AuthenticationController.java
	void openCreateAccount(ActionEvent event) {
=======
	void openCreateAccount(){
>>>>>>> d5a7bf8c734075ac90efbba1f57b2b32334cec55:Agar.io/src/ui/Controller.java
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Registrer.fxml"));
			Parent root = (Parent) fxmlLoader.load();
			Stage stage = new Stage();
			stage.setScene(new Scene(root));
			stage.setTitle("Registrer");
			stage.show();

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
	void createAccount() {
		// create account
		game.registerUser(txtRegistrerUser.getText(), txtRegistrerPass.getText(), txtRegistrerEmail.getText());
<<<<<<< HEAD:Agar.io/src/ui/AuthenticationController.java
		// Close this window
		// Open window Login
=======
		//Close this window
		Stage thisStage = (Stage) butCreateAccount.getScene().getWindow();
		thisStage.close();
		//Open window Login
>>>>>>> d5a7bf8c734075ac90efbba1f57b2b32334cec55:Agar.io/src/ui/Controller.java
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Login.fxml"));
			Parent root = (Parent) fxmlLoader.load();
			Stage stage = new Stage();
			stage.setScene(new Scene(root));
			stage.setTitle("Login");
			stage.show();

		} catch (Exception e) {
			System.out.println("Can't load Login window");
		}

	}
}
