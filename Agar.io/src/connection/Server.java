package connection;

import java.io.*;
import java.security.*;
import javax.net.ssl.*;

import controller.Login;
import model.Game;

public class Server {

	public final static String EXIT = "EXIT";
	public final static String LOGIN = "LOGIN";
	public final static String SING_IN = "SING_IN";
	public final static String PLAY = "PLAY";
	public static int PORT = 8000;
	private Login login;
	public String message = "";

	public Server() {
		login = new Login();
		String Password = "123456";
		String ksName = "./resources/data/MyServer.jks";
		char Pass[] = Password.toCharArray();

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

	public boolean validateLogin(String email, String password) {
		return login.validateLogin(email, password);
//		return true;
	}
	
	public void singin (String nickname,String email, String password) {
		login.registerUser(nickname, password, email);
	}
	
	public static void main(String[] args) {
		Server s = new Server();

	}
}