package control;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

import Client.ClientChat;

public class HiloEscucharChat extends Thread{

	private ClientChat cliente;
	public HiloEscucharChat(ClientChat c) {
		// TODO Auto-generated constructor stub
	   cliente =  c;
	}
	
	@Override
	public void run() {
		DataInputStream in;
		Socket sock;
			try {
				while (cliente.isClientConnected()) {
			   sock = cliente.getSocket();
				in = new DataInputStream(sock.getInputStream());
				String mensajeDelServer = in.readUTF();
				System.out.println("Server - " + mensajeDelServer);
				cliente.recibirMensaje(mensajeDelServer);
			    }
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
	}
}
