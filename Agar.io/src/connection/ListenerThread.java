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

			String key = (String) is.readObject();
			if (key.equals(Server.LOGIN)) {
				login(is, os);
			}
			if (key.equals(Server.SING_IN)) {
				singin(is, os);
			}
//			if(key.equals(Server.PLAY)){
//				play(is, os);
//			}

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

		String email = (String) is.readObject();
		System.out.println("We got: " + email);
		String password = (String) is.readObject();
		System.out.println("We got: " + password);
		log = server.validateLogin(email, password);
		if (log) {
			os.writeObject(Server.PLAY);
			os.writeObject(server.findNickname(email));
			os.writeObject("1");
		} else {
			os.writeObject(Server.EXIT);
		}
		os.flush();
	}

	public void singin(ObjectInputStream is, ObjectOutputStream os) throws Exception {
		String nickname = (String) is.readObject();
		System.out.println("We got: " + nickname);
		String email = (String) is.readObject();
		System.out.println("We got: " + email);
		String password = (String) is.readObject();
		System.out.println("We got: " + password);
		server.singin(nickname, email, password);
		os.writeObject(Server.LOGIN);
		os.flush();
	}

}
