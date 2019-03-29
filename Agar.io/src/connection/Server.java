package connection;

import java.io.*;
import java.security.*;
import javax.net.ssl.*;

public class Server {

	public static int PORT = 8000;
	
	public Server() {
		String Password = "123456";
		String ksName = "./resources/data/MyServer.jks";
		char Pass[] = Password.toCharArray();

		KeyStore ks;
		try {
			ks = KeyStore.getInstance("JKS");
			ks.load(new FileInputStream(ksName), Pass);
			KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
			kmf.init(ks, Pass);
			SSLContext sc = SSLContext.getInstance("TLS");
			sc.init(kmf.getKeyManagers(), null, null);
			SSLServerSocketFactory ssf = sc.getServerSocketFactory();
			SSLServerSocket s = (SSLServerSocket) ssf.createServerSocket(PORT);

			System.out.println("****** Server online ******");
			while (true) {
				SSLSocket sslsocket = (SSLSocket) s.accept();
				System.out.println("New Client accepted");
				
				ServerListenerThread t = new ServerListenerThread(sslsocket);
				t.start();
				ServerSenderThread sst = new ServerSenderThread(sslsocket,this);
				sst.start();
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	public static void main(String[] args) {
Server s = new Server();
		
	}
}