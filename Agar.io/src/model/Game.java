package model;

import java.io.*;
import java.util.ArrayList;

public class Game {

	private ArrayList<Circle> foods;
	private ArrayList<User> users;
	
	
	public void load_Useres() {
		File archivo = new File("");
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
	
	public void save_Users() {
		try {
			
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(""));
			oos.writeObject(users);
			oos.close();
			
		} catch (IOException e) {
		}
	}
}
