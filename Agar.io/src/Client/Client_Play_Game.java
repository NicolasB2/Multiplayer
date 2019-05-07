package Client;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

import control.Controller;

import gui.Main_Agario;



public class Client_Play_Game extends Thread {

	public final static String PATH = "./resources/data/userGame.txt";

	private Controller controller;
	private Socket socket;
	private int port;
	private String musicRoot;
	
	private ArrayList<String> userMessages;
	private ThreadSendMessages ThreadSM;
	private Main_Agario gui;
	private boolean chatService;
	private Socket chatSocket;
	private String nickname;

	public Client_Play_Game(Controller controller, int port) {
		this.controller = controller;
		this.port = port;
		gui = controller.getMain_Agario();
		nickname = controller.getNickName();
		
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

			String read = in.readUTF();
			while (read.equals("wait")) {
				controller.showWait();
				read = in.readUTF();
			}
			
			controller.cleanMessage();
			String[] infoBig = read.split("_");
			String[] infoPlayers = infoBig[0].split(",");
			String[] infoBalls = infoBig[1].split(",");

			controller.initializeWorld(infoPlayers, infoBalls);
			controller.startMoving();
			
			
			
			boolean control = true;
			while (control) {

				int id = controller.getId();
				if (controller.getGame().getAvatar(id) != null) {

					double x = controller.getGame().getAvatar(id).getPosX();
					double y = controller.getGame().getAvatar(id).getPosY();
					boolean isPlaying = controller.getGame().getAvatar(id).isAlive();
					double radious = controller.getGame().getAvatar(id).getRadious();
					out.writeUTF(id + "/" + x + "/" + y + "/" + isPlaying + "/" + radious);

					read = in.readUTF();
					
					if(read.equals("time")) {
						System.out.println("time");
						controller.getGame().setOn(false);
						boolean win = controller.getGame().calculeWin(controller.getId());
						if(win) {
							controller.showWin();
						}else {
							controller.showLose();
						}
						
						break;
					}
					
					infoBig = read.split("_");
					infoPlayers = infoBig[0].split(",");
					infoBalls = infoBig[1].split(",");

					controller.updateWorld(infoPlayers, infoBalls);

					
					if(controller.youWin()) {
						out.writeUTF("exit");
						controller.showWin();
						break;
					}
				} else {
					out.writeUTF("exit");
					System.out.println("you lose");
					controller.showLose();
					break;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void sendMessage(String message) {
		userMessages.add(message);
		ThreadSM.addMessage(message);
	}
	
	public void receiveMessage(String message) {
		
		gui.receiveMessage(message);
	}
	
	public void eraseMessages() {
		userMessages = new ArrayList<String>();
	}
	public boolean isChatService() {
		return chatService;
	}

	public void setChatService(boolean chatService) {
		this.chatService = chatService;
	}
	public ArrayList<String> getUserMessages() {
		return userMessages;
	}

	public void setUserMessages(ArrayList<String> userMessages) {
		this.userMessages = userMessages;
	}
	public Socket getChatSocket() {
		return chatSocket;
	}

	public void setChatSocket(Socket chatSocket) {
		this.chatSocket = chatSocket;
	}
	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getMusicRoot() {
		return musicRoot;
	}

	public void setMusicRoot(String musicRoot) {
		this.musicRoot = musicRoot;
	}

}
