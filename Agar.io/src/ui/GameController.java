package ui;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import model.Game;

public class GameController {

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

	
}
