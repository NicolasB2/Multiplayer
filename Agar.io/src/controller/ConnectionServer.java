package controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import javax.net.ssl.SSLServerSocketFactory;

import connection.Server_Receive_Thread;
import connection.Server_Send_Thread;

public class ConnectionServer {


	private static int PORT = 8000;
	private static ServerSocket serverSocket;
	
	private Server_Receive_Thread receive;
	private Server_Send_Thread send;
	
	public static final String KEYSTORE_LOCATION = "C:/Program Files (x86)/Java/jre1.8.0_181/bin/keystore.jks";
	public static final String KEYSTORE_PASSWORD = "123456";
	
	public ConnectionServer() throws Exception {
		System.setProperty("javax.net.ssl.keyStore", KEYSTORE_LOCATION);
		System.setProperty("javax.net.ssl.keyStorePassword", KEYSTORE_PASSWORD);
		SSLServerSocketFactory ssf = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
		try {
			this.serverSocket = ssf.createServerSocket(PORT);
			System.out.println("SSLServerSocket Started in "+ serverSocket.getLocalPort());
			
			Socket socket = serverSocket.accept();
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			System.out.println("Client socket created");
			
			String line = null;
			while(((line = br.readLine()) != null))
			{
				System.out.println(line);
				
			}
			
			br.close();
			out.close();
			socket.close();
			serverSocket.close();
			System.out.println("SSLServerSocket Teminated");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public boolean validate_Login(String email, String password) {

		return false;
	}

	public void create_account(String email, String password) {

	}

	public void play_game() {

	}
	
	
	
}
