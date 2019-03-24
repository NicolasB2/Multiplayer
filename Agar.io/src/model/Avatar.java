package model;

import javafx.scene.shape.Circle;

public class Avatar {

	private Circle icon;
	private boolean isAlive;

	public Avatar(Circle c) {
		icon = c;
	}

	public void move(int x, int y) {

	}

	public void eat(Circle food) {
		if (food.intersects(icon.getBoundsInLocal())) {
			System.out.println("yeeees");
		}
		icon.setRadius(icon.getRadius() + (food.getRadius() / 4));

	}

	public Circle getIcon() {
		return icon;
	}

	public void setIcon(Circle icon) {
		this.icon = icon;
	}

	public boolean isAlive() {
		return isAlive;
	}

	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}

}
