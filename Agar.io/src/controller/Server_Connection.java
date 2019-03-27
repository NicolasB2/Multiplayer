package controller;

import java.net.ServerSocket;

import conection.Server_Receive_Thread;
import conection.Server_Send_Thread;

public class Server_Connection {


	private static int PORT = 8000;
	private static ServerSocket serverSocket;
	
	private Server_Receive_Thread receive;
	private Server_Send_Thread send;
	
	public Server_Connection() throws Exception {
		// TODO Auto-generated constructor stub
	}
	
	
	public boolean validate_Login(String email, String password) {

		return false;
	}

	public void create_account(String email, String password) {

	}

	public void play_game() {

	}
	
	
	
}
