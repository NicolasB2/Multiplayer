package services;

import java.io.*;
import java.net.Socket;
import control.Controller;

public class Client_Play_Game extends Thread {

	public final static String PATH = "./resources/data/userGame.txt";
	
	private Controller controller;
	private Socket socket;
	private int port;

	public Client_Play_Game(Controller controller, int port) {
		this.controller = controller;
		this.port = port;
		try {
			this.socket = new Socket(Controller.SERVER_ADRESS, port);
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
			
//			out.writeUTF(Server.PORT_PLAY + "");
			String read = in.readUTF();
			String[] infoBig = read.split("_");
			String[] infoPlayers = infoBig[0].split(",");
			String[] infoBalls = infoBig[1].split(",");

			controller.initializeWorld(infoPlayers, infoBalls);
			controller.startMoving();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
