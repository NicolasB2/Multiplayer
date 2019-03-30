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
	private boolean x;
	private String email;
	private String password;

	public ClientConnection(boolean x, String email, String password) {
		this.x = x;
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
			if (x) {
				login(os, is, email, password);
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

	public void login(ObjectOutputStream os, ObjectInputStream is, String email, String password) throws Exception {
		boolean log = false;

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