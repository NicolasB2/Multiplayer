package controller;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import conection.Client_Receive_Thread;
import conection.Client_Send_Thread;

public class Client_Connection {

	private static String SERVER_IP= "localhost";
	private static int  PORT= 8000;
	private static Socket socket;
	
	private Client_Receive_Thread receive;//thread which allow receive strings 
	private Client_Send_Thread send;////thread which allow send strings 

	
	public Client_Connection() throws Exception {
		
	}

	public boolean validate_Login(String email, String password) {

		return false;
	}

	public void create_account(String email, String password) {

	}

	public void play_game() {

	}
}
