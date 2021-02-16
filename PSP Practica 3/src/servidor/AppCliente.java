package servidor;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class AppCliente {
	static final String IP = "localhost";
	static final int PUERTO = 4444;

	public static void main(String[] args) throws IOException {
		// Obtenemos conexion e inicializamos
		Socket socket = new Socket(IP, PUERTO);
		
		BufferedReader input=new BufferedReader(new InputStreamReader(socket.getInputStream()));
		PrintWriter output=new PrintWriter(socket.getOutputStream(), true);
		String inputUsuario;
		String nombreUsuario="";
		AtiendeCliente hiloCliente=new AtiendeCliente(socket);
		hiloCliente.start();

		do {
			if(nombreUsuario.equals("")) {
				System.out.println("Introduce tu nombre");
				inputUsuario=Leer.pedirCadena();
				nombreUsuario=inputUsuario;
				output.println("[Nuevo cliente se ha unido "+nombreUsuario+"]");
				if(inputUsuario.equals("*")) {
					break;
				}
			}
			else {
				inputUsuario=Leer.pedirCadena();
				output.println(nombreUsuario+" dice: "+inputUsuario);
				if(inputUsuario.equals("*")) {
					break;
				}
				
			}
		}while(!inputUsuario.equals("*"));
		
		/*
		DataInputStream entrada = new DataInputStream(socket.getInputStream());
		DataOutputStream salida = new DataOutputStream(socket.getOutputStream());

		System.out.println("Escribe lo que sea (* para cerrar):");
		String string = Leer.pedirCadena();

		// Enviamos un mensaje y esperamos la respuesta del servidor
		salida.writeUTF("Hola desde el cliente");
		String mensajeDelServidor = entrada.readUTF();
		System.out.println("Recibido mensaje del servidor:\n " + mensajeDelServidor);
		string = Leer.pedirCadena();

		// Cerramos la conexión
		socket.close();
		System.out.println("Cliente cerrado");*/

	}
}