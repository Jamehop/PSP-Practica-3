package servidor;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

public class AppCliente {
	static final String IP = "localhost";
	static final int PUERTO = AppServidor.PUERTO;
	static DataOutputStream datosSalida;
	static String nombreUser;
	static String mensajeUser;

	public static void main(String[] args) {
		
		try {
			Socket socketCliente = new Socket();					
			InetSocketAddress direccion = new InetSocketAddress(IP, PUERTO);
			socketCliente.connect(direccion);
			
			//CREAR ATIENDE_SERVIDOR para leer los mensajes del terminal y enviarselos al servidor.
			AtiendeServidor atiendeServidor = new AtiendeServidor(socketCliente);
			atiendeServidor.start();
			
			datosSalida = new DataOutputStream(socketCliente.getOutputStream());
			
			do {
				if(nombreUser == null) {
					System.out.println("Introduce tu nombre");
					nombreUser = Leer.pedirCadena();
					datosSalida.writeUTF(nombreUser + " se acaba de conectar.");
				}else {
					String escriboNombre = "[" + nombreUser + "]  ";					
					mensajeUser = Leer.pedirCadena();
					System.out.print(escriboNombre);
					System.out.println(mensajeUser);
					
					String nombreUserYMensaje = escriboNombre + mensajeUser;
					datosSalida.writeUTF(nombreUserYMensaje);						
				}				
			}while(mensajeUser != "*");
			
			
			socketCliente.close();
		} catch (IOException e) {			
			e.printStackTrace();
		}			
		
	}//FIN MAIN
	

}