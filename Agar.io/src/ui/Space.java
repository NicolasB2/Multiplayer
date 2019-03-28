package ui;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Avatar;
import model.Game;

public class Space extends Canvas {

	private Image dibujoAux;
	private Graphics gAux;
	private Dimension dimAux;
	private Dimension dimPanel; // add private final
	private int id;

	private Game avatars;
	private Game food;
	// private IGestorPlayer players;
	// private IGestorVirus virus;

	public Space(Game avatars, Game food, Dimension d) {

		this.avatars = avatars;
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
		if (gAux == null || dimAux == null || dimPanel.width != dimAux.width || dimPanel.height != dimAux.height) {
			dimAux = dimPanel;
			dibujoAux = createImage(dimAux.width, dimAux.height);
			gAux = dibujoAux.getGraphics();
		}

		// Paint background
		gAux.setColor(new Color(220, 220, 220));
		gAux.setColor(Color.WHITE);
		gAux.fillRect(0, 0, Main_Agario.WINDOW_WIDTH, Main_Agario.WINDOW_HEIGHT);
		gAux.setColor(new Color(220, 220, 220));

		int espacio = 40;
		for (int i = 0; i < dimPanel.width; i += espacio)
			gAux.drawLine(i, 0, i, dimPanel.height);
		for (int j = 0; j < dimPanel.height; j += espacio)
			gAux.drawLine(0, j, dimPanel.width, j);

		// Paint Avatars

		if (this.avatars != null) {
			try {
				this.paintPlayer(this.avatars, gAux);
			} catch (Exception e) {
				Logger.getLogger(Space.class.getName()).log(Level.SEVERE, null, e);

			}
		}

		// Paint Food
		if (this.food != null) {
			try {
				this.paintFood(this.food, gAux);
			} catch (Exception e) {
				Logger.getLogger(Space.class.getName()).log(Level.SEVERE, null, e);
			}
		}

	}

	public void paintPlayer(Game avatars, Graphics g) throws RemoteException {
		for (int i = 0; i < avatars.avatars.size(); i++) {
			try {
				Avatar a = avatars.avatars.get(i); // getPlayerIterator

			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}

	public void paintFood(Game food, Graphics g) throws RemoteException {
		for (int i = 0; i < food.food.size(); i++) {
			try {

			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}

}
