package connection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.print.DocFlavor.STRING;

public class ClientConnection {

	public final static int PORT = 8000;
	public final static String SERVER_ADRESS = "localhost";
	private boolean isloogin;
	private String email;
	private String password;
	private String nickname;

	public ClientConnection( String email, String password) {
		this.isloogin = true;
		this.email = email;
		this.password = password;
		connection();
	}

	public ClientConnection(String nickname, String email, String password) {
		this.isloogin = false;
		this.nickname = nickname;
		this.email = email;
		this.password = password;
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
			if (isloogin) {
				login(os, is);
			}else {
				register(os, is);
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

	public void login(ObjectOutputStream os, ObjectInputStream is) throws Exception {

		os.writeObject(Server.LOGIN);
		System.out.println("email: " + email);
		os.writeObject(email);
		System.out.println("password:" + password);
		os.writeObject(password);
		os.flush();

		String s = (String) is.readObject();
		System.out.println(s);

	}
	
	public void register(ObjectOutputStream os, ObjectInputStream is) throws Exception {

		os.writeObject(Server.SING_IN);
		System.out.println("nick: " + nickname);
		os.writeObject(nickname);
		System.out.println("email: " + email);
		os.writeObject(email);
		System.out.println("password:" + password);
		os.writeObject(password);
		os.flush();

		String s = (String) is.readObject();
		System.out.println(s);

	}

	public static void main(String[] args) {

//		ClientConnection c = new ClientConnection("","","");
	}
}