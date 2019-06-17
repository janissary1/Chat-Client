//Written by Daniel Bellissimo
import java.net.*;
import java.io.*;

public class HostServer implements Runnable {
	
	private Thread tcpThread;
	private int portNumber = 1533;
    private ServerSocket tcpListener;
    
	public HostServer() {
		tcpThread = new Thread(this);
	}
	public void startThread() {
		this.tcpThread.start(); //when a thread is started the function returns
	}
	
	@Override
    public void run() {
        while(true){
            try{
            	tcpListener = new ServerSocket(this.portNumber) ;
                Socket tcpSocket = tcpListener.accept();
                BufferedReader in = new BufferedReader(new InputStreamReader(tcpSocket.getInputStream()));
                String input = in.readLine();
                
                //TODO: Add decryption/encryption
                //daniel:pass1
                String[] received_data = input.split(":");
                OutputStream out = tcpSocket.getOutputStream();
                PrintWriter pw = new PrintWriter(out);
                
                if(received_data[0].equals("daniel") && received_data[1].equals("pass1")){ //should do a username/password lookup and THEN spawn thread
                    
                    pw.println("Authentication Successful");
                    //Send coversation data
                    pw.println("Content-Type: image/jpeg");
                    pw.println("");
                    pw.flush();
                    out.flush();
                }
                else {
                	pw.println("Authentication Unsuccessful");
                	pw.flush();
                	out.flush();
                }
                tcpSocket.close();
            }
            catch (Exception ignore){}
        }
	}
}