package model;

import java.io.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Random;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import threads.FoodThread;

public class Game {
	public final static int FOOD_RADIUS = 10;
	private final static String USERS_PATH = "./resources/data/users.txt";
	private final static String SCORE_PATH = "./resources/data/scores.txt";

	private ArrayList<Player> players;
	private ArrayList<Circle> food;
	private LocalTime startTime;
	private LocalTime endTime;

	private boolean isOn;
	private Player testPlayer;

	public Game(Circle c) {

		players = new ArrayList<Player>();
		food = new ArrayList<Circle>();
		saveUsers();
		loadUsers();
		loadInitialFood();

		testPlayer = new Player("Saris", "123", "prueba@vcorreo.com");
		testPlayer.setAvatar(c);
	}

	public Player getTestPlayer() {
		return testPlayer;
	}

	public void setTestPlayer(Player testPlayer) {
		this.testPlayer = testPlayer;
	}

	private void loadInitialFood() {
		for (int i = 0; i < 150; i++) {

			double randomX = Math.floor(Math.random() * (1371 - 1) + 1);
			double randomY = Math.floor(Math.random() * (774 - 1) + 1);

			Random rand = new Random();
			int r = rand.nextInt(255);
			int g = rand.nextInt(255);
			int b = rand.nextInt(255);

			Circle c = new Circle(randomX, randomY, FOOD_RADIUS, Color.rgb(r, g, b));
			food.add(c);
		}

		isOn = true;
		FoodThread f = new FoodThread(this);
		f.start();
	}

	private void loadUsers() {
		File archivo = new File(USERS_PATH);
		if (archivo.exists()) {
			try {
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo));
				players = (ArrayList<Player>) ois.readObject();
				ois.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private void saveUsers() {
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(USERS_PATH));
			oos.writeObject(players);
			oos.close();

		} catch (IOException e) {
		}
	}

	private void loadScores() {
		File archivo = new File(SCORE_PATH);
		if (archivo.exists()) {
			try {
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo));
				players = (ArrayList<Player>) ois.readObject();
				ois.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private void saveScores() {
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(SCORE_PATH));
			oos.writeObject(players);
			oos.close();

		} catch (IOException e) {
		}
	}

	public void registerUser(String nickname, String password, String email) {
		Player usr = new Player(nickname, password, email);
		players.add(usr);
	}
	
	
	public void readSerializable() {
		try {
			InputStream file = new FileInputStream("users.txt");
			InputStream buffer = new BufferedInputStream(file);
			ObjectInput input = new ObjectInputStream(buffer);
			
			users = (ArrayList<Player>)input.readObject();
					
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	
	public boolean validateLogin(String email, String passwork) {
		
		readSerializable();
		boolean correct = false;
		for(int i=0; i<users.size();i++) {
			Player compare = users.get(i);
			if(compare.getEmail().equals(email)&& compare.getPassword().equals(passwork)) {	
				correct=true;
			}
		}
		return correct;
				
	}
	

	public boolean isOn() {
		return isOn;
	}

	public ArrayList<Circle> getFood() {
		return food;
	}

	public void setFood(ArrayList<Circle> food) {
		this.food = food;
	}

	// Test Serializable User
//	public static void main(String[] args) {
//
//		Game test = new Game();
//		test.registerUser("deibi", "12345", "elDeibi@hotmail.com");
//		test.registerUser("DaniG", "0987654321", "daniG@gmail.com");
//		test.registerUser("Sarris", "0987612345", "laSarris@hotmail.com");
//		test.registerUser("Naicolas", "5432167890", "elBiober@hotmail.com");
//	}

}
