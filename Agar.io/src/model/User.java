package model;

public class User {

	private String nickname;
	private String password;
	private String email;
	private Player player;
	
	public User(String nickname, String password, String email) {
		
		this.nickname = nickname;
		this.password = password;
		this.email = email;
	}

	public String getNickname() {
		return nickname;
	}

	public String getPassword() {
		return password;
	}

	public String getEmail() {
		return email;
	}

	public Player getPlayer() {
		return player;
	}
	
	
	
	
}
