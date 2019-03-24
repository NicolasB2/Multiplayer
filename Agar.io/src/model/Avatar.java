package model;

import javafx.scene.shape.Circle;

public class Avatar {

	private Circle icon;
	private boolean isAlive;

	public Avatar(Circle icon) {
		this.icon = icon;

	}

	public void move(int x, int y) {

	}

	public void grow(Circle food) {

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
