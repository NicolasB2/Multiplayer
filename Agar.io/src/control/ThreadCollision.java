package control;

import model.Game;

public class ThreadCollision extends Thread {
	private static final int INTERVAL = 10;
	private Game game;
	private int id;

	public ThreadCollision(Game game) {
		this.game = game;
	}

	public void run() {
		while (game.isOn()) {
			try {
				checkCollisions();
				Thread.sleep(INTERVAL);
			} catch (Exception e) {
				
			}
		}
	}

	public void checkCollisions() {
		for (int i = 0; i < game.getAvatars().size(); i++) {
			for (int j = 0; j < game.getFood().size(); j++) {
				if (game.getFood().get(j) != null) {
					game.getAvatars().get(i).check_Collision(game.getFood().get(j));
				}
			}

		}
		for (int i = 0; i < game.getAvatars().size(); i++) {
			for (int j = 0; j < game.getAvatars().size(); j++) {
				if (game.getAvatars().get(j) != null) {
					if (j != id) {
						game.getAvatars().get(i).check_Collision(game.getAvatars().get(j));
					}
				}
			}
		}

		game.deleteAvatars();
		game.deleteFood();
	}
}
