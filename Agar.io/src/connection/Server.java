package connection;

import java.io.*;
import java.security.*;
import javax.net.ssl.*;

public class Server {

	public final static String LOGIN = "LOGIN";
	public final static String PLAY = "PLAY";
	public static int PORT = 8000;
	public String message = "";

	public Server() {
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

	public String validateLogin(String email, String password) {
		return "Loggin";
	}
	public static void main(String[] args) {
		Server s = new Server();

	}
}