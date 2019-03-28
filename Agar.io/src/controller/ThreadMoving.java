package controller;

import java.awt.MouseInfo;
import java.awt.Point;

import model.Game;

public class ThreadMoving extends Thread {
	private static final int INTERVALO = 20;

	private int id;
	private Game game;
	private double xFinal, yFinal;

	public ThreadMoving(int id, Game game) {
		this.id = id;
		this.game = game;
		this.xFinal = 0;
		this.yFinal = 0;
	}

	@Override
	public void run() {
		while (true) {
			try {
				this.updatePositionMouse();
				game.move(xFinal, yFinal, id);
				Thread.sleep(INTERVALO);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	//verificar
	private void updatePositionMouse() {
		Point mouse = MouseInfo.getPointerInfo().getLocation();
		this.xFinal = mouse.x - 50;
		this.yFinal = mouse.y - 50;
	}
}
