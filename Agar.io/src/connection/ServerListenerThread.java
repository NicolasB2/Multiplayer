package connection;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.net.ssl.SSLSocket;

public class ServerListenerThread extends Thread {

	SSLSocket sslsocket;

	public ServerListenerThread(SSLSocket sslsocket) {
		this.sslsocket = sslsocket;
	}

	@Override
	public void run() {

		ObjectInputStream is = null;
		ObjectOutputStream os = null;
		try {
			is = new ObjectInputStream(sslsocket.getInputStream());
			os = new ObjectOutputStream(sslsocket.getOutputStream());

			while (true) {
				if(is.read()>0) {
				String p = (String) is.readObject();
				System.out.print("We got: ");
				System.out.println(p);
				}
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				is.close();

				this.sslsocket.close();
			} catch (IOException ex) {

			}
		}
	}
}