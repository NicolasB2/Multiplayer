package web.register;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import control.DataBase;


public class RegisterThread implements Runnable{

private final Socket socket;
	
	
	public RegisterThread(Socket remote) {
		this.socket = remote;
	}

	@Override
	public void run() {
		try {
			DataInputStream in;
			DataOutputStream out;

			in = new DataInputStream(socket.getInputStream());
			out = new DataOutputStream(socket.getOutputStream());
			
			String email = in.readUTF();
			String password = in.readUTF();
			String nickname = in.readUTF();
			
			DataBase.registerUser(email, password, nickname);
				
			
		} catch (IOException e) {

		}
		
	}
}
