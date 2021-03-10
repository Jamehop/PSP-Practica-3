package servidor;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

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
				//escribe los mensajes que llegan al socket en el appservidor
				String salidaString = entrada.readUTF();
				System.out.println(salidaString);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
