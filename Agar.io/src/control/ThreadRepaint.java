package control;

import java.util.logging.Level;
import java.util.logging.Logger;

import gui.Main_Agario;
import gui.Space;

public class ThreadRepaint extends Thread {

	private Space space;

	public ThreadRepaint(Space space) {

		this.space = space;

	}

	@Override
	public void run() {
		while (true) {

			try {
				this.space.repaint();
				sleep(150);
			} catch (Exception ex) {
				System.out.println("Error in repaint");
			}
		}

	}

}
