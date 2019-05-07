package control;

import Client.ClientChat;

import gui.Chat_GUI;


public class HiloRecibirMensajesInterfaz extends Thread {

	
	private ClientChat cliente;
	private Chat_GUI ic;
	
	public HiloRecibirMensajesInterfaz(Chat_GUI i, ClientChat c) {
		// TODO Auto-generated constructor stub
		cliente = c;
		ic = i;
	}
	
	@Override
	public void run() {
		
		try {
			while(cliente.isClientConnected()) {
				if(cliente.getMensajesRecibidos().size() > 0) {
					for (int i = 0; i < cliente.getMensajesRecibidos().size(); i++) {
						String mensaje = cliente.getMensajesRecibidos().get(i);
						System.out.println("Interfaz - " + mensaje);
						ic.recibirMensajes(mensaje);
					}
					
					cliente.borrarMensajesRecibidos();
					ic.repaint();
				}
				Thread.sleep(5);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
