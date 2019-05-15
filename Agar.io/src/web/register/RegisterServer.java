package web.register;

import java.net.ServerSocket;
import java.net.Socket;


public class RegisterServer {


	public RegisterServer() {

		try {
			ServerSocket serverSocket = new ServerSocket(8081);

			System.out.println("Login server started");
			while (true) {

				Socket remote = serverSocket.accept();
				new Thread(new RegisterThread(remote)).start();

			}

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public static void main(String[] args) {
		RegisterServer rs = new RegisterServer();
	}
}
