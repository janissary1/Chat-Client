//Written by Daniel Bellissimo
import java.net.*;
import java.util.Set;
import java.util.concurrent.Executor;
import java.io.*;

public class HostServer implements Executor {
	
	private Thread tcpThread;
	private int portNumber = 1533,connections_n = 0;
    private ServerSocket tcpListener;
    public static void main(String[] args) {
    	HostServer server = new HostServer();
    	server.serve_connections();
    }
	public HostServer() {}
	/*Start the server which handles authentication requests and then spawns threads for successfully authenticated users*/
	
	public void execute(Runnable client) {
		Thread client_thread = new Thread(client);
		client_thread.setName(((Client_Instance) client).getclientName());
		client_thread.start();
	}
	/*
	public void stop_thread(String client_name) {
		Set<Thread> threadSet = Thread.getAllStackTraces().keySet();
		for(Thread i : threadSet) {
			if(i.getName().equals(client_name)) {i.}
		}

	}*/
	
	//'Starts' the server
	public void serve_connections() {
		while(true) {
			try {
			//1| Starts socket, accepts incoming connection, creates Buffered reader object for reading data from client
			tcpListener = new ServerSocket(this.portNumber);
        	System.out.println("Listening...");
            Socket tcpSocket = tcpListener.accept();
            System.out.println("Connection Attempt from: " + tcpSocket.getRemoteSocketAddress());
            BufferedReader in = new BufferedReader(new InputStreamReader(tcpSocket.getInputStream()));
            //1\
            
            //Testing received data *REMOVE WHEN DONE*
            String data;
            String user_auth = "";
            while ((data = in.readLine()) != null ) {
                System.out.println(data);
                user_auth = data;
            }
            
            
            //TODO: Add decryption/encryption
            //2| Receives username/password data, creates Output stream on the tcpsocket and printwriter to print to connecting client that authentication was success/fail 
            //daniel:pass1
            String[] received_data = user_auth.split(":");
            OutputStream out = tcpSocket.getOutputStream();
            PrintWriter pw = new PrintWriter(out);
            System.out.println(received_data[0]);
            System.out.println(received_data[1]);
            //2\

            if(received_data[0].equals("daniel") && received_data[1].equals("pass1")){ //should do a username/password lookup and THEN spawn thread
                
            	//Spawn Thread using the already instantiated TCP socket object, input buffer and output stream
                this.execute(new Client_Instance(received_data[0],tcpSocket,in,out));
                this.connections_n += 1;
            	System.out.println("Authentication Successful");
            	pw.println("Authentication Successful");
                pw.flush();
                out.flush();
            }
            else {
            	System.out.println("Failed Authentication from: " + tcpSocket.getRemoteSocketAddress());
            	pw.println("Authentication Unsuccessful");
            	pw.flush();
            	out.flush();
            }
			}catch(IOException e) {}
		}
	}
	public boolean isTerminated() { return false;}
}