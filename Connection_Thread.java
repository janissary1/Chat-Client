import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Connection_Thread extends Thread {
	
	public String name;
	private Socket tcpSocket;
	private BufferedReader input;
	private OutputStream output; 
	private volatile boolean run_flag = true; //for thread killing
	
	public Connection_Thread(String name,Socket tcpSocket, BufferedReader input, OutputStream output) {
		this.name = name;
		this.tcpSocket = tcpSocket;
		this.input = input;
		this.output = output;
	}
	public void terminate() {
		this.run_flag = false;
	}
	
	@Override
    public void run() {
		System.out.println("Thread: " + name +  " ...Started Fine");
		while (run_flag){
		try{super.sleep(1000);
			System.out.println(name + " thread is running");
		}catch(InterruptedException e){}

		}
		try {
			tcpSocket.close();
			System.out.println("Thread terminated");
			}catch(IOException e) {}
	
	}

}
