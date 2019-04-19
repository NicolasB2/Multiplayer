package control;

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
		this.xFinal = (mouse.x -game.getAvatar(id).getCenterX())/250;
		this.yFinal = (mouse.y-game.getAvatar(id).getCenterY())/250; 
		
		if(this.xFinal<0) {
			this.xFinal-=4;
		}
		if(this.yFinal<0) {
			this.yFinal-=4;
		}
		if(this.yFinal>1) {
			this.yFinal+=4;
		}
	}
}