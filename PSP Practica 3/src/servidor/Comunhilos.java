package servidor;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class Comunhilos {
	private static int MAX_CONEXIONES=10;
	private int conexionesTotales;
	private int conexcionesActuales;
	private Socket[] tablaDeConexiones;
	private String mensajes;
	public Comunhilos(int MAX_CONEXIONES) {
		this.MAX_CONEXIONES=MAX_CONEXIONES;
		this.conexionesTotales = 0;
		this.conexcionesActuales = 0;
		this.mensajes="";
	}
	public int getConexionesTotales() {
		return conexionesTotales;
	}
	public void setConexionesTotales(int conexionesTotales) {
		this.conexionesTotales = conexionesTotales;
	}
	public int getConexcionesActuales() {
		return conexcionesActuales;
	}
	public void setConexcionesActuales(int conexcionesActuales) {
		this.conexcionesActuales = conexcionesActuales;
	}
	public String getMensajes() {
		return mensajes;
	}
	public void setMensajes(String mensajes) {
		this.mensajes = mensajes;
	}
	
	public synchronized void anadirMensaje(String mensaje, String nombreUsuario) throws IOException {
		String mensajeConUsuario=nombreUsuario+": "+mensaje;
		mensajes+=mensajeConUsuario;
		
		for(int i=0;i<conexcionesActuales;i++) {
			PrintWriter output=new PrintWriter(tablaDeConexiones[i].getOutputStream(), true);
			output.println(mensajeConUsuario);
		}
	}
	public synchronized void anadir(Socket conexion) throws IOException {
		tablaDeConexiones[conexionesTotales]=conexion;
		conexionesTotales++;
		conexcionesActuales++;
		
		PrintWriter output=new PrintWriter(conexion.getOutputStream(), true);
		output.println(mensajes);
		
	}
	
}
