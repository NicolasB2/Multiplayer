package connection;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;

import javax.net.ssl.SSLSocket;

public class ServerSenderThread extends Thread{
	
	SSLSocket sslsocket;
	private Server server;

	public ServerSenderThread(SSLSocket sslsocket,Server server) {
		this.server = server;
		this.sslsocket = sslsocket;
		ObjectOutputStream os = null;
	}
	
	@Override
	public void run() {
		ObjectOutputStream os = null;
		BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		try {
			
			os = new ObjectOutputStream(sslsocket.getOutputStream());
			while (true) {
				line = br.readLine();
				 os.writeObject(br);
		            os.flush();
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			try {
				 os.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			
		}
	}
}
