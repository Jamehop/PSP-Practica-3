package servidor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class AtiendeServidor extends Thread{
	private Socket socket;
	private ArrayList<AtiendeServidor> threadList;
	private PrintWriter output;
	public AtiendeServidor(Socket socket, ArrayList<AtiendeServidor> threadList) {
		this.socket = socket;
		this.threadList = threadList;
	}
	
	@Override
	public void run() {
		try {
			BufferedReader input=new BufferedReader(new InputStreamReader(socket.getInputStream()));
			//todos los mensajes
			output=new PrintWriter(socket.getOutputStream(), true);
			
			while(true) {
				String outputString=input.readLine();
				if(outputString.equals("*")) {
					break;
				}
				printToAllClients(outputString);
				System.out.println("(Recibido en servidor) "+outputString);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void printToAllClients(String outputString) {
		for(AtiendeServidor miAtiendeServidor: threadList) {
			miAtiendeServidor.output.println(outputString);
		}
		
	}
}