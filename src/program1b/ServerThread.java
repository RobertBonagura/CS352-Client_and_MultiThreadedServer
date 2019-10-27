package program1b;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerThread extends Thread{
	
	Socket sock;
	PrintWriter writeSock;
	PrintWriter writeLog;
	BufferedReader readSock;
	
	public ServerThread(Socket s) {
		try {
			sock = s;
			writeSock = new PrintWriter(sock.getOutputStream(), true);
			readSock = new BufferedReader(new InputStreamReader(sock.getInputStream()));
		} catch (IOException e){
			e.printStackTrace();
		}
	}
	
	public void run() {
		boolean quitTime = false;
		try {
			while (!quitTime) {
				String inLine = readSock.readLine();
				if (inLine.contentEquals("quit")) {
					writeSock.println("Goodbye!");
					quitTime = true;
				} else {
					//String outLine = inLine + "HaHa!";
					String outLine = PolyAlphabet.convert(inLine);
					writeSock.println(outLine);
				}
			}
			sock.close();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}

}
