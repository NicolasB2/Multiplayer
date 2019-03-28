package controller;
import model.Game;

public class ThreadCollision extends Thread {
	private static final int INTERVAL = 10;
	private Game game;

	public ThreadCollision(Game game) {
		this.game = game;
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
		for (int i = 0; i < game.avatars.size(); i++) {

			for (int j = 0; j < game.food.size(); j++) {
				 game.avatars.get(i).check_Collision( game.food.get(j));
			}
		}
		
		for (int i = 0; i <  game.avatars.size(); i++) {

			for (int j = i; j <  game.avatars.size(); j++) {
				 game.avatars.get(i).check_Collision( game.food.get(j));
			}
		}
		
		 game.deleteAvatars();
		 game.deleteFood();
	}
}
