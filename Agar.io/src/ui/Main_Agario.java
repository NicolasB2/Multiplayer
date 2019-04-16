package ui;

import java.awt.*;
import java.util.*;

import javax.swing.*;

import connection.*;
import controller.*;
import model.*;

public class Main_Agario extends JFrame {

	public static final int WINDOW_WIDTH = 1400;
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
	}

	public void setPlayer(String nick, int id) {
		this.id = id;
		this.nickName = nick;
	}

	
	public void play() {
		ClientConnection cc = new ClientConnection(this,loginWindow.getEmail(),Server.PLAY);
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(false);
		this.setBounds(WINDOW_POS_X, WINDOW_POS_Y, WINDOW_WIDTH, WINDOW_HEIGHT);
		this.setResizable(false);
		this.setFocusable(true);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.game = new Game();
		
		System.out.println(this.game.getFood().size());
		game.addAvatar(this.nickName, this.id);
		game.startGame();
		ThreadMoving m = new ThreadMoving(id,this.getGame());
		m.start();
		initGame();
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

	
	public String [] coordenates(){
		String [] coordenates = new String [3];
		coordenates[0] = this.id+"";
		coordenates[1] = this.game.getAvatar(this.id).getCenterX()+"";
		coordenates[2] = this.game.getAvatar(this.id).getCenterY()+"";
		
		
		return coordenates;
	}
	
	public static void main(String[] args) {
		Main_Agario m = new Main_Agario();
		m.setVisible(false);
	}

}
