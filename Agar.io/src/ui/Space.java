package ui;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JPanel;

import model.Avatar;
import model.Game;

public class Space extends Canvas {

	private Image dibujoAux;
//	private Graphics gAux;
	private Dimension dimAux;
	private Dimension dimPanel; // add private final
	private int id;

	private Main_Agario main;
	private ArrayList<Avatar> food;
	private Avatar avatar;

	public Space(Main_Agario main, Avatar avatar, ArrayList<Avatar> food, Dimension d) {

		this.main = main;
		this.avatar = avatar;
		this.food = food;
		this.setSize(d);
		this.dimPanel = d;
		this.id = -1;
	}

	@Override
	public void update(Graphics g) {
		paint(g);
	}

	@Override
	public void paint(Graphics g) {


		// Paint background
		g.setColor(new Color(220, 220, 220));
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, Main_Agario.WINDOW_WIDTH, Main_Agario.WINDOW_HEIGHT);
		g.setColor(new Color(220, 220, 220));

		int espacio = 40;
		for (int i = 0; i < dimPanel.width; i += espacio)
			g.drawLine(i, 0, i, dimPanel.height);
		for (int j = 0; j < dimPanel.height; j += espacio)
			g.drawLine(0, j, dimPanel.width, j);

		// Paint Avatars

		if (main.getGame().getAvatars() != null) {
			try {
				this.paintPlayer(main.getGame().getAvatars(), g);
			} catch (Exception e) {
				Logger.getLogger(Space.class.getName()).log(Level.SEVERE, null, e);

			}
		}

		if (food == null) {
			System.out.println("food null");
		}

		// Paint Food
		if (this.food != null) {
			try {
				this.paintFood(this.food, g);
			} catch (Exception e) {
				Logger.getLogger(Space.class.getName()).log(Level.SEVERE, null, e);
			}
		}

	}

	public void setID(int value) {
		this.id = value;
	}

	private void paintOwnScore() {

	}

	private void paintLaderBoard() {

	}

	public void paintPlayer(ArrayList<Avatar> avatars, Graphics g) throws RemoteException {
		
		for (int i = 0; i < avatars.size(); i++) {
			System.out.println(avatars.get(i).getNickName());
			try {
				Avatar a = avatars.get(i);
				//				a.render(g, 1);			
				
				double r = a.getRadious();
				System.out.println(r + " ");
				g.setColor(a.getColor());
				g.fillOval((int) (a.getCenterX() - r), (int) (a.getCenterY() - r), (int) (2 * r), (int) (2 * r));
				g.setColor(Color.BLACK);
				g.drawOval((int) (a.getCenterX() - r), (int) (a.getCenterY() - r), (int) (2 * r), (int) (2 * r));

				
				
				
			} catch (Exception e) {
				System.out.print("Problem in paintPlayer");
				;
				e.printStackTrace();
			}
		}
	}

	public void paintFood(ArrayList<Avatar> food, Graphics g) throws RemoteException {
		for (int i = 0; i < food.size(); i++) {
			try {
				Avatar f = food.get(i);
				f.render(g, 1);
				
			} catch (Exception e) {
				System.out.print("Problem in paintFood");
				;
				e.printStackTrace();
			}
		}
	}

}
