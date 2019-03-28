package ui;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;


public class Space extends Canvas {

	private Image dibujoAux;
	private Graphics gAux;
	private Dimension dimAux;
	private Dimension dimPanel; // add private final

	// private IGestorPlayer players;
	// private IGestorVirus virus;
	// private int id;

	public Space(Dimension d) {

		this.setSize(d);
		this.dimPanel = d;

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
		
		//Paint background
		gAux.setColor(new Color(220, 220, 220));
        gAux.setColor(Color.WHITE);
        gAux.fillRect(0, 0, Main_Agario.WINDOW_WIDTH, Main_Agario.WINDOW_HEIGHT);
        gAux.setColor(new Color(220, 220, 220));

        int espacio = 40;
        for (int i=0; i < dimPanel.width; i+=espacio)
            gAux.drawLine(i, 0, i, dimPanel.height);                    
        for (int j=0; j < dimPanel.height; j+=espacio)
            gAux.drawLine(0, j, dimPanel.width, j);

        
        
        
        
	}

}
