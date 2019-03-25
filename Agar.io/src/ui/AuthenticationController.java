package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
	private Button butLogin;

	@FXML
	void validateLogin() {
		if (game.validateLogin(txtEmail.getText(), txtPass.getText())) {
			// Open game
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

		} else {
			// Send message
		}
	}

	@FXML
	void openCreateAccount(ActionEvent event) {
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
	private Button butCreateAccount;

	@FXML
	void createAccount() {
		// create account
		game.registerUser(txtRegistrerUser.getText(), txtRegistrerPass.getText(), txtRegistrerEmail.getText());
		// Close this window
		// Open window Login
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
