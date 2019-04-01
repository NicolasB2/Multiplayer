package connection;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class ReadingThread extends Thread{

	private ObjectOutputStream os;
	private ObjectInputStream is;

	public ReadingThread(ObjectOutputStream os, ObjectInputStream is) {
		this.os = os;
		this.is = is;
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
				out.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	
}
