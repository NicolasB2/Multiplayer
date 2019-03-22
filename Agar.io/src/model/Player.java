package model;

import java.awt.Color;
import java.awt.Point;

public class Player extends Circle {

	public final static String ALIVE = "ALIVE";
	public final static String GAME_OVER = "GAME_OVER";
	public String state;
	
	private String name;

	public Player(Color color, Point position, int radius) {
		super(color, position, radius);

	}

	public void move() {

	}

	public void grow() {

	}

}
