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
}
