package connection;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.net.ssl.SSLSocket;

public class ListenerThread extends Thread {

SSLSocket sslsocket;

public ListenerThread(SSLSocket sslsocket) {
    this.sslsocket = sslsocket;
}

@Override
public void run() {

    ObjectInputStream is = null;
    ObjectOutputStream os = null;
    try {
        is = new ObjectInputStream(sslsocket.getInputStream());
        os = new ObjectOutputStream(sslsocket.getOutputStream());

        while(true){
            String p = (String) is.readObject();
            System.out.println("We got: " + p);

            os.writeObject("thanks");
            os.flush();

        }//while         

    } catch (IOException ex) {
       
    } catch (ClassNotFoundException ex) {
        
    } finally {
        try {
            is.close();
            os.close();
            this.sslsocket.close();
        } catch (IOException ex) {
            
        }
    }
}//run
}
