package control;

import java.io.*;
import java.util.*;
import model.*;

public class DataBase {
	public final static int FOOD_RADIUS = 10;
	public final static String USERS_PATH = "./resources/data/users.txt";
	public final static String SCORE_PATH = "./resources/data/scores.txt";
	public final static String GAME_PATH = "./resources/data/game.txt";
	public final static String GAME_USER_PATH = "./resources/data/userGame.txt";
	private static ArrayList<Player> players = new ArrayList<Player>();
	private static Hashtable<String, ArrayList<String>> map = new Hashtable<String, ArrayList<String>>();

	public Game loadGame() {

		Game game = null;
		try {
			InputStream file = new FileInputStream(GAME_PATH);
			InputStream buffer = new BufferedInputStream(file);
			ObjectInput input = new ObjectInputStream(buffer);

			game = (Game) input.readObject();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return game;
	}

	public static void saveGame(Game game) {
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(GAME_PATH));
			oos.writeObject(game);
			oos.close();
			oos.flush();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void registerUser(String nickname, String password, String email) {
		readUsers();
		Player usr = new Player(nickname, password, email);
		players.add(usr);
		saveUsers();
	}

	public static void readUsers() {
		try {
			InputStream file = new FileInputStream(USERS_PATH);
			InputStream buffer = new BufferedInputStream(file);
			ObjectInput input = new ObjectInputStream(buffer);

			players = (ArrayList<Player>) input.readObject();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void saveUsers() {
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(USERS_PATH));
			oos.writeObject(players);
			oos.close();

		} catch (IOException e) {
		}
	}

	public static boolean validateLogin(String email, String password) {

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

	public static String findNickName(String email) {

		readUsers();
		for (int i = 0; i < players.size(); i++) {
			if (players.get(i).getEmail().equals(email)) {
				return players.get(i).getNickname();
			}

		}
		return "";
	}

	public static void loadScores() {
		try {
			FileInputStream input = new FileInputStream(new File(SCORE_PATH));
			ObjectInputStream ois = new ObjectInputStream(input);
			map = (Hashtable<String, ArrayList<String>>) ois.readObject();

			ois.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void saveScore() {
		try {
			FileOutputStream outSer = new FileOutputStream(new File(SCORE_PATH));
			ObjectOutputStream os = new ObjectOutputStream(outSer);
			os.writeObject(map);
			os.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public static void addScore(String email, String data) {
		loadScores();
		if(map!=null) {
			if(map.containsKey(email)) {
				map.get(email).add(data);
			}else {
				ArrayList<String> arrayScore = new ArrayList<String>();
				arrayScore.add(data);
				map.put(email, arrayScore);
			}
		}
		saveScore();
	}

	public static void main(String[] args) {

		DataBase.registerUser("david", "123", "david");
		DataBase.registerUser("nicolas", "123", "nicolas");
		DataBase.registerUser("Sara", "123", "Sara");
		DataBase.registerUser("daniela", "123", "daniela");
		
		

//		DataBase.addScore("nicolas", 123454);
//		DataBase.addScore("nicolas", 5673);
//		DataBase.addScore("david", 7653);
//		DataBase.addScore("david", 9876);
//		DataBase.addScore("Sara", 23489);
//		DataBase.addScore("Sara", 67890);
//		DataBase.addScore("daniela", 34567);
//		DataBase.addScore("daniela", 5677);
		
//		DataBase.loadScores();
//		System.out.println(DataBase.map.get("nicolas").size());
	}

}
