import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Client_Instance implements Runnable {
	
	private String client;
	private Socket tcpSocket;
	private BufferedReader in;
	private OutputStream out; 
	private boolean thread_flag = true; //for thread killing
	
	public Client_Instance(String client,Socket tcpSocket, BufferedReader in, OutputStream out) {
		this.client = client;
		this.tcpSocket = tcpSocket;
		this.in = in;
		this.out = out;
	}
	public String getclientName() {
		return this.client;
	}
	public void stop() {
		this.thread_flag = false;
	}
	
	@Override
    public void run() {
        System.out.println("Thread: " + client +  " ...Started Fine");
        try {
        tcpSocket.close();
		 while(thread_flag){
			 
		 }
        }catch(IOException e) {}
        
	
	}

}
