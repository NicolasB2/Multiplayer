package model;

import java.awt.Color;
import java.awt.Point;

public class Circle {

	private Color color;
	private Point position;
	private int radius;
	
	public Circle(Color color, Point position, int radius) {
		this.color = color;
		this.position = position;
		this.radius = radius;
	}

	public Color getColor() {
		return color;
	}

	public Point getPosition() {
		return position;
	}

	public int getRadius() {
		return radius;
	}
	
}
