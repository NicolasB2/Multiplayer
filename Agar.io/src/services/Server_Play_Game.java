package services;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server_Play_Game extends Thread {

	private Server server;
	private ServerSocket serverSocket;
	private Socket socket;

	public Server_Play_Game(Server server) {
		this.server = server;
		this.socket = null;

		try {

			this.serverSocket = new ServerSocket(Server.PORT_PLAY);
			this.socket = serverSocket.accept();
			this.serverSocket.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		try {

			DataInputStream in;
			DataOutputStream out;

			in = new DataInputStream(socket.getInputStream());
			out = new DataOutputStream(socket.getOutputStream());
			server.getGame().startGame();
			
			while (true) {
				out.writeUTF(server.sendBaseGame());
				String received = in.readUTF();
				String[] player = received.split("/");
				server.updateGame(player);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
