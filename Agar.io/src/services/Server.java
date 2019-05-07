package services;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

import control.*;

import model.*;

public class Server {

	// Since client
	public final static String LOGIN = "LOGIN";
	public final static String SIGN_IN = "SIGN_IN";
	public final static String PLAY = "PLAY";
	public final static String MUSIC = "MUSIC";
	public final static String OBSERVE = "OBSERVE";
	public final static String MUSIC_SERVER = "./resources/music.wav";

	// Answers
	public final static String LOGIN_OK = "LOGIN_OK";
	public final static String LOGIN_INCORRECT = "LOGIN_INCORRECT";
	public final static String SAVE = "SAVE";

	// Ports
	public static int PORT_CONNECTION = 46567;// tcp
	public static int PORT_LOGIN = 38000;// ssl
	public static int PORT_PLAY = 33000;// tcp
	public static int PORT_MUSIC = 50000;// udp
	public static int PORT_STREAMING = 45000;// udp

	public final static int END_TIME = 300000;
	public final static int WAIT_TIME = 10000;

	private static ServerSocket serverSocket;
	private int gamers;
	private Game game;
	private ThreadSendMusic threadSM;
	private boolean alive;

	private ServerSocket serverSocketChat;

	private boolean runningChatService;

	private ThreadChatMulticast threadCM;

	private ThreadUsersMessages threadUM;

	private ThreadUsersMessagesHandler ThreadUMH;

	private ArrayList<String> messages;

	private ArrayList<String> users;

	private ArrayList<Socket> chatSockets;

	private boolean sendMulticast;

	public Server() throws IOException {
		System.out.println("****** Server online ******");
		serverSocket = new ServerSocket(PORT_CONNECTION);

		game = new Game();
		int maxGamers = 0;

		ThreadInicializate th = new ThreadInicializate(this.game);
		th.start();


		while (maxGamers < Game.MAX_PLAYERS) {
			Socket socket;
			socket = serverSocket.accept();
			AssignPort assign = new AssignPort(socket, this);
			assign.start();
			maxGamers++;
		}

	}

	public void singin(String nickname, String email, String password) {
		DataBase.registerUser(nickname, password, email);
	}

	public int validateLogin(String email, String password, String observer) {
		boolean log = DataBase.validateLogin(email, password);

		int r = -1;
		if (log) {
			int id = nextId();

			if (observer.equals("true")) {
				r = 0;
			} else {
				gamers++;
				game.addAvatar(findNickname(email), id);
				System.out.println("gamers: " + gamers);
				r = id;
			}

		}
		System.out.println("gamers: " + gamers);
		return r;
	}

	private String findNickname(String email) {
		return DataBase.findNickName(email);
	}

	public int nextId() {
		int id_ = game.getIdAvailable();
		return id_;
	}

	public String sendBaseGame() {

		ArrayList<Avatar> gamers = game.getAvatars();
		String message = "";

		for (int i = 0; i < gamers.size(); i++) {

			if (gamers.get(i) != null) {
				String id = gamers.get(i).getId() + "";
				String nickname = gamers.get(i).getNickName();
				String radious = gamers.get(i).getRadious() + "";
				String posX = gamers.get(i).getPosX() + "";
				String posY = gamers.get(i).getPosY() + "";
				String rgb = gamers.get(i).getColor().getRGB() + "";
				String live = gamers.get(i).isAlive() ? "true" : "false";
				String player = "";

				if (i == gamers.size() - 1) {
					player = id + "/" + nickname + "/" + radious + "/" + posX + "/" + posY + "/" + rgb + "/" + live;
				} else {
					player = id + "/" + nickname + "/" + radious + "/" + posX + "/" + posY + "/" + rgb + "/" + live
							+ ",";
				}

				message += player;
			}
		}

		message += "_";

		ArrayList<Avatar> food = game.getFood();

		for (int i = 0; i < food.size(); i++) {

			if (food.get(i) != null) {
				String rgb = food.get(i).getColor().getRGB() + "";
				String posX = food.get(i).getPosX() + "";
				String posY = food.get(i).getPosY() + "";
				String ball = rgb + "/" + posX + "/" + posY;

				if (i < food.size() - 1) {
					ball += ",";
				}
				message += ball;
			}
		}
		return message;
	}

	public static ServerSocket getServerSocket() {
		return serverSocket;
	}

	public static void setServerSocket(ServerSocket serverSocket) {
		Server.serverSocket = serverSocket;
	}

	public int getGamers() {
		return gamers;
	}

	public void setGamers(int gamers) {
		this.gamers = gamers;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public static void main(String[] args) {
		try {
			Server s = new Server();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void updateGame(String[] player) {
		int id = Integer.parseInt(player[0]);
		double x = Double.parseDouble(player[1]);
		double y = Double.parseDouble(player[2]);

		boolean isAlive = false;

		if (player[3].equalsIgnoreCase("true")) {
			isAlive = true;
		}

		double radious = Double.parseDouble(player[4]);

		if (this.game.getAvatar(id) != null) {
			this.game.updatePlayer(id, x, y, isAlive, radious);
		}
	}

	public ServerSocket getServerSocketChat() {
		return serverSocketChat;
	}

	public void setServerSocketChat(ServerSocket serverSocketChat) {
		this.serverSocketChat = serverSocketChat;
	}

	public boolean isRunningChatService() {
		return runningChatService;
	}

	public void setRunningChatService(boolean runningChatService) {
		this.runningChatService = runningChatService;
	}

	public ArrayList<String> getMessages() {
		return messages;
	}

	public void setMessages(ArrayList<String> messages) {
		this.messages = messages;
	}

	public void eraseMessages() {

		messages = new ArrayList<String>();
		System.out.print("");
	}
	public boolean isSendMulticast() {
		return sendMulticast;
	}

	public void setSendMulticast(boolean sendMulticast) {
		this.sendMulticast = sendMulticast;
	}

	public void addChatSocket(Socket usm) {

		chatSockets.add(usm);
		
	}
	public void newMessage(String userMessage) {
		
		messages.add(userMessage);
		
	}

	public ArrayList<Socket> getChatSockets() {
		return chatSockets;
	}

	public void setChatSockets(ArrayList<Socket> chatSockets) {
		this.chatSockets = chatSockets;
	}
	public boolean isAlive() {
		return alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}

}