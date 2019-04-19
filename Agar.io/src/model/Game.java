package model;
import java.awt.*;
import java.io.*;
import java.text.DecimalFormat;
import java.util.*;

import control.*;
import ui.Space;

public class Game implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public final static int FOOD_RADIUS = 10;

	private ArrayList<Avatar> food;
	private ArrayList<Avatar> avatars;

	private boolean isOn;
	private boolean timeout;

	private Font font;
	public Long start;

	public Game() {
		this.food = new ArrayList<Avatar>();
		this.avatars = new ArrayList<Avatar>();
		isOn = false;
		timeout = false;
		initializeFood();
		
		Avatar a = new Avatar("Juan", 3);
		avatars.add(a);

	}

	public void startGame() {
		isOn = true;
		timeout = false;

		ThreadFood f = new ThreadFood(this);
		f.start();

		ThreadCollision c = new ThreadCollision(this);
		c.start();
	}

	public String send_Game() {
		return "";
	}

	public void read_Game(String g) {

	}

	private void initializeFood() {
		for (int i = 0; i < 100; i++) {

			Avatar a = new Avatar();
			food.add(a);
		}

		isOn = true;
	}

	public void deleteFood() {
		ArrayList<Integer> aux = new ArrayList<Integer>();
		for (int i = 0; i < food.size(); i++) {
			if (food.get(i) != null) {
				if (!food.get(i).isAlive()) {
					aux.add(i);
				}
			}
		}
		for (int i = 0; i < aux.size(); i++) {
			this.food.remove((int) aux.get(i));
		}

	}

	public void deleteAvatars() {
		ArrayList<Integer> aux = new ArrayList<Integer>();
		for (int i = 0; i < avatars.size(); i++) {
			if (!avatars.get(i).isAlive()) {
				aux.add(i);
			}
		}

		for (int i = 0; i < aux.size(); i++) {
			avatars.remove((int) aux.get(i));
		}

	}

	public Avatar getAvatar(int id) {
		for (int i = 0; i < avatars.size(); i++) {
			if (avatars.get(i).getId() == id) {
				return avatars.get(i);
			}
		}
		return null;
	}

	public void addAvatar(String nickName, int id) {
		Avatar a = new Avatar(nickName, id);
		avatars.add(a);
	}

	public int getIdAvailable() {
		return avatars.size();

	}

	public void move(double x, double y, int id) {
		Avatar avar = getAvatar(id);
		if (avar != null) {
			avar.move(x, y);
		}
	}

	public boolean isOn() {
		return isOn;
	}

	public void setOn(boolean isOn) {
		this.isOn = isOn;
	}

	public boolean isTimeout() {
		return timeout;
	}

	public void setTimeout(boolean timeout) {
		this.timeout = timeout;
	}

	public ArrayList<Avatar> getFood() {
		return food;
	}

	public void setFood(ArrayList<Avatar> food) {
		this.food = food;
	}

	public ArrayList<Avatar> getAvatars() {
		return avatars;
	}

	public void setAvatars(ArrayList<Avatar> avatars) {
		this.avatars = avatars;
	}

	public void render(Graphics g, double scale) {
		for (int i = 0; i < avatars.size(); i++) {
			Space.render(g, scale, avatars.get(i));
		}
		Avatar avatar = avatars.get(0);
		if (avatar != null) {
			double x = avatar.getCenterX();
			double y = avatar.getCenterY();
			int r = (int) avatar.getRadious();
			this.font = new Font("Calibri", Font.BOLD, r / 2);
			FontMetrics metrics = g.getFontMetrics(font);
			int xt = (int) x - metrics.stringWidth(avatar.getNickName()) / 2;
			int yt = (int) (y + r / 4);
			g.setFont(font);
			g.drawString(avatar.getNickName(), xt, yt);
		}

	}

	public ArrayList<Avatar> getTop() {

		ArrayList<Avatar> top = new ArrayList<>();
		top = avatars;
		Collections.sort(top, new Comparator<Avatar>() {
			@Override
			public int compare(Avatar a1, Avatar a2) {
				return (int) (a2.getRadious() - a1.getRadious());
			}

		});

		if (top.size() > 3) {
			top.subList(3, top.size()).clear();
			return top;
		} else {
			return top;
		}
	}

	public String reportScores() {

		String report = "";

		for (int i = 0; i < getTop().size(); i++) {
			DecimalFormat df = new DecimalFormat("#.##");
			report += (i + 1) + ".  " + getTop().get(i).getNickName() + " " + df.format(getTop().get(i).getRadious())
					+ "\n";
		}
		return report;
	}

}
