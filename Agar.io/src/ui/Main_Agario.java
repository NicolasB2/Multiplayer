package ui;

import java.awt.Dimension;

import javax.swing.JFrame;

import controller.Login;
import model.Game;

public class Main_Agario extends JFrame {

	public static final int WINDOW_WIDTH = 1000;
	public static final int WINDOW_HEIGHT = 800;
	public static final int WINDOW_POS_X = 50;
	public static final int WINDOW_POS_Y = 50;

	private Login_GUI loginWindow;
	private Space space;
	private String nickName;
	private int id;
	
	private Game players;
	private Game food;
	
	
	// falta: connection with moving

	
	public Main_Agario() {
		initComponents();
		//this.loginWindow = new Login_GUI();
		//this.loginWindow.setVisible(true);

	}

	private void initComponents() {
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(false);
		this.setBounds(WINDOW_POS_X, WINDOW_POS_Y, WINDOW_WIDTH, WINDOW_HEIGHT);
		this.setResizable(false);
	    this.setFocusable(true);
	    this.setLocationRelativeTo(null);
	    
	    
	    this.space = new Space(this.players, food, new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
	    
	    
	}
}




	