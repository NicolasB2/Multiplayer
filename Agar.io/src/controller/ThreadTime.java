package controller;

import connection.Server;
import model.Game;

public class ThreadTime extends Thread {

	private Server connectionServer;
	private int timeOut;

	public ThreadTime(Server server, int timeOut) {

		connectionServer = server;
		this.timeOut = timeOut;

	}

	@Override
	public void run() {
		
		try {
			Thread.sleep(this.timeOut);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		connectionServer.initilizateGame();
	}
}
