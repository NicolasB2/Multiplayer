package model;

import java.io.*;
import java.sql.Date;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

import controller.ThreadFood;
import controller.ThreadMoving;
import controller.Player;
import controller.ThreadCollision;

public class Game {
	
	public final static int WAIT_TIME = 120000;
	public final static int END_TIME = 300000;
	public final static int FOOD_RADIUS = 10;
	
	public ArrayList<Avatar> food;
	public ArrayList<Avatar> avatars;
	private Long start;
	private boolean isOn;

	public Game() {
		
		this.food = new ArrayList<Avatar>();
		this.avatars = new ArrayList<Avatar>();
		StartTime();
		isOn = false;
	}

	public void startGame(int id) {
		isOn = true;
		StartTime();
		initializeFood();
		
		ThreadFood f = new ThreadFood(this);
		f.start();
		
		ThreadCollision c = new ThreadCollision(this);
		c.start();
		
		ThreadMoving m = new ThreadMoving(id,this);
		m.start();
	}
	
	public void StartTime() {
		start = System.currentTimeMillis();
	}
	
	public boolean itsTimeOut() {
		Long corrently = System.currentTimeMillis();
		Long minus = corrently-start;
		
		if(isOn) {
			if (minus==END_TIME) {
				return true;
			}
		}
		
		if(!isOn){
			if (minus==WAIT_TIME) {
				return true;
			}
		}
		
		return false;
	}
	
	private void initializeFood() {
		for (int i = 0; i < 150; i++) {
			
			Avatar a = new Avatar();
			food.add(a);
		}

		isOn = true;
	}

	public void deleteFood() {
		ArrayList<Integer> aux = new ArrayList<Integer>();
		for (int i = 0; i < food.size(); i++) {
			if (!food.get(i).isAlive()) {
				aux.add(i);
			}
		}

		for (int i = 0; i < aux.size(); i++) {
			food.remove(aux.get(i));
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
			avatars.remove(aux.get(i));
		}

	}

	public Avatar getAvatar(int id) {
		for (int i = 0; i < avatars.size(); i++) {
			if(avatars.get(i).getId()==id) {
				return avatars.get(i);
			}
		}
		return null;
	}
	
	public void addAvatar(String nickName, int xMax, int yMax) {
		Avatar a = new Avatar(nickName,avatars.size() , xMax, yMax);
		avatars.add(a);
	}

	public void move(double x, double y, int id) {
		Avatar avar  = getAvatar(id);
		if(avar!=null) {
			avar.move(x, y);
		}	
	}

	public boolean isOn() {
		return isOn;
	}

	public void setOn(boolean isOn) {
		this.isOn = isOn;
	}

}
