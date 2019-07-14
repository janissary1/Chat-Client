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
	private volatile boolean interrupted = false; //for thread killing
	
	public Client_Instance(String name,Socket tcpSocket, BufferedReader in, OutputStream out) {
		this.name = name;
		this.tcpSocket = tcpSocket;
		this.in = in;
		this.out = out;
	}
	
	public void interrupt() {
		this.interrupted = true;
	}
	
	public boolean getInterrupt() {
		return this.interrupted;
	}
	
	@Override
    public void run() {
		while (!this.interrupted){
        System.out.println("Thread: " + name +  " ...Running. Interrupt: " + this.interrupted);
        try {Thread.sleep(1000);}catch(InterruptedException e) {}
        }
		System.out.println("Thread stopped sucessfully");
		try {tcpSocket.close();}catch(IOException e) {}
	
	}

}
