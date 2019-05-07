package control;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

import services.ServerChat;

public class HiloMultiCast extends Thread {

	private ServerChat server;
	
	public HiloMultiCast(ServerChat s) {
		// TODO Auto-generated constructor stub
	server = s;
	}
	
	@Override
	public void run() {
		try {
			DataOutputStream out;
			while (server.isChatServerOnline()) {
				if(server.isSendMultiCast() && server.getMensajes().size()>0) {;
					for (int i = 0; i < server.getSockets().size(); i++) {
						Socket actual = server.getSockets().get(i);
						out = new DataOutputStream(actual.getOutputStream());
						for (int j = 0; j < server.getMensajes().size(); j++) {
						   String mensaje = server.getMensajes().get(j);
						   out.writeUTF(mensaje);
						}
					}
					server.eraseMessage();
				}
				
			}
		} catch (Exception e) {
			
		}
	}
}
