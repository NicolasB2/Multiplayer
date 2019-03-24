package ui;

import java.util.ArrayList;

import javafx.fxml.FXML;
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
