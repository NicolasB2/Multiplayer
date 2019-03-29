package connection;
import java.io.ObjectOutputStream;

import javax.net.ssl.SSLSocket;

public class ServerSenderThread extends Thread{
	
	SSLSocket sslsocket;
	private Server server;

	public ServerSenderThread(SSLSocket sslsocket,Server server) {
		this.server = server;
		this.sslsocket = sslsocket;
	}
	
	@Override
	public void run() {
		ObjectOutputStream os = null;
		try {
			
			os = new ObjectOutputStream(sslsocket.getOutputStream());
			while (true) {
				sleep(10000);
				 os.writeObject("hello friend");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				 os.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			
		}
	}
}
