package ui;

import javax.swing.JFrame;

import controller.Game;

public class Main_Agario extends JFrame {

	private Game connectionModel;

	public Main_Agario() {

		
		
	}

	
	
	public String validateLogin(String email, String password) {
		String message = "Invalid login";
		if (connectionModel.validateLogin(email, password)) {
			message = "Valid login";
		}

		return message;

	}

	public void openRegistrer() {
		Registrer ventRegistrer = new Registrer();
		ventRegistrer.setVisible(true);

	}

	public void openGame() {
		
	}
	
	
	public void creatAccount(String nickname, String password, String email) {
		connectionModel.registerUser(nickname, password, email);
		
	}

	
	
	public static void main(String[] args) {

		Registrer ventRegistrer = new Registrer();
		ventRegistrer.setVisible(true);
		
		//Login ventLogin = new Login();
		//ventLogin.setVisible(true);
	}

}
