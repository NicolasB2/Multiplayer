package services;

import java.io.*;
import java.net.*;
import control.*;
import model.*;

public class Server {

	// Since client
	public final static String LOGIN = "LOGIN";
	public final static String SIGN_IN = "SIGN_IN";
	public final static String PLAY = "PLAY";
	public final static String MUSIC = "MUSIC";
	public final static String OBSERVE = "OBSERVE";

	// Answers
	public final static String LOGIN_OK = "LOGIN_OK";
	public final static String LOGIN_INCORRECT = "LOGIN_INCORRECT";
	public final static String SAVE = "SAVE";

	// Ports
	public static int PORT_CONNECTION = 8000;// tcp
	public static int PORT_LOGIN = 9000;// ssl
	public static int PORT_PLAY = 10000;// tcp
	public static int PORT_MUSIC = 11000;// udp
	public static int PORT_STREAMING = 12000;// udp

	public final static int END_TIME = 300000;
	public final static int WAIT_TIME = 10000;

	private static ServerSocket serverSocket;
	private int gamers;
	private Game connectionGame;

	public Server() throws IOException {
		System.out.println("****** Server online ******");
		serverSocket = new ServerSocket(PORT_CONNECTION);

		while (gamers < 6) {
			System.out.println("clientes: " + gamers);
			Socket socket;
			socket = serverSocket.accept();
			AssignPort assign = new AssignPort(socket, this);
			assign.start();
		}
	}

	public void initilizateGame() {
		connectionGame.startGame();
	}

	public boolean validateLogin(String email, String password) {
		boolean log = DataBase.validateLogin(email, password);
		if (log) {
			if (gamers == 0) {
				connectionGame = new Game();
				initilizateGame();
			}
			gamers++;
		}
		return log;
	}

	public void singin(String nickname, String email, String password) {
		DataBase.registerUser(nickname, password, email);
	}

	public String findNickname(String email) {
		return DataBase.findNickName(email);
	}

	public String nextId() {
		String id_ = connectionGame.getIdAvailable() + "";
		return id_;
	}

	public int getGamers() {
		return gamers;
	}

	public void setGamers(int gamers) {
		this.gamers = gamers;
	}

	public void addGamer() {
		gamers++;

	}

	public static void main(String[] args) {
		try {
			Server s = new Server();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}