package connection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.print.DocFlavor.STRING;

import ui.Login_GUI;

public class ClientConnection {

	public final static int PORT = 8000;
	public final static String SERVER_ADRESS = "localhost";
	public Login_GUI gui;
	private String commands;
	private String email;
	private String password;
	private String nickname;
	private String nick;
	private String id;

	//login
	public ClientConnection( String email, String password,Login_GUI gui, String commands) {
		this.gui = gui;
		this.commands = commands;
		this.email = email;
		this.password = password;
		connection();
	}

	//register
	public ClientConnection(String nickname, String email,  String password,  String commands) {
		this.commands = commands;
		this.password = password;
		this.nickname = nickname;
		this.email = email;
		connection();
	}
	
	//play
	public ClientConnection(  String commands) {
		this.commands = commands;
		connection();
	}
	private void connection() {
		System.setProperty("javax.net.ssl.trustStore", "./resources/data/MyClient.jks");
		ObjectOutputStream os = null;
		ObjectInputStream is = null;
		SSLSocket sslsocket = null;

		try {
			SSLSocketFactory f = (SSLSocketFactory) SSLSocketFactory.getDefault();
			sslsocket = (SSLSocket) f.createSocket(SERVER_ADRESS, PORT);

			sslsocket.startHandshake();
			System.out.println("Authentication done");

			os = new ObjectOutputStream(sslsocket.getOutputStream());
			is = new ObjectInputStream(sslsocket.getInputStream());

			BufferedReader b = new BufferedReader(new InputStreamReader(System.in));
			if (commands.equals(Server.LOGIN)) {
				login(os, is);
			}
			if (commands.equals(Server.SING_IN)){
				register(os, is);
			}
			if (commands.equals(Server.PLAY)){
				play(os, is);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {

			try {
				is.close();
				sslsocket.close();
			} catch (IOException ex) {

			}
		}
	}

	private void play(ObjectOutputStream os, ObjectInputStream is) {
		
		
	}

	public void login(ObjectOutputStream os, ObjectInputStream is) throws Exception {

		os.writeObject(Server.LOGIN);
		os.writeObject(email);
		os.writeObject(password);
		os.flush();

		String s = (String) is.readObject();
		System.out.println(s);
		if(s.equals(Server.LOGIN_OK)) {
			this.gui.loginCorrect = true;
		}

	}
	
	public void register(ObjectOutputStream os, ObjectInputStream is) throws Exception {

		os.writeObject(Server.SING_IN);
		os.writeObject(nickname);
		os.writeObject(email);
		os.writeObject(password);
		os.flush();

		String s = (String) is.readObject();
		System.out.println(s);

	}
	
	public static void main(String[] args) {

//		ClientConnection c = new ClientConnection("","","");
	}

}