package services;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.security.KeyStore;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;

public class Server_Login_Signin extends Thread {

	private SSLSocket sslsocket;
	private Server server;

	public Server_Login_Signin(Server server) {
		this.server = server;
	}

	private void login(ObjectInputStream is, ObjectOutputStream os) throws Exception {
		boolean log = false;

		String email = (String) is.readObject();
		System.out.println("We got: " + email);
		String password = (String) is.readObject();
		System.out.println("We got: " + password);
		log = server.validateLogin(email, password);

		if (log) {
			os.writeObject(Server.LOGIN_OK);
		} else {
			os.writeObject(Server.LOGIN_INCORRECT);
		}
		os.flush();
	}

	public void singin(ObjectInputStream is, ObjectOutputStream os) throws Exception {
		System.out.println("insede signin");
		String nickname = (String) is.readObject();
		System.out.println("nickname: " + nickname);
		String email = (String) is.readObject();
		System.out.println("email: " + email);
		String password = (String) is.readObject();
		System.out.println("password: " + password);

		server.singin(nickname, email, password);
		
		os.writeObject(Server.SAVE);
		os.flush();
	}

	@Override
	public void run() {
		ObjectInputStream is = null;
		ObjectOutputStream os = null;

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
			SSLServerSocket s = (SSLServerSocket) ssf.createServerSocket(Server.PORT_LOGIN);
			SSLSocket sslsocket = (SSLSocket) s.accept();

			is = new ObjectInputStream(sslsocket.getInputStream());
			os = new ObjectOutputStream(sslsocket.getOutputStream());
			
			String key = (String) is.readObject();
			
			if (key.equals(Server.LOGIN)) {
				login(is, os);
			}
			if (key.equals(Server.SIGN_IN)) {
				singin(is, os);
			}

			sslsocket.close();
			s.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}