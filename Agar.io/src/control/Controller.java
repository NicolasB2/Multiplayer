package control;

import java.awt.Color;
import java.io.*;
import java.net.*;
import java.util.ArrayList;

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

	public void login(String mail, String password) {
		int x = answerPort(Server.LOGIN);
		Client_Login_Signin cls = new Client_Login_Signin(this, mail, password, x);
	}

	public void register(String mail, String password, String nickname) {
		int x = answerPort(Server.LOGIN);
		Client_Login_Signin cls = new Client_Login_Signin(this, mail, password, nickname, x);
	}

	public void startGame() {
		int x = answerPort(Server.PLAY);
		Client_Play_Game cpg = new Client_Play_Game(this, x);
		cpg.start();
	}

	public void initializeWorld(String[] infoPlayers, String[] infoBalls) {

		ArrayList<Avatar> players = new ArrayList<Avatar>();

		for (int i = 0; i < infoPlayers.length; i++) {
			String[] player = infoPlayers[i].split("/");
			int id = Integer.parseInt(player[0]);
			String nickname = player[1];
			double radious = Double.parseDouble(player[2]);
			double posX = Double.parseDouble(player[3]);
			double posY = Double.parseDouble(player[4]);
			int rgb = Integer.parseInt(player[5]);

			Avatar player_avatar = new Avatar(nickname, id);
			player_avatar.setColor(new Color(rgb));
			player_avatar.setPosX(posX);
			player_avatar.setPosY(posY);
			player_avatar.setRadious(radious);
			players.add(player_avatar);
		}

		ArrayList<Avatar> food = new ArrayList<Avatar>();

		for (int i = 0; i < infoBalls.length; i++) {
			String[] ballInfo = infoBalls[i].split("/");
			int rgb = Integer.parseInt(ballInfo[0]);
			double posX = Double.parseDouble(ballInfo[1]);
			double posY = Double.parseDouble(ballInfo[2]);
			Avatar bl = new Avatar();
			bl.setColor(new Color(rgb));
			bl.setPosX(posX);
			bl.setPosY(posY);
			food.add(bl);
		}

		game.initializeWorld(players, food);
	}


	public Avatar getAvatar() {
		return game.getAvatar(this.id);
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