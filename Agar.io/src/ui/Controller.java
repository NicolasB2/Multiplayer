package ui;

import java.awt.Button;
import java.awt.TextField;
import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import model.Game;

public class Controller {

	private Game game;

	@FXML
	private AnchorPane pane;

	@FXML
	private Circle player;

	@FXML
	private ArrayList<Circle> food;

	@FXML
	void initialize() {
		game = new Game();
		food = game.getFood();

		for (int i = 0; i < food.size(); i++) {
			pane.getChildren().add(food.get(i));
		}
	}

	@FXML
	void mouseMoved(MouseEvent event) {

		player.setLayoutX(event.getX());
		player.setLayoutY(event.getY());

	}

	@FXML
	void mouseC(MouseEvent event) {
		System.out.println(event.getX() + " " + event.getY());
	}

	// Login

	@FXML
	private TextField txtEmail;

	@FXML
	private PasswordField txtPass;

	@FXML
	private Button butLogin;

	@FXML
	void validateLogin() {
		if(game.validateLogin(txtEmail.getText(), txtPass.getText())) {
			//Open game 
		}else {
			//Send message
		}
	}

	@FXML
	void openCreateAccount() {
		// Open window registrer

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
		//create account
		game.registerUser(txtRegistrerUser.getText(), txtRegistrerPass.getText(), txtRegistrerEmail.getText());
		//Close this window
		//Open window Login
	}
}
