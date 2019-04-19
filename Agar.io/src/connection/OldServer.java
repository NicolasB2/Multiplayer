package connection;

import java.io.*;
import java.security.*;
import javax.net.ssl.*;

import control.*;
import model.Game;

public class OldServer {

	// Since client
	public final static String LOGIN = "LOGIN";
	public final static String SIGN_IN = "SIGN_IN";
	public final static String PLAY = "PLAY";
	public final static String  MUSIC = "MUSIC";
	public final static String OBSERVE = "OBSERVE";
	
	// Ports
	public static int PORT_CONNECTION = 8000;//tcp
	public static int PORT_LOGIN = 9000;//ssl
	public static int PORT_PLAY = 10000;//tcp
	public static int PORT_MUSIC = 11000;//udp
	public static int PORT_STREAMING = 12000;//udp
	
	public final static int END_TIME = 300000;
	public final static int WAIT_TIME = 10000;

	private int clients;
	private Game connectionGame;

	
	public OldServer() {
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
			SSLServerSocket s = (SSLServerSocket) ssf.createServerSocket(PORT_CONNECTION);

			System.out.println("****** Server online ******");
			System.out.println("clientes: " +clients);
			while (clients<6) {
				SSLSocket sslsocket = (SSLSocket) s.accept();
				System.out.println("New Client accepted");
				//ListenerThread t = new ListenerThread(sslsocket, this);
			//	t.start();
				System.out.println("clientes: " +clients);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void initilizateGame() {
		connectionGame.startGame();
	}

	public boolean validateLogin(String email, String password) {
		boolean log = DataBase.validateLogin(email, password);
		if (log) {
			if (clients == 0) {
				connectionGame = new Game();
				initilizateGame();
			}
			clients++;
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
		String id_ = connectionGame.getIdAvailable()+"";
		return id_ ;
	}
	
	
	public static void main(String[] args) {
		OldServer s = new OldServer();

	}

	public File sendSerializable() {
		DataBase.saveGame(this.connectionGame);
		File f = new File(DataBase.GAME_PATH);
		return f;
	}
}