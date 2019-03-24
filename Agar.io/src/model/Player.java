package model;

import java.io.Serializable;

import javafx.scene.shape.Circle;

public class Player implements Serializable{

	private String nickname;
	private String password;
	private String email;
	private Avatar player; 
	
	public Player(String nickname, String password, String email){
		
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

	public Avatar getPlayer() {
		return player;
	}

	public void setPlayer(Avatar player) {
		this.player = player;
	}

	
}
