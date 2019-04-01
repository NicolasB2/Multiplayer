package connection;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SendingThread extends Thread{

	
	private ObjectInputStream is;
	private ObjectOutputStream os;
	private Server server;
	
	public SendingThread(ObjectInputStream is, ObjectOutputStream os, Server server) {
		this.is = is;
		this.os = os;
		this.server = server;
	}
	
	public void run() {
		while(true) {
			try {

				File file = server.sendSerializable();

				long length = file.length();
				byte[] bytes = new byte[16 * 1024];
				FileInputStream in = new FileInputStream(file);

				
				int count = in.read(bytes);
				
				while (count > 0) {
					os.write(bytes, 0, count);
					count = in.read(bytes);
					
					
				}
				
				sleep(1000);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
		}
	}
}
