package model;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

public class Player extends Circle {

	private String name;
	private boolean alive;

	public Player(Color color, Point2D position, int radius) {
		super(color, position, radius);

	}

	public void move(int x, int y) {

	}

	public void grow(Circle food) {
		this.setRadius(this.getRadius() + food.getRadius());
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isAlive() {
		return alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}

}
