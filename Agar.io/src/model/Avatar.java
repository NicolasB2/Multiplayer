package model;

import java.awt.Color;
import java.util.Random;
import ui.Main_Agario;

public class Avatar {

	public static final int INIT_RADIOUS = 150;
	public static final int INIT_SPEED = 4;
	public static final int MAX_COLOR = 256;

	private static Random random = new Random();

	private String nickName;
	private double centerX;
	private double centerY;
	private double radious;
	private double speed;
	private boolean avatar;
	private boolean alive;
	private Color color;
	private int id;

	public void set_color() {
		int r = random.nextInt(MAX_COLOR);
		int g = random.nextInt(MAX_COLOR);
		int b = random.nextInt(MAX_COLOR);
		this.color = new Color(r, g, b);
	}

	public Avatar() {
		this.centerX = random.nextInt(3 * Main_Agario.WINDOW_WIDTH / 4) + Main_Agario.WINDOW_WIDTH / 8;
		this.centerY = random.nextInt(3 * Main_Agario.WINDOW_HEIGHT / 4) + Main_Agario.WINDOW_HEIGHT / 8;
		this.avatar = false;
		this.alive = true;
	}

	public Avatar(String nickName, int id, int xMax, int yMax) {
		this.centerX = random.nextInt(xMax);
		this.centerY = random.nextInt(yMax);
		this.avatar = true;
		this.alive = true;
		this.id = id;
		this.nickName = nickName;
	}

	public void move(double x, double y) {
		this.centerX += x;
		this.centerY += y;
	}

	public void calculate_speed() {
		this.setSpeed(INIT_SPEED / this.radious);
	}

	private double distance(double xi, double yi, double xf, double yf) {
		return Math.sqrt((yf - yi) * (yf - yi) + (xf - xi) * (xf - xi));
	}
	
	private boolean collision(Avatar other) {
		double d = distance(this.centerX, this.centerY, other.centerX, other.centerY);
		if (d < this.radious + other.getRadious()) {
			if (this.radious > other.getRadious()) {
				return true;
			} else if (this.radious < other.radious) {
				return false;
			} else
				return false;
		} else {
			return false;
		}
	}

	public void check_Collision(Avatar other) {	
		boolean c = collision(other);
		if (c == true){
            this.increaseRadious(other.getRadious());
            other.setAlive(false);
        } else if (c == false){
            other.increaseRadious(this.radious);
            this.setAlive(false);
        }
	}

	public double getRadious() {
		return radious;
	}

	public void setRadious(double radious) {
		this.radious = radious;
	}

	public void increaseRadious(double increase) {
		this.radious += increase;
	}
	
	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public boolean isAvatar() {
		return avatar;
	}

	public void setAvatar(boolean avatar) {
		this.avatar = avatar;
	}

	public boolean isAlive() {
		return alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
