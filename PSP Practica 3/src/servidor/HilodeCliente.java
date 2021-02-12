package servidor;

import java.net.Socket;

import javax.swing.DefaultListModel;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;

public class HilodeCliente implements Runnable {
	public HilodeCliente(DefaultListModel charla, Socket socket) {

	}

	public void run() {
		while (true) {
			// Código para atender al cliente.
		}
	}
}
