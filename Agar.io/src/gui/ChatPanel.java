package gui;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import java.awt.*;

public class ChatPanel extends JPanel {

	
	private JTextArea area;
	private Chat_GUI p;
	private String m;
	
	public ChatPanel(Chat_GUI principal) {
		// TODO Auto-generated constructor stub
		p = principal;
		m = "";
		setLayout(new BorderLayout());
		setSize(new Dimension(510,480));
		this.setBorder(new TitledBorder(new EtchedBorder(),"Chat"));
		//510*85
	    area= new JTextArea();
	    area.setSize(new Dimension(510,400));
	    area.setEditable(false);
	    area.setBackground(Color.WHITE);
	    JScrollPane scroll = new JScrollPane(area);
	    add(scroll, BorderLayout.CENTER);     
	}
	
	public void refrescarPanel() {
		this.repaint();
	}
	
	public void nuevoMensaje(String mensaje) {
		m += mensaje + "\n";
		area.setText(m);
		refrescarPanel();
	}
}
