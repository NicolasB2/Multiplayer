package control;

import java.io.*;
import java.net.*;
import model.*;
import services.*;

public class Controller {

	public final static int PORT = 8000;
	public final static String SERVER_ADRESS = "localhost";

	private Socket socket;
	private Game game;
	private boolean correctLogin;
	private String nickName;
	private int id;

	public Controller() {
		
		this.correctLogin = false;
		this.game = new Game();
		
		try {
			this.socket = new Socket(SERVER_ADRESS, PORT);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int answerPort(String request) {
		
		int answer = -1;
		DataOutputStream out;
		DataInputStream in;

		try {
			out = new DataOutputStream(socket.getOutputStream());
			in = new DataInputStream(socket.getInputStream());
			
			out.writeUTF(request);
			String message = in.readUTF();
			answer = Integer.parseInt(message);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return answer;
	}

	public void login(String mail,String password) {
		int x = answerPort(Server.LOGIN);
		Client_Login_Signin cls = new Client_Login_Signin(this, mail, password, x);
	}

	public void register(String mail,String password,String nickname) {
		int x = answerPort(Server.LOGIN);
		Client_Login_Signin cls = new Client_Login_Signin(this, mail, password,nickname,x);
	}
	
	public void setPlayer(String nick, int id) {
		this.id = id;
		this.nickName = nick;
		game.addAvatar(this.nickName, this.id);
	}

	public Avatar getAvatar() {
		return game.getAvatar(this.id);
	}

	public void startGame() {
		game.startGame();
	}

	public void startMoving() {
		ThreadMoving m = new ThreadMoving(id, this.game);
		m.start();
	}

	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public boolean isCorrectLogin() {
		return correctLogin;
	}

	public void setCorrectLogin(boolean correctLogin) {
		System.out.println("change in controller");
		this.correctLogin = correctLogin;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}