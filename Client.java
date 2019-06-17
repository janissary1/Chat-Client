import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
	private Socket tcpSocket;
	private int portNumber = 1533;
	private InetAddress host_server; 
	
	public Client() throws Exception{
		this.host_server = InetAddress.getByName("danielbellissimo.ca");
	}
	public void Connect() throws Exception {
		tcpSocket = new Socket(host_server,portNumber);

	}
	
}
