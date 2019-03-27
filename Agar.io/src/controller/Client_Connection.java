package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import javax.net.ssl.SSLSocketFactory;

import conection.Client_Receive_Thread;
import conection.Client_Send_Thread;

public class Client_Connection {

	private static String SERVER_IP= "localhost";
	private static int  PORT= 8000;
	private static Socket socket;
	
//	private Client_Receive_Thread receive;
//	private Client_Send_Thread send;
	public static final String TRUSTTORE_LOCATION = "C:/Program Files (x86)/Java/jre1.8.0_181/bin/keystore.jks";

	public Client_Connection() throws Exception {

		System.setProperty("javax.net.ssl.trustStore", TRUSTTORE_LOCATION);
		SSLSocketFactory sf = (SSLSocketFactory) SSLSocketFactory.getDefault();
		
		try {
			this.socket = sf.createSocket(SERVER_IP,PORT);
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

	public boolean validate_Login(String email, String password) {

		return false;
	}

	public void create_account(String email, String password) {

	}

	public void play_game() {

	}
}
