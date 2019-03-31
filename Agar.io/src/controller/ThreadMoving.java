package controller;

import java.awt.MouseInfo;
import java.awt.Point;

import model.Game;
import ui.Space;

public class ThreadMoving extends Thread {
	private static final int INTERVALO = 100;

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
		this.xFinal = (mouse.x -game.getAvatar(1).getCenterX())/100;
		this.yFinal = (mouse.y-game.getAvatar(1).getCenterY())/100; 
		System.out.println(xFinal+"---"+-yFinal);
	}
}
