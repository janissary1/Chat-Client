import java.net.*;
import java.io.*;

public class HostServer implements Runnable {
	
	private ServerSocket tcpListener;
	private Thread tcpThread;
	private int portNumber = 1533;
	
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
                    pw.println("Content-Type: image/jpeg");
                    pw.println("");
                    pw.flush();

                    // Write the file to the output buffer
                    Files.copy(file.toPath(), out);
                    out.flush();
                }
                else {
                	
                }
                tcpSocket.close();
            }
            catch (Exception ignore){}
        }
	}
}