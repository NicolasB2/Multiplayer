package connection;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;

import javax.net.ssl.SSLSocket;

public class ClientListenerThread extends Thread {

	SSLSocket sslsocket;
	
	public ClientListenerThread(SSLSocket sslsocket) {
		ObjectInputStream is = null;
		try {
			is = new ObjectInputStream(sslsocket.getInputStream());
			boolean exit = false;
			
			while (!exit) {
				String s = (String) is.readObject();
				System.out.println(s);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
	}
}
