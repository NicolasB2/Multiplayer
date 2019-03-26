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
	private ArrayList<Circle> food;

	@FXML
	private AnchorPane pane;

	@FXML
	private Circle player;

	@FXML
	void initialize() {
		game = new Game();
		game.getTestPlayer().setAvatar(player);
		food = new ArrayList<Circle>();

		for (int i = 0; i < game.getFood().size(); i++) {
			food.add(game.getFood().get(i));
		}
		paintFood();
	}

	private void paintFood() {

		for (int i = 0; i < game.getFood().size(); i++) {
			final int pos = i;

			pane.getChildren().add(food.get(i));
			food.get(i).setOnMouseEntered(e -> {

				game.getTestPlayer().getAvatar().eat(food.get(pos));
				pane.getChildren().remove(food.get(pos));
				game.getFood().remove(food.get(pos));

			});

		}
	}

	@FXML
	void mouseMoved(MouseEvent event) {

		player.setLayoutX(event.getX());
		player.setLayoutY(event.getY());

	}

}
