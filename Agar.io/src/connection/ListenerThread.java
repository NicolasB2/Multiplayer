package connection;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

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
			if(key.equals(Server.PLAY)){
				play(is, os);
			}

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
			os.writeObject(Server.LOGIN_OK);
			server.addClients();
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
		os.writeObject(Server.SAVE);
		os.flush();
	}
	
	private void play(ObjectInputStream is, ObjectOutputStream os) throws Exception {
		
		String id = server.nextId();
		os.writeObject(id);
		String email = (String) is.readObject();
		String nick = server.findNickname(email);
		os.writeObject(nick);
		
		System.out.println(id);
		System.out.println(email);
		System.out.println(nick);
		
		try {

			File file = server.sendSerializable();

			long length = file.length();
			byte[] bytes = new byte[16 * 1024];
			FileInputStream in = new FileInputStream(file);

			int count = in.read(bytes);
			while (count > 0) {
				os.write(bytes, 0, count);
				count = in.read(bytes);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		os.flush();
	}

}
