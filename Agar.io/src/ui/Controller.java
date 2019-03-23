package ui;

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
	void initialize() {
		game = new Game();
	}
	@FXML
	void mouseMoved(MouseEvent event) {

		player.setLayoutX(event.getX());
		player.setLayoutY(event.getY());

	}
}
