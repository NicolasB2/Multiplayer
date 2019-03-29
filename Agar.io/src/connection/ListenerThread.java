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

			while (true) {
				String p = (String) is.readObject();
				System.out.println("We got: " + p);
				server.message += p+"\n" ;
				
				os.writeObject(server.message);
				os.flush();

			} // while

		} catch (IOException ex) {

		} catch (ClassNotFoundException ex) {

		} finally {
			try {
				is.close();
				os.close();
				this.sslsocket.close();
			} catch (IOException ex) {

			}
		}
	}// run
}
