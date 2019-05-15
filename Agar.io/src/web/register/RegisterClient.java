package web.register;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class RegisterClient {
	
	private Socket socket;
	private String email, password, nickname;

	public RegisterClient(String email, String password, String nickname) {
		this.email = email;
		this.password = password;
		this.nickname = nickname;

	}

	public boolean validateLogin() {
		String answer = "";
		try {
			this.socket = new Socket("localhost", 8081);

			DataInputStream in;
			DataOutputStream out;

			in = new DataInputStream(socket.getInputStream());
			out = new DataOutputStream(socket.getOutputStream());

			out.writeUTF(this.email);
			out.writeUTF(this.password);

			answer = in.readUTF();

		} catch (Exception e) {
		}

		if (answer.equals("yes")) {
			return true;
		}

		if (answer.equals("no")) {
			return false;
		}
		return false;
	}
}



