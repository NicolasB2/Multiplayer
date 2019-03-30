package ui;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.logging.Level;
import java.util.logging.Logger;

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

	private Game game;

	// falta: connection with moving

	public Main_Agario() {
		game = new Game();
		initComponents();
//		this.loginWindow = new Login_GUI();
//		this.loginWindow.setVisible(true);
		this.id = 1;
		this.space = new Space(this, game.getAvatar(id), game.getAvatars(),  new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));

	}

	public void play() {

		// esperar que se logee el usuario y pasen los 2 min de espera

		this.createBufferStrategy(2);
		this.setLocationRelativeTo(null);
		this.setIgnoreRepaint(false);
		
	
		
		 while(true){
	            this.space.repaint();
	            try {
	                Thread.sleep(10);
	            } catch (InterruptedException ex) {
	                Logger.getLogger(Main_Agario.class.getName()).log(Level.SEVERE, null, ex);
	            }
	        }

	}

	private void initComponents() {

		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(false);
		this.setBounds(WINDOW_POS_X, WINDOW_POS_Y, WINDOW_WIDTH, WINDOW_HEIGHT);
		this.setResizable(false);
		this.setFocusable(true);
		this.setLocationRelativeTo(null);

		// Add player with socket

		this.space = new Space(this,game.getAvatar(id), game.getFood(), new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
		this.space.setFocusable(false);
		this.setIgnoreRepaint(false);
		this.add((Component) this.space);

		/*
		 * this.addKeyListener(new KeyAdapter(){
		 * 
		 * @Override public void keyPressed(KeyEvent e) { formKeyPressed(e); } });
		 */ // For the split

	}
	
	public Game getGame() {
		return game;
	}

	@Override
	public void paint(Graphics g) {
		if (this.space != null) {
			this.space.paint(g);
		}
	}

	public static void main(String[] args) {
		Main_Agario m = new Main_Agario();
		m.setVisible(true);
	}
}
