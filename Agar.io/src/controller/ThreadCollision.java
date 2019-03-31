package controller;

import model.Game;

public class ThreadCollision extends Thread {
	private static final int INTERVAL = 10;
	private Game game;
	private int id;

	public ThreadCollision(Game game, int id) {
		this.game = game;
		this.id = id;
	}

	public void run() {
		while (game.isOn()) {
			try {
				checkCollisions();
				Thread.sleep(INTERVAL);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void checkCollisions() {

		for (int j = 0; j < game.getFood().size(); j++) {
			game.getAvatars().get(id-1).check_Collision(game.getFood().get(j));
		}

		for (int j = 0; j < game.getAvatars().size(); j++) {
			if (j != id) {
				game.getAvatars().get(id-1).check_Collision(game.getFood().get(j));
			}
		}

		game.deleteAvatars();
		game.deleteFood();
	}
}
