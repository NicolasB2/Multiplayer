package model;

import java.io.*;
import java.util.ArrayList;

public class Game {

	private static String USERS_PATH = "./Sources/Users.txt";
	private static String SCORE_PATH = "./Sources/Scores.txt";
	
	private ArrayList<Circle> foods;
	private ArrayList<User> users;
	private Score score;
	
	public Game() {
		this.foods = new ArrayList<Circle>();
		this.users = new ArrayList<User>();
		save_Users();
		load_Useres();
	}
	
	
	public void load_Useres() {
		File archivo = new File(USERS_PATH);
		if (archivo.exists()) {
			try {
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo));
				users = (ArrayList<User>)ois.readObject();
				ois.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void save_Users() {
		try {
			
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(USERS_PATH));
			oos.writeObject(users);
			oos.close();
			
		} catch (IOException e) {
		}
	}
	
	public void load_Scores() {
		File archivo = new File(SCORE_PATH);
		if (archivo.exists()) {
			try {
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo));
				users = (ArrayList<User>)ois.readObject();
				ois.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void save_Scores() {
		try {
			
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(SCORE_PATH));
			oos.writeObject(users);
			oos.close();
			
		} catch (IOException e) {
		}
	}
	
	public void add_User(String nickname, String password, String email) {
		User usr =  new User(nickname, password, email);
		users.add(usr);
	}
	
	public void add(User per) {
		users.add(per);
	}
	
	public void itialize_Foods() {
		
	}
	
	public String[] get_Podium() {
		return null;
	}
	
	
	//Test Serializable User
	public static void main(String[] args) {
		
		Game test = new Game();
		test.add_User("deibi", "12345","elDeibi@hotmail.com");
		test.add_User("DaniG", "0987654321", "daniG@gmail.com");
		test.add_User("Sarris", "0987612345", "laSarris@hotmail.com");
		test.add_User("Naicolas", "5432167890", "elBiober@hotmail.com");
	}
	
	
	
}
