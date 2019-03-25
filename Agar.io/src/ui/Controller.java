package ui;

import java.awt.Button;
import java.awt.Event;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.ArrayList;

import org.hamcrest.Condition.Step;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
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
		game = new Game(player);
		food = game.getFood();

		for (int i = 0; i < food.size(); i++) {
			final int b = i;
			pane.getChildren().add(food.get(i));
			pane.getChildren().get(i).setOnMouseEntered(e -> {
				game.getTestPlayer().getAvatar().eat((Circle)pane.getChildren().get(b));
				
			});
		}

		player.toFront();
	}

	@FXML
	void mouseMoved(MouseEvent event) {

		player.setLayoutX(event.getX());
		player.setLayoutY(event.getY());

	}

	@FXML
	void mouseC(MouseEvent event) {

	}

	// Login

	@FXML
	private TextField txtEmail;

	@FXML
	private PasswordField txtPass;

	@FXML
	private javafx.scene.control.Button butLogin;

	@FXML
	void validateLogin() {
		if(game.validateLogin(txtEmail.getText(), txtPass.getText())) {
			
			//Close thisWindow
			Stage thisStage = (Stage) butLogin.getScene().getWindow();
			thisStage.close();
			
			//Open game 
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
			
			
		}else {
			//Send message
			System.out.println("Incorrect Login");
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Alert.");
			alert.setHeaderText("Incorrect Login");
		}
	}

	@FXML
	void openCreateAccount(){
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
		//create account
		game.registerUser(txtRegistrerUser.getText(), txtRegistrerPass.getText(), txtRegistrerEmail.getText());
		//Close this window
		Stage thisStage = (Stage) butCreateAccount.getScene().getWindow();
		thisStage.close();
		//Open window Login
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
