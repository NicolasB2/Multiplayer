package connection;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

import ui.Main_Agario;

public class ReadingThread extends Thread{

	private ObjectOutputStream os;
	private ObjectInputStream is;
	private Main_Agario main; 

	public ReadingThread(ObjectOutputStream os, ObjectInputStream is, Main_Agario main) {
		this.os = os;
		this.is = is;
		this.main = main;
	}
	
	public void run() {
		
		OutputStream out;
		
		while(true) {
			try {
				out = new FileOutputStream("./resources/data/saveGame.txt");
				byte[] bytes = new byte[16 * 1024];

				int count;
				while ((count = is.read(bytes)) > 0) {
					out.write(bytes, 0, count);
					
				}
				
				String[] x = main.coordenates();
				os.writeObject(x[0]);
				os.writeObject(x[1]);
				os.writeObject(x[2]);
				
				out.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	
}
