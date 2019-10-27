package program1b;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	
	private void run() {
		
		int portNumber = 5520;
		try {
			ServerSocket servSock = new ServerSocket(portNumber);
			while (true) {
				Socket sock = servSock.accept();
				ServerThread servThread = new ServerThread(sock);
				servThread.start();
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		
		Server server = new Server();
		server.run();
	}

}
