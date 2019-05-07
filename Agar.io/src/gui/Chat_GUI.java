package gui;

import javax.swing.*;

import Client.ClientChat;


import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Chat_GUI extends JFrame {
	
	private SendMessagePanel enviar;
	private ChatPanel chat;
	private JLabel banner;
	private ClientChat cliente;
	
	
	public Chat_GUI() {
		// TODO Auto-generated constructor stub	
		String nickname = "";
		while(nickname.equals("")|| nickname.equals(" ") || nickname.equals(":") || nickname.equals(": ")){
		nickname = JOptionPane.showInputDialog(this, "Ingrese su nickname para el chat", "Nickname", JOptionPane.OK_CANCEL_OPTION);
		}
		setLayout(new BorderLayout());
		setTitle("Chat");
		setSize(new Dimension(510,480));
		setResizable(false);
		enviar = new SendMessagePanel(this);
		chat = new ChatPanel(this);
		banner = new JLabel();

		
		add(banner, BorderLayout.NORTH);
		add(chat, BorderLayout.CENTER);
		add(enviar,BorderLayout.SOUTH);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		cliente = new ClientChat(nickname);
		control.HiloRecibirMensajesInterfaz t = new control.HiloRecibirMensajesInterfaz(this, cliente);
		t.start();
	}

	
	public void enviarMensaje(String mensaje) {
		cliente.enviarMensaje(mensaje);
	}
	public void recibirMensajes(String mensaje) {
		chat.nuevoMensaje(mensaje);
	}

}
