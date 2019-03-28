package model;

import java.io.*;
import java.sql.Date;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

import controller.FoodThread;
import controller.Player;

public class Game {
	
	public final static int WAIT_TIME = 120000;
	public final static int END_TIME = 300000;
	public final static int FOOD_RADIUS = 10;
	
	private ArrayList<Avatar> food;
	private ArrayList<Avatar> avatars;
	private Long start;
	private boolean isOn;

	public Game() {
		
		this.food = new ArrayList<Avatar>();
		this.avatars = new ArrayList<Avatar>();
		StartTime();
		isOn = false;
	}

	public void startGame() {
		isOn = true;
		StartTime();
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

			double randomX = Math.floor(Math.random() * (1371 - 1) + 1);
			double randomY = Math.floor(Math.random() * (774 - 1) + 1);

			Random rand = new Random();
			int r = rand.nextInt(255);
			int g = rand.nextInt(255);
			int b = rand.nextInt(255);

			Avatar a = new Avatar();
			food.add(a);
		}

		isOn = true;
		FoodThread f = new FoodThread(this);
		f.start();
	}

	private void deleteFood() {
		ArrayList<Integer> aux = new ArrayList<Integer>();
		for (int i = 0; i < food.size(); i++) {
			if (food.get(i).isAlive()) {
				aux.add(i);
			}
		}

		for (int i = 0; i < aux.size(); i++) {
			food.remove(aux.get(i));
		}

	}

	private void deleteAvatars() {
		ArrayList<Integer> aux = new ArrayList<Integer>();
		for (int i = 0; i < avatars.size(); i++) {
			if (avatars.get(i).isAlive()) {
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
	
	public void checkCollisions() {
		for (int i = 0; i < avatars.size(); i++) {

			for (int j = 0; j < food.size(); j++) {
				avatars.get(i).check_Collision(food.get(j));
			}
		}
		
		for (int i = 0; i < avatars.size(); i++) {

			for (int j = i; j < avatars.size(); j++) {
				avatars.get(i).check_Collision(food.get(j));
			}
		}
		
		deleteAvatars();
		deleteFood();
	}

	public boolean isOn() {
		return isOn;
	}

	public void setOn(boolean isOn) {
		this.isOn = isOn;
	}

}
