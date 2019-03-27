package controller;

import java.io.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Random;

import model.Player;
import threads.FoodThread;

public class Login {
	public final static int FOOD_RADIUS = 10;
	private final static String USERS_PATH = "./resources/data/users.txt";
	private final static String SCORE_PATH = "./resources/data/scores.txt";

	private ArrayList<Player> players = new ArrayList<Player>();

	public Login() {

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
		saveUsers();
	}

	public void readSerializable() {
		try {
			InputStream file = new FileInputStream(USERS_PATH);
			InputStream buffer = new BufferedInputStream(file);
			ObjectInput input = new ObjectInputStream(buffer);

			players = (ArrayList<Player>) input.readObject();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public boolean validateLogin(String email, String password) {

		readSerializable();
		boolean correct = false;
		for (int i = 0; i < players.size(); i++) {
			Player compare = players.get(i);
			
			if (compare.getEmail().equals(email) && compare.getPassword().equals(password)) {
				correct = true;
				
			}
		}
		return correct;

	}


	public static void main(String[] args) {

		Login test = new Login();
		test.registerUser("deibi", "12345", "elDeibi@hotmail.com");
		test.registerUser("DaniG", "0987654321", "daniG@gmail.com");
		test.registerUser("Sarris", "0987612345", "laSarris@hotmail.com");
		test.registerUser("Naicolas", "5432167890", "elBiober@hotmail.com");
	}

}
