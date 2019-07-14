//Written by Daniel Bellissimo
import java.net.*;
import java.util.ArrayList;
import java.util.Set;
import java.util.concurrent.Executor;
import java.io.*;

public class HostServer implements Executor {
	
	private Thread tcpThread;
	private int portNumber = 1533,connections_n = 0;
    private ServerSocket tcpListener;
    private ArrayList<Client_Instance> client_instances = new ArrayList<Client_Instance>();
    public static void main(String[] args) {
    	HostServer server = new HostServer();
    	server.serve_connections();
    }
	public HostServer() {}
	/*Start the server which handles authentication requests and then spawns threads for successfully authenticated users*/
	
	public void execute(Runnable client) {
        Client_Instance client_h = ((Client_Instance) client);
        this.client_instances.add(client_h);
		Thread client_thread = new Thread(client_h);
		client_thread.setName(client_h.name);
		client_thread.start();
	}
	public void printThreadNames() {
		Set<Thread> threadSet = Thread.getAllStackTraces().keySet();
        for(Thread i : threadSet){System.out.println(i.getName());}
	}
	
	public void Interrupt_thread(String client_name) {
		for(Client_Instance i : client_instances) {if(i.name.equals(client_name)) {
			System.out.println("Attempting to Interrupt:" + i.name);
			i.interrupt();}
		}

	}
	
	//'Starts' the server
	public void serve_connections() {
        //1| Starts socket, accepts incoming connection, creates Buffered reader object for reading data from client
        try{tcpListener = new ServerSocket(this.portNumber);}catch(IOException e){}
		while(true) {
			try {
        	System.out.println("Listening for a connection...");
            Socket tcpSocket = tcpListener.accept(); //blocks here
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
                this.printThreadNames();
                
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