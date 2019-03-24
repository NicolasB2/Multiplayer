package threads;

import java.util.Random;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import model.Game;

public class FoodThread extends Thread {

	private Game game;

	public FoodThread(Game game) {
		this.game = game;
	}

	public void run() {

		try {
			while (game.isOn()) {

				double randomX = Math.floor(Math.random() * (773 - 1) + 1);
				double randomY = Math.floor(Math.random() * (1371 - 1) + 1);

				Random rand = new Random();
				int r = rand.nextInt(255);
				int g = rand.nextInt(255);
				int b = rand.nextInt(255);

				Circle c = new Circle(randomX, randomY, game.FOOD_RADIUS, Color.rgb(r, g, b));
				game.getFood().add(c);

				sleep(2500);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
