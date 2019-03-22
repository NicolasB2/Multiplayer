package controller;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class Server_Send_Thread extends Thread {

	private Server server;

	public Server_Send_Thread(Server server) {

		this.server = server;
	}

	public void run() {

		try {

			DataInputStream in;
			DataOutputStream out;
			System.out.println("start send thread");
			while (true) {

				if (server.isSendMulticast() && !server.lastMessage().equals("")) {

					for (int i = 0; i < server.getSockets().size(); i++) {
						Socket actual = server.getSockets().get(i);
						out = new DataOutputStream(actual.getOutputStream());
						if (i != server.getSender()) {
							String mensaje = server.lastMessage();
							out.writeUTF(mensaje);
						}
					}
					server.setSendMulticast(false);

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
