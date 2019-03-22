package model;

import java.io.*;
import java.util.ArrayList;

public class Game {

	private static String USERS_PATH = "./Sources/Users.txt";
	private ArrayList<Circle> foods;
	private ArrayList<User> users;
	
	public Game() {
		this.foods = new ArrayList<Circle>();
		this.users = new ArrayList<User>();
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
	
	public void add_User(String nickname, String password, String email) {
		User usr =  new User(nickname, password, email);
		users.add(usr);
	}
	
	public void itialize_Foods() {
		
	}
	
	public String[] get_Podium() {
		return null;
	}
	
	
}
