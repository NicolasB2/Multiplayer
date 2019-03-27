package model;

import java.io.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Random;

import controller.FoodThread;
import model.Player;

public class Game {
	public final static int FOOD_RADIUS = 10;
	private ArrayList<Avatar> avatars;
	private LocalTime startTime;
	private LocalTime endTime;
	private boolean isOn;

	public Game() {

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
			avatars.add(a);
		}

		isOn = true;
		FoodThread f = new FoodThread(this);
		f.start();
	}

	public boolean isOn() {
		return isOn;
	}

	public void setOn(boolean isOn) {
		this.isOn = isOn;
	}

}
