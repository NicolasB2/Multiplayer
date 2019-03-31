package ui;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
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
		
		//Paint Leader board
		paintLeaderBoard(main.getGame().getTop(), g);

	}


	public void paintPlayer(ArrayList<Avatar> avatars, Graphics g) throws RemoteException {
		
		for (int i = 0; i < avatars.size(); i++) {
			try {
				Avatar a = avatars.get(i);
				render(g, 1,a);	
				
				double x = a.getCenterX();
	            double y = a.getCenterY();
	            double r = a.getRadious();
	            g.setFont(new Font("Ubuntu",Font.BOLD,(int)r/2));
	            FontMetrics metrics = g.getFontMetrics(g.getFont());
	            int xt =(int) x - metrics.stringWidth(a.getNickName())/2;
	            int yt = (int) (y + r/4) ;
	            g.drawString(a.getNickName(), xt , yt);
				
			} catch (Exception e) {
				System.out.print("Problem in paintPlayer");
				e.printStackTrace();
			}
		}
	}

	public void paintFood(ArrayList<Avatar> food, Graphics g) throws RemoteException {
		for (int i = 0; i < food.size(); i++) {
			try {
				Avatar f = food.get(i);
				render(g, 1,f);
				
			} catch (Exception e) {
				System.out.print("Problem in paintFood");
				e.printStackTrace();
			}
		}
	}
	
	public static void render(Graphics g, double scale, Avatar a) {		
		
		double r = a.getRadious();
		g.setColor(a.getColor());
		g.fillOval((int) (a.getCenterX() -r), (int) (a.getCenterY() -r), (int)(2*r),(int) (2*r));
		g.setColor(Color.BLACK);
		g.drawOval((int) (a.getCenterX() - r), (int) (a.getCenterY() -r), (int)(2*r), (int)(2*r));
	}
	
	private void paintLeaderBoard(ArrayList<Avatar> top, Graphics g){
		
		g.setColor(Color.DARK_GRAY);
        g.setFont(new Font("Ubuntu",Font.BOLD,15));
        g.drawString("LEADERBOARD", (int) dimPanel.getWidth()-150, 50);
        g.drawString("----------------", (int) dimPanel.getWidth()-175, 60);
        int i = 30;
        int pos = 1;
        
        
        g.drawString(main.getGame().reportScores(), (int)dimPanel.getWidth()-150, 75);
        
	}

}
