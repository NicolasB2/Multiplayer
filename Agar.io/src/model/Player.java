package model;

import java.io.Serializable;

public class Player implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String nickname;
	private String password;
	private String email;
	private Avatar avatar;

	public Player(String nickname, String password, String email) {

		this.nickname = nickname;
		this.password = password;
		this.email = email;

	}

	public Avatar getAvatar() {
		return avatar;
	}

	public void setAvatar() {
//		avatar = new Avatar(c);
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

}
