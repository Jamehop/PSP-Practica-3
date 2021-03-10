package servidor;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class Comunhilos {

	private final int MAX_CONEXIONES = 5;
	private int conexionesTotales;
	private int conexionesActuales = 0;
	
	private static ArrayList<Socket> sockets = new ArrayList<Socket>();
	private static ArrayList<String> mensajes;
	private static String mensaje;
	
	public Comunhilos(int MAX_CONEXIONES_SIMULTANEAS) {
	}

	public int getConexionesTotales() {
		return conexionesTotales;
	}

	public int getConexionesActuales() {
		return conexionesActuales;
	}
	
	public static void setConexionesActuales(int conexionesActuales) {
		conexionesActuales = conexionesActuales;
	}

	public static ArrayList<Socket> getSockets() {
		return sockets;
	}

	public static void setSocket(Socket socket) {
		Comunhilos.sockets.add(socket);
	}

	public static String getMensaje() {
		return mensaje;
	}
	
	public static void setMensaje(String mensaje) {
		Comunhilos.mensaje = mensaje;
	}
	
}