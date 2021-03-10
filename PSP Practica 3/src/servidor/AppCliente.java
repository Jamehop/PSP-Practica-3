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
	static String nombreUsuario;
	static String mensajeUsuario;

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
				if(nombreUsuario == null) {
					System.out.println("Introduce tu nombre");
					nombreUsuario = Leer.pedirCadena();
					datosSalida.writeUTF(nombreUsuario + " se ha unido al chat.");
				}else {
					String escriboNombre = "[" + nombreUsuario + "]  ";					
					mensajeUsuario = Leer.pedirCadena();
					System.out.print(escriboNombre);
					System.out.println(mensajeUsuario);
					
					String nombreUserYMensaje = escriboNombre + mensajeUsuario;
					datosSalida.writeUTF(nombreUserYMensaje);						
				}				
			}while(mensajeUsuario != "*");
			
			
			socketCliente.close();
		} catch (IOException e) {			
			e.printStackTrace();
		}			
		
	}//FIN MAIN
	

}