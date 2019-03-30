package connection;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.net.ssl.SSLSocket;

public class ListenerThread extends Thread {

	private SSLSocket sslsocket;
	private Server server;

	public ListenerThread(SSLSocket sslsocket, Server server) {
		this.sslsocket = sslsocket;
		this.server = server;
	}

	@Override
	public void run() {

		ObjectInputStream is = null;
		ObjectOutputStream os = null;
		try {
			is = new ObjectInputStream(sslsocket.getInputStream());
			os = new ObjectOutputStream(sslsocket.getOutputStream());

			

				login(is, os);
				play(is, os);
			

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				is.close();
				os.close();
				this.sslsocket.close();
			} catch (IOException ex) {

			}
		}
	}

	private void login(ObjectInputStream is, ObjectOutputStream os) throws Exception {
		boolean log = false;
		while (!log) {
			
		String p = (String) is.readObject();
		System.out.println("We got: " + p);
		server.message += p + "\n";

		os.writeObject(server.message);
		os.flush();
		}
	}
	
	private void play(ObjectInputStream is, ObjectOutputStream os) throws Exception {
		while (true) {
		String p = (String) is.readObject();
		System.out.println("We got: " + p);
		server.message += p + "\n";

		os.writeObject(server.message);
		os.flush();
		}
	}
}
