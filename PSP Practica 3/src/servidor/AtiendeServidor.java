package servidor;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

//Recibe los mensajes del servidor y los escribe en el terminal.
public class AtiendeServidor extends Thread {

	Socket conexion;
	DataInputStream entrada;

	public AtiendeServidor(Socket conexion) throws IOException {
		this.entrada = new DataInputStream(conexion.getInputStream());
		this.conexion = conexion;
	}

	@Override
	public void run() {
		try {		
			while (true) {
				String salidaString = entrada.readUTF(); // El readUTF es bloqueante, por eso lo ponemos en un hilo.
				System.out.println(salidaString);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
