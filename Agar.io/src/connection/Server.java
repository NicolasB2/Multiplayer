package connection;

import java.io.*;
import java.security.*;
import java.security.cert.CertificateException;

import javax.net.ssl.*;



public class Server {


public static void main(String[] args) {


	String ksPassword = "123456";
	String ctPassword = "123456";
    String ksName = "./resources/data/MyServer.jks";
    char ksPass[] = ksPassword.toCharArray();
    char ctPass[] = ctPassword.toCharArray();

    KeyStore ks;
    try {
        ks = KeyStore.getInstance("JKS");
        ks.load(new FileInputStream(ksName), ksPass);
        KeyManagerFactory kmf = 
        KeyManagerFactory.getInstance("SunX509");
        kmf.init(ks, ctPass);
        SSLContext sc = SSLContext.getInstance("TLS");
        sc.init(kmf.getKeyManagers(), null, null);
        SSLServerSocketFactory ssf = sc.getServerSocketFactory();
        SSLServerSocket s   = (SSLServerSocket) ssf.createServerSocket(8000);                

        while(true){                
            SSLSocket sslsocket = (SSLSocket) s.accept();
            System.out.println("New Client accepted");
            ListenerThread t = new ListenerThread(sslsocket);
            t.start();;      
        }

    } catch (KeyStoreException | IOException | NoSuchAlgorithmException | CertificateException | UnrecoverableKeyException | KeyManagementException ex) {
        
    }        
} 
}