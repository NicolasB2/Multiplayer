package controller;

import java.io.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Random;

import model.Game;

public class DataBase {
	public final static int FOOD_RADIUS = 10;
	private final static String USERS_PATH = "./resources/data/users.txt";
	private final static String SCORE_PATH = "./resources/data/scores.txt";
	private final static String GAME_PATH = "./resources/data/scores.txt";
	private ArrayList<Player> players = new ArrayList<Player>();


	public DataBase() {

	}

	public void loadGame(Game game){
		File archivo = new File(GAME_PATH);
		if (archivo.exists()) {
			try {
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo));
				game = (Game) ois.readObject();
				ois.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	public void saveGame(Game game) {
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(GAME_PATH));
			oos.writeObject(game);
			oos.close();

		} catch (IOException e) {
		}
	}

	public void registerUser(String nickname, String password, String email) {
		Player usr = new Player(nickname, password, email);
		players.add(usr);
		saveUsers();
	}
	
	private void saveUsers() {
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(USERS_PATH));
			oos.writeObject(players);
			oos.close();

		} catch (IOException e) {
		}
	}

	public void readUsers() {
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

		readUsers();
		boolean correct = false;
		for (int i = 0; i < players.size(); i++) {
			Player compare = players.get(i);

			if (compare.getEmail().equals(email) && compare.getPassword().equals(password)) {
				correct = true;

			}
		}
		return correct;

	}

	public String findNickName(String email) {
		
		for (int i = 0; i < players.size(); i++) {
			if(players.get(i).getEmail().equals(email)) {
				return players.get(i).getNickname();
			}
			
		}
		return "";
	}
	public static void main(String[] args) {

		DataBase test = new DataBase();
		test.registerUser("deibi", "12345", "elDeibi@hotmail.com");
		test.registerUser("DaniG", "0987654321", "daniG@gmail.com");
		test.registerUser("Sarris", "0987612345", "laSarris@hotmail.com");
		test.registerUser("Naicolas", "5432167890", "elBiober@hotmail.com");
	}

}
