package connection;

import java.io.*;
import java.security.*;
import javax.net.ssl.*;

import controller.DataBase;
import controller.ThreadTime;
import model.Game;

public class Server {

	// since client
	public final static String LOGIN = "LOGIN";
	public final static String SING_IN = "SING_IN";
	public final static String PLAY = "PLAY";

	// Answer
	public final static String LOGIN_OK = "LOGIN_OK";
	public final static String EXIT = "EXIT";
	public final static String SAVE = "SAVE";

	public static int PORT = 8000;
	public final static int END_TIME = 300000;
	public final static int WAIT_TIME = 10000;

	private int clients;
	private DataBase ConnectionDB;
	private Game connectionGame;

	public Server() {
		ConnectionDB = new DataBase();
		String Password = "123456";
		String ksName = "./resources/data/MyServer.jks";
		char Pass[] = Password.toCharArray();
		clients = 0;
		KeyStore ks;
		try {
			ks = KeyStore.getInstance("JKS");
			ks.load(new FileInputStream(ksName), Pass);
			KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
			kmf.init(ks, Pass);
			SSLContext sc = SSLContext.getInstance("TLS");
			sc.init(kmf.getKeyManagers(), null, null);
			SSLServerSocketFactory ssf = sc.getServerSocketFactory();
			SSLServerSocket s = (SSLServerSocket) ssf.createServerSocket(PORT);

			System.out.println("****** Server online ******");
			while (true) {
				SSLSocket sslsocket = (SSLSocket) s.accept();
				System.out.println("New Client accepted");
				ListenerThread t = new ListenerThread(sslsocket, this);
				t.start();
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void initilizateGame() {
		connectionGame.startGame();
		System.out.println("se inicializa el juego");
	}

	public boolean validateLogin(String email, String password) {
		boolean log = ConnectionDB.validateLogin(email, password);
		if (log) {
			if (clients == 0) {
				connectionGame = new Game();
				ThreadTime tt = new ThreadTime(this, WAIT_TIME);
				tt.start();
			}
			clients++;
		}
		return log;
	}

	public void singin(String nickname, String email, String password) {
		ConnectionDB.registerUser(nickname, password, email);
	}

	public String findNickname(String email) {
		return ConnectionDB.findNickName(email);
	}

	public static void main(String[] args) {
		Server s = new Server();

	}
}