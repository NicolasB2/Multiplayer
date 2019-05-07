package Client;

import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class ThreadInfoChatServer extends Thread {

	public Client_Play_Game client;

	public ThreadInfoChatServer(Client_Play_Game client) {

		this.client = client;
	}

	public void run() {
		DataInputStream in;
		Socket socket;

		try {

			while (client.isChatService()) {
				socket = client.getChatSocket();
				in = new DataInputStream(socket.getInputStream());
				String serverMessage = in.readUTF();
				client.receiveMessage(serverMessage.replaceAll(";", " : ") + "\n");

			}

		} catch (Exception e) {
//			e.printStackTrace();
		}
	}

}
