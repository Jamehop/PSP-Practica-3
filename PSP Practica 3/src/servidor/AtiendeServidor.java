package servidor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class AtiendeServidor extends Thread{
	static final String IP = "localhost";
	static final int PUERTO = 4444;
	private Socket socket;
	private PrintWriter output;
	public AtiendeServidor(Socket socket) throws IOException {
		this.socket = socket;
		
	}

	@Override
	public void run() {
		
		try {
			
			BufferedReader input =new BufferedReader(new InputStreamReader(socket.getInputStream()));
			output=new PrintWriter(socket.getOutputStream(), true);
			while (true) {
				String respuesta = input.readLine();
				System.out.println(respuesta);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
