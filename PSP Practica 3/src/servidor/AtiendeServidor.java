package servidor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class AtiendeServidor extends Thread{
	private Socket socket;
	private ArrayList<AtiendeServidor> listaHilos;
	private PrintWriter output;
	public AtiendeServidor(Socket socket, ArrayList<AtiendeServidor> listaHilos) {
		this.socket = socket;
		this.listaHilos = listaHilos;
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

	public void printToAllClients(String outputString) {
		for(AtiendeServidor miAtiendeServidor: listaHilos) {
			miAtiendeServidor.output.println(outputString);
		}
		
	}
}
