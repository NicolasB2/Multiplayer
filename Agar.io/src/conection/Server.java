package conection;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {

	private ArrayList<Server_Receive_Thread> serverRecive;
	private Server_Send_Thread serverSend;

	private ArrayList<String> messeges;
	private ArrayList<Socket> sockets;

	private static ServerSocket serverSocket;
	private boolean sendMulticast;

	private int sender;

	public Server(int port, int number_clients) {

		try {

			System.out.println("Server on line");
			serverSocket = new ServerSocket(port);
			sendMulticast = false;
			sender = -1;

			sockets = new ArrayList<>();
			messeges = new ArrayList<>();

			serverRecive = new ArrayList<>();

			for (int i = 0; i < number_clients; i++) {
				Socket s = serverSocket.accept();
				sockets.add(s);
				System.out.println("Client was connected");
				serverRecive.add(new Server_Receive_Thread(this, s, i));
			}

			serverSend = new Server_Send_Thread(this);
			serverSend.start();

			for (int i = 0; i < serverRecive.size(); i++) {
				serverRecive.get(i).start();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public ArrayList<Socket> getSockets() {
		return sockets;
	}

	public boolean isSendMulticast() {
		return sendMulticast;
	}

	public void setSendMulticast(boolean sendMulticast) {
		this.sendMulticast = sendMulticast;
	}

	public int getSender() {
		return sender;
	}

	public void setSender(int sender) {
		this.sender = sender;
	}

	public void newMessege(String mensajeObtenidoCliente) {
		messeges.add(mensajeObtenidoCliente);
	}

	public String lastMessage() {
		if (messeges.size() > 0) {
			return messeges.get(messeges.size() - 1);
		}
		return "";
	}

	public static void main(String[] args) {

		Server s = new Server(8000, 3);

	}
}
