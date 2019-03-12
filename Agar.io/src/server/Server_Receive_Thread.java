package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server_Receive_Thread extends Thread {

	private Server server;
	private Socket socket;
	private int number;

	public Server_Receive_Thread(Server server, Socket socket, int number) {

		this.server = server;
		this.socket = socket;
		this.number = number;
	}

	public void run() {

		try {

			DataInputStream in;
			System.out.println("start recive thread");
			while (true) {

				in = new DataInputStream(socket.getInputStream());
				String messege = in.readUTF();
				System.out.println(messege);
				server.setSender(number);
				server.newMessege(messege);
				server.setSendMulticast(true);

			}
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}
