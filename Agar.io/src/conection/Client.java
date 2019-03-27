package conection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;

public class Client {

	private String serverIp;// server's IP address
	private int port;// Free port to establish connection
	private String nickname; //user's nickname
	
	private static Socket socket; // Socket which allowing connection
	private boolean isClientConected; //control boolean
	
	private Client_Receive_Thread receive;//thread which allow receive strings 
	private Client_Send_Thread send;////thread which allow send strings 

	public Client(String serverIp, int port, String nickname) {

		this.port = port;
		this.serverIp = serverIp;
		this.nickname = nickname;

		try {
			System.out.println("Welcome to the nicolás chat");
			socket = new Socket(serverIp, port);
			System.out.println("_________________________________");
			isClientConected = true;
			receive = new Client_Receive_Thread(this);
			receive.start();
			send = new Client_Send_Thread(this);
			send.start();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	
	public String getNickname() {
		return nickname;
	}

	public static Socket getSocketSend() {
		return socket;
	}

	public static void setSocketSend(Socket socketSend) {
		Client.socket = socketSend;
	}

	public boolean isClientConected() {
		return isClientConected;
	}

	public void setClientConected(boolean isClientConected) {
		this.isClientConected = isClientConected;
	}

	public static void main(String[] args) {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("please enter your nickname");
		try {
			String nickname = br.readLine();
			Client c = new Client("localhost", 8000 , nickname);

		} catch (IOException e) {
			
		}
	
	}

}
