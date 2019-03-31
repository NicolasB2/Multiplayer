package controller;

import java.util.logging.Level;
import java.util.logging.Logger;

import ui.Main_Agario;
import ui.Space;

public class hilo extends Thread {

	private Space space;
	
	public hilo(Space space) {
	
		this.space = space;
		
	}
	
	@Override
	public void run() {
		while (true) {

			try {
	                Thread.sleep(1000);
	                this.space.repaint();

			} catch (Exception ex) {
				System.out.println("Error");
				Logger.getLogger(Main_Agario.class.getName()).log(Level.SEVERE, null, ex);

			}
		}

		
	
	}

		
}
