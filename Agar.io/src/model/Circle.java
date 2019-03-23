package model;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

public class Circle {

	private Color color;
	private Point2D position;
	private int radius;

	public Circle(Color color, Point2D position, int radius) {
		this.color = color;
		this.position = position;
		this.radius = radius;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Point2D getPosition() {
		return position;
	}

	public void setPosition(Point2D position) {
		this.position = position;
	}

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}
}
