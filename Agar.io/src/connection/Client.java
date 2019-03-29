package connection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.print.DocFlavor.STRING;

public class Client {

	public final static int PORT = 8000;
	public final static String SERVER_ADRESS = "localhost";
	
	public Client() {
		System.setProperty("javax.net.ssl.trustStore", "./resources/data/MyClient.jks");
		ObjectOutputStream os = null;
		ObjectInputStream is = null;
		SSLSocket sslsocket = null;

		try {
			SSLSocketFactory f = (SSLSocketFactory) SSLSocketFactory.getDefault();
			sslsocket = (SSLSocket) f.createSocket(SERVER_ADRESS,PORT);

			sslsocket.startHandshake();
			System.out.println("Authentication done");

			os = new ObjectOutputStream(sslsocket.getOutputStream());
			is = new ObjectInputStream(sslsocket.getInputStream());

			BufferedReader b = new BufferedReader(new InputStreamReader(System.in));
			boolean exit = false;
			
			while (!exit) {
				String line = b.readLine();
				os.writeObject(line);
				os.flush();

				String s = (String) is.readObject();
				System.out.println(s);
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
	public static void main(String[] args) {

		Client c = new Client();
	}
}