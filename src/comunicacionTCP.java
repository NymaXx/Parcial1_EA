import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class comunicacionTCP extends Thread {

	private Socket socket;
	private BufferedReader reader;
	private BufferedWriter writer;
	
	private Main main;
	
	public comunicacionTCP(Main main) {
		this.main = main;
	}
	
	//Hilo de recepcion

	public void run() {
		try {
			ServerSocket server = new ServerSocket(5000);
			System.out.println("Esperando...");
			this.socket = server.accept();
			System.out.println("Conexi?n aceptada");

			// Reader
			InputStream is = socket.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			this.reader = new BufferedReader(isr);

			// Writer
			OutputStream os = socket.getOutputStream();
			OutputStreamWriter osw = new OutputStreamWriter(os);
			this.writer = new BufferedWriter(osw);

			while (true) {
				recibirMensaje();

			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//Esperar conexion
	public void esperarConexion() {
		this.start();
	}

	// Mandar un mensaje
	public void mandarMensaje(String mensaje) {
		new Thread(

				() -> {
					try {
						writer.write(mensaje + "\n");
						writer.flush();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

		).start();

	}
	
	//Recibir mensaje
	public void recibirMensaje() throws IOException {
		String line = reader.readLine();
		//System.out.println(line);
		main.setArregloRec(line.split(","));
		float x = Float.parseFloat(main.getArregloRec()[0]);
		float y = Float.parseFloat(main.getArregloRec()[1]);
		String recordatorio = main.getArregloRec()[2];
		String importancia = main.getArregloRec()[3];
		
		main.getList().add(new Recordatorio(x, y, recordatorio, importancia));
		
		/*String gg;
		for(int i=0; i>main.getList().size(); i++) {
			 gg = main.getList().get(i).getX() + " " +main.getList().get(i).getY() + " " + main.getList().get(i).getRecordatorio();
			
		};
		System.out.println(gg);*/
		
	}
	
	//Cerrar conexion
	public void cerrarConexion() {
		try {
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
