package conection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import javax.net.ssl.SSLSocketFactory;

public class Client {

	private String serverIp;// server's IP address
	private int port;// Free port to establish connection
	private String nickname; //user's nickname
	
	private static Socket socket; // Socket which allowing connection
	private boolean isClientConected; //control boolean
	
	private Client_Receive_Thread receive;//thread which allow receive strings 
	private Client_Send_Thread send;////thread which allow send strings 

	public static final String TRUSTTORE_LOCATION = "C:/Program Files (x86)/Java/jre1.8.0_181/bin/keystore.jks";
	
	public Client() {
		
		// TODO Auto-generated method stub
				System.out.println("SSLClientSocket Started");
				System.setProperty("javax.net.ssl.trustStore", TRUSTTORE_LOCATION);
				SSLSocketFactory sf = (SSLSocketFactory) SSLSocketFactory.getDefault();
				
				try {
					this.socket = sf.createSocket("localhost", 8000);
					PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
					BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					Scanner scanner = new Scanner(System.in);
					while(true)
					{
						System.out.println("Enter text: ");
						String inputLine = scanner.nextLine();
						if("quit".equalsIgnoreCase(inputLine)) 
						{
							break;
						}
						out.println(inputLine);
						System.out.println("Server response: "+  br.readLine());
					}
					System.out.println("SSLServerSocket Terminated");

				} catch (Exception e) {
					// TODO Auto-generated catch block
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
	
	}

}
