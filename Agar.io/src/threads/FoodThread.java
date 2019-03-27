package threads;

import java.util.Random;
import model.Game;

public class FoodThread extends Thread {

	private Game game;

	public FoodThread(Game game) {
		this.game = game;
	}

	/**
	 * Creates a new circle and adds it to the food ArrayList every 2.5 seconds. The
	 * circle has a radius of 10 and a random color.
	 */
	public void run() {

		try {
			while (game.isOn()) {

				double randomX = Math.floor(Math.random() * (773 - 1) + 1);
				double randomY = Math.floor(Math.random() * (1371 - 1) + 1);

				Random rand = new Random();
				int r = rand.nextInt(255);
				int g = rand.nextInt(255);
				int b = rand.nextInt(255);

				

				sleep(2500);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
