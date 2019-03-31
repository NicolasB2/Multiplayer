package ui;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;

import controller.DataBase;
import controller.ThreadCollision;
import controller.ThreadFood;
import controller.ThreadMoving;
import controller.hilo;
import model.Avatar;
import model.Game;

public class Main_Agario extends JFrame {

	public static final int WINDOW_WIDTH = 1000;
	public static final int WINDOW_HEIGHT = 800;
	public static final int WINDOW_POS_X = 50;
	public static final int WINDOW_POS_Y = 50;

	private Login_GUI loginWindow;
	private Registrer registrerWindow;
	private Space space;
	private String nickName;
	private int id;

	private ThreadMoving moving;
	private ThreadCollision collision;
	private ThreadFood tfood;

	private Game game;

	// falta: connection with moving

	public Main_Agario() {

		this.loginWindow = new Login_GUI(this);
		this.loginWindow.setVisible(true);

//		play();
	}

	public void setPlayer(String nick, int id) {
		this.id = id;
		this.nickName = nick;
		space.setID(id);
	}

	public void play() {

		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(false);
		this.setBounds(WINDOW_POS_X, WINDOW_POS_Y, WINDOW_WIDTH, WINDOW_HEIGHT);
		this.setResizable(false);
		this.setFocusable(true);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		game = new Game();
		initGame();
		setPlayer("dani flow latino", 1);
		game.startGame(1);
		hilo h = new hilo(space);
		h.start();

	}

	private void initGame() {
		// Add player with socket
		ArrayList<Avatar> players = game.getAvatars();
		ArrayList<Avatar> food = game.getFood();

		this.space = new Space(this, game.getAvatar(id), game.getFood(), new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
		this.space.setFocusable(false);
		this.setIgnoreRepaint(false);
		this.add((Component) this.space);
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

	public void openWindowRegistrer() {
		registrerWindow = new Registrer(this);
		registrerWindow.setVisible(true);

	}

	public void closeRegistre() {
		registrerWindow.setVisible(false);
	}

	public static void main(String[] args) {
		Main_Agario m = new Main_Agario();
		m.setVisible(false);
	}

}
