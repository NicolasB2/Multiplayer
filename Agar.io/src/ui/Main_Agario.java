package ui;

import java.awt.*;
import java.io.*;
import java.net.*;
import java.util.*;
import javax.swing.*;
import connection.*;
import control.*;
import model.*;

public class Main_Agario extends JFrame {

	public static final int WINDOW_WIDTH = 1400;
	public static final int WINDOW_HEIGHT = 800;
	public static final int WINDOW_POS_X = 50;
	public static final int WINDOW_POS_Y = 50;

	private Login_GUI loginWindow;
	private Registrer registrerWindow;
	private Space space;
	private Controller controller;

	public Main_Agario() {
		controller = new Controller();
		this.loginWindow = new Login_GUI(this);
		this.loginWindow.setVisible(true);
	}
	
	public void play() {
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(false);
		this.setBounds(WINDOW_POS_X, WINDOW_POS_Y, WINDOW_WIDTH, WINDOW_HEIGHT);
		this.setResizable(false);
		this.setFocusable(true);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
		controller.startGame();
		controller.startMoving();
		paintGame(controller.getAvatar(),controller.getGame().getFood());
	}

	private void paintGame(Avatar avatar, ArrayList<Avatar> food) {
		// Add player with socket

		this.space = new Space(controller.getGame(), new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
		this.space.setFocusable(false);
		this.setIgnoreRepaint(false);
		this.add((Component) this.space);
		
		ThreadRepaint h = new ThreadRepaint(space);
		h.start();
	}


	@Override
	public void paint(Graphics g) {
		if (this.space != null) {
			this.space.paint(g);
		}
	}

	public void openWindowRegistrer() {
		registrerWindow = new Registrer(this);
		registrerWindow.setVisible(true);

	}

	public void closeRegistre() {
		registrerWindow.setVisible(false);
	}

	
//	public String [] coordenates(){
//		String [] coordenates = new String [3];
//		coordenates[0] = this.id+"";
//		coordenates[1] = this.game.getAvatar(this.id).getCenterX()+"";
//		coordenates[2] = this.game.getAvatar(this.id).getCenterY()+"";	
//		return coordenates;
//	}
	

	public int askPort(String request) {
		return controller.answerPort(request);
	}
	
	
	public Controller getController() {
		return controller;
	}

	public void setController(Controller controller) {
		this.controller = controller;
	}

	public static void main(String[] args) {
		Main_Agario m = new Main_Agario();
		m.setVisible(false);
	}





}
