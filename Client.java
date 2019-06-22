import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.*;
import java.io.*;

public class Client {
	private Socket tcpSocket;
	private int portNumber = 1533;
	private InetAddress host_server; 
	
	public Client() throws Exception{
		this.host_server = InetAddress.getByName("danielbellissimo.ca");
	}
	public void connect() throws Exception {
		tcpSocket = new Socket(host_server,portNumber);
		
		OutputStream out = tcpSocket.getOutputStream();
		PrintWriter pw = new PrintWriter(out);
		pw.println("daniel:pass1");
		pw.flush();
		BufferedReader in = new BufferedReader(new InputStreamReader(tcpSocket.getInputStream()));
		String data = null;
		/*
		while ((data = in.readLine()) != null ) {
            System.out.println(data);
        }*/
	}
	
}
