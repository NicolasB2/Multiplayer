package controller;

import model.Game;

public class ThreadTime extends Thread {
	private Game game;

	public ThreadTime(Game game) {
		this.game = game;
	}

	@Override
	public void run() {

		Long corrently = System.currentTimeMillis();
		Long minus = corrently - game.start;

		if (game.isOn()) {
			if (minus == game.END_TIME) {
				game.setTimeout(true);
			}
		}

		if (!game.isOn()) {
			if (minus == game.WAIT_TIME) {
				game.setTimeout(true);
			}
		}
		game.setTimeout(false);

	}
}
