package connection;

import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class Client_Receive_Thread extends Thread {

	public Client client;

	public Client_Receive_Thread(Client client) {

		this.client = client;
	}

	public void run() {

		DataInputStream in;
		Socket socket;
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		try {

			while (client.isClientConected()) {
				socket = client.getSocketSend();
				in = new DataInputStream(socket.getInputStream());
				String message = in.readUTF();
				System.out.println(message);
				bw.flush();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
