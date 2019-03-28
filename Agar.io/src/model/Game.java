package model;

import java.io.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Random;

import controller.FoodThread;
import model.Player;

public class Game {
	public final static int FOOD_RADIUS = 10;
	private ArrayList<Avatar> food;
	private ArrayList<Avatar> avatars;

	private LocalTime startTime;
	private LocalTime endTime;
	private boolean isOn;

	public Game() {
		initializeFood();
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

	// FALTA
	public void addAvatar() {
		Avatar a = new Avatar();
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
