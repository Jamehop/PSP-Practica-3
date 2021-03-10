package servidor;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class AtiendeCliente extends Thread{
	
	Socket conexion;
	DataInputStream entrada;
	DataOutputStream salida;
	Comunhilos Comunhilos = null;

//CONSTRUCTOR
	public AtiendeCliente(Socket conexion) throws IOException {
		this.entrada = new DataInputStream(conexion.getInputStream());
		this.conexion = conexion;
	}
	
//METODOS
	@Override
	public void run() {
		try {				
			while(true) {	
				//Lee los mensajes de los clientes y los imprime.
				String mensaje = entrada.readUTF();	
				System.out.println(mensaje);
				//Guardo los mensajes en el monitor
				
				ArrayList<Socket> listaSockets = Comunhilos.getSockets();	//Cargo la ista de los sockets
				for(Socket s : listaSockets) {
					if(s != conexion) {		//Si el socket es el mismo que el del cliente que escribe el mensaje, no se lo vuelve a enviar.
						salida = new DataOutputStream(s.getOutputStream());
						salida.writeUTF(mensaje);
					}
				}

			}

		} catch (IOException e) {
			e.printStackTrace();
		}		
	}//FIN RUN
	
		
}//FIN AtiendeCliente