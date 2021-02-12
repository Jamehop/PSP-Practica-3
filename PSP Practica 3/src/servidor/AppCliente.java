package servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class AppCliente {
	  static final String IP = "localhost";
	  static final int PUERTO = 4444;

	  public static void main(String[] args) throws IOException {
	    // Obtenemos conexion e inicializamos
	    Socket socket = new Socket(IP, PUERTO);

	    // Obtenemos los canales de entrada de datos y de salida
	    DataInputStream entrada = new DataInputStream(socket.getInputStream());
	    DataOutputStream salida = new DataOutputStream(socket.getOutputStream());

	    // Enviamos un mensaje y esperamos la respuesta del servidor
	    salida.writeUTF("patata");
	    String mensajeDelServidor = entrada.readUTF();
	    System.out.println("Recibido mensaje del servidor: " + mensajeDelServidor);

	    // Cerramos la conexión
	    socket.close();

	  }
	}