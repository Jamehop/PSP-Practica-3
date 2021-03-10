package servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class AppServidor {
	static final int PUERTO = 4444;
	static final int MAX_CONEXIONES = 10;
	
	public static void main(String[] args) {
		try {
			@SuppressWarnings("resource")
			ServerSocket serverSocket = new ServerSocket();	
				
			InetSocketAddress direccion = new InetSocketAddress("localhost", PUERTO);
			serverSocket.bind(direccion);						
			System.out.println("Escuchando en puerto " + PUERTO);
			
			int conexionesContador = 0;
			while (conexionesContador<MAX_CONEXIONES) {					
				Socket clienteSocket = serverSocket.accept();	
				conexionesContador++;
				Comunhilos.setConexionesActuales(conexionesContador);	
				
				//CREO ATIENDE_CLIENTE 
				AtiendeCliente atiendeCliente = new AtiendeCliente(clienteSocket);
				atiendeCliente.start();		
				
				Comunhilos.setSocket(clienteSocket);			
			}
			System.out.println("Máximas conexiones alcanzadas.");
		} catch (IOException e) {			
			e.printStackTrace();
		}	
	}//FIN MAIN
	
	
}