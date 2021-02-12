package servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.DefaultListModel;


//http://www.chuidiang.org/java/sockets/hilos/socket_hilos.php
public class ServidorChat {
	private DefaultListModel charla = new DefaultListModel();
	public ServidorChat() throws IOException
	   {
	      // Se crea el socket servidor
	      ServerSocket socketServidor = new ServerSocket(8080);

	      while (true)
	      {
	          // Se espera y acepta un nuevo cliente
	          Socket cliente = socketServidor.accept();

	          Runnable nuevoCliente = new HilodeCliente(charla, cliente);
	          Thread hilo = new Thread(nuevoCliente);
	          hilo.start();       
	      }
	   }
}
