package servidor;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class AtiendeCliente extends Thread {

	Socket conexion;
	DataInputStream entrada;
	DataOutputStream salida;
	Comunhilos Comunhilos = null;

	public AtiendeCliente(Socket conexion) throws IOException {
		this.entrada = new DataInputStream(conexion.getInputStream());
		this.conexion = conexion;
	}

	@Override
	public void run() {
		try {
			while (true) {
				// Lee los mensajes de los clientes y los imprime.
				String mensaje = entrada.readUTF();
				System.out.println(mensaje);

				ArrayList<Socket> listaSockets = Comunhilos.getSockets();
				for (Socket s : listaSockets) {
					// para evitar que se duplique el mensaje
					if (s != conexion) {
						salida = new DataOutputStream(s.getOutputStream());
						salida.writeUTF(mensaje);
					}
				}

			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}