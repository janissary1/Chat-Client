import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Client_Instance implements Runnable {
	
	public String name;
	private Socket tcpSocket;
	private BufferedReader in;
	private OutputStream out; 
	private volatile boolean thread_flag = true; //for thread killing
	
	public Client_Instance(String name,Socket tcpSocket, BufferedReader in, OutputStream out) {
		this.name = name;
		this.tcpSocket = tcpSocket;
		this.in = in;
		this.out = out;
	}
	public void stop() {
		this.thread_flag = false;
	}
	
	@Override
    public void run() {
		while (thread_flag){
        System.out.println("Thread: " + name +  " ...Started Fine");
        try {
        tcpSocket.close();
		 while(thread_flag){
			 
		 }
        }catch(IOException e) {}
	}
	
	}

}
