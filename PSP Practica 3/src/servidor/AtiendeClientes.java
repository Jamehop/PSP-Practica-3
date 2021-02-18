package servidor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class AtiendeClientes extends Thread{
	private static final int MAX_CONEXIONES = 10;
	private Comunhilos comunhilos;
	private Socket socket;
	
	private PrintWriter output;
	public AtiendeClientes(Socket socket, Comunhilos comunhilos) {
		this.socket = socket;
		this.comunhilos=comunhilos;
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
				comunhilos.anadirMensaje(outputString, getName());
				comunhilos.anadir(socket);
				
				System.out.println("(Recibido en servidor) "+outputString);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*private void printToAllClients(String outputString) {
		for(AtiendeServidor miAtiendeServidor: threadList) {
			miAtiendeServidor.output.println(outputString);
		}
		
	}*/
}
