package model;

import java.io.*;
import java.util.ArrayList;

public class Game {

	private static String USERS_PATH = "./resources/data/users.txt";
	private static String SCORE_PATH = "./resources/data/scores.txt";

	private ArrayList<Circle> food;
	private ArrayList<User> users;

	public Game() {
		this.food = new ArrayList<Circle>();
		this.users = new ArrayList<User>();

		saveUsers();
		loadUsers();
	}

	public void loadUsers() {
		File archivo = new File(USERS_PATH);
		if (archivo.exists()) {
			try {
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo));
				users = (ArrayList<User>) ois.readObject();
				ois.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void saveUsers() {
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(USERS_PATH));
			oos.writeObject(users);
			oos.close();

		} catch (IOException e) {
		}
	}

	public void loadScores() {
		File archivo = new File(SCORE_PATH);
		if (archivo.exists()) {
			try {
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo));
				users = (ArrayList<User>) ois.readObject();
				ois.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void saveScores() {
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(SCORE_PATH));
			oos.writeObject(users);
			oos.close();

		} catch (IOException e) {
		}
	}

	public void registerUser(String nickname, String password, String email) {
		User usr = new User(nickname, password, email);
		users.add(usr);
	}

	// Test Serializable User
	public static void main(String[] args) {

		Game test = new Game();
		test.registerUser("deibi", "12345", "elDeibi@hotmail.com");
		test.registerUser("DaniG", "0987654321", "daniG@gmail.com");
		test.registerUser("Sarris", "0987612345", "laSarris@hotmail.com");
		test.registerUser("Naicolas", "5432167890", "elBiober@hotmail.com");
	}

}
