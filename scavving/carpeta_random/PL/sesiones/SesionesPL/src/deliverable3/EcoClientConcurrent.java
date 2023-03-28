package deliverable3;
										//cliente concurrente que manda y recibe lo del servidor
import java.io.*; 
import java.net.*;
	public class EcoClientConcurrent{		//clase principal
		static String theLine=" ";	
		//String de lectura de cliente
		static String server_respuesta=null;	
		//String de recepci�n de respuestas del servidor
		static Socket s_server=null;	
		//Iniciaci�n de Socket
		public static void main (String args[]) throws IOException {	//MAIN
			String host = "localhost";	//Host por defecto
			if (args.length > 0) { host=args[0]; }	//Cambio de host por defecto si se mete uno
													//si no, se usa por defecto
			try {
				s_server = new Socket(host, 22569);	//Configuraci�n de socket server
				ReceiveServer input = new ReceiveServer();		
				//ReceiveServer es una subclase para recibir mensajes del server (definida mas abajo)
				Thread hebra_input=new Thread(input);		
				//Crea la hebra a partir de ReceiveServer
				SendServer output = new SendServer();		
				//Crea la funci�n de envio a Server; SendSever (definida m�s abajo)
				Thread hebra_output=new Thread(output);		
				//Crea la hebra a partir de SendServer
				hebra_input.start();	//Lanza la hebra de recepci�n
				hebra_output.start();	//Lanza la hebra de envio
				hebra_input.join();		//Espera a que finalice la hebra de recepci�n
				hebra_output.join();	//Espera a que finalice la hebra de envio
				//.join = se usa para mantener un orden en la secuencia de los hilos
				//finalizan segun el orden del join (primero la input y luego la output)
				System.out.println("-------------------");
				System.out.println("Proceso Finalizado");	
				//Confirmaci�n de finalizaci�n de cliente, cuando ya pasaron todas las hebras su join
				System.out.println("-------------------");
			} catch (Exception e) {System.out.println(e);	//Errores
			} finally {
				try {s_server.close();	
				//Cierra el Socket del servidor
				}catch(Exception e) {System.out.println("->[No se ha podido conectar con el servidor]");}	
				//Error de cierre del socket del servidor
			}
		}
	}
//SUBCLASE PARA EL RUBAABLE DE LAS HEBRAS DE ENVIO DE MENSAJES AL SERVIDOR
class SendServer extends EcoClientConcurrent implements Runnable {	
	SendServer(){}	//Constructor por defecto
	public void run()  {	//RUN
		try {
		LineNumberReader sysIn = new LineNumberReader(	//Lector de Linea de cliente (escritura)
				new InputStreamReader(System.in));
		//LineNumberReader crea un secuencia de entrada de caracteres almacenada en el bufer
				//y realiza un seguimiento
		//InputStreamReader es como un Reader normal que lee lo que se reciba
		PrintWriter netOut = new PrintWriter(s_server.getOutputStream(), true);	
		//Salida a Servidor
		//PrintWriter = nos permite imprimir salir en streamde texto
		System.out.println("[Cliente conectado a Servidor '"+s_server.getRemoteSocketAddress()+"']");	
		//Confirmaci�n conexi�n a Servidor
		System.out.println("----------------------------------------------------");
			while (!(theLine.equals("."))) {	
				//Lee lo que escribe el cliente hasta que ponga un "." -> Condici�n de Parada
				System.out.print("->Escriba la Linea a transmitir (Escriba '.' para acabar): ");
				theLine = sysIn.readLine();	//Lee lo que escribe el CLiente por la consola
				netOut.println(theLine);	//Envio a Servidor 
				Thread.sleep(100);	
				//Espera un tiempo a que la otra hebra escriba los resultados del Servidor
				if (server_respuesta==null) {System.out.println("-->[ERROR de Conexi�n] ");}	
				//Comprueba si el servidor ha respondido a la petici�n
				//si esta vacio da error
			}																												//Si server_respuesta es null, el servidor no ha respondido y se supone que no funciona
		} catch (IOException e) {System.out.println("Error de Servidor");} catch (InterruptedException e) {	//Errores
			e.printStackTrace();
		}
	}
}
//SUBCLASE PARA EL RUNNABLE DE LAS HEBRAS DE RECEPCION DE RESUESTA DEL SERVIDOR
class ReceiveServer extends EcoClientConcurrent implements Runnable {	
	ReceiveServer(){}	//Constructor por defecto
	public void run() {	//RUN
		try {
		LineNumberReader netIn = new LineNumberReader(	
				//Lector de linea de servidor
				//y la almacena en la variable netIn
				new InputStreamReader(s_server.getInputStream()));
				//LineNumberReader crea un secuencia de entrada de caracteres almacenada en el bufer
				//y realiza un seguimiento
				//InputStreamReader es como un Reader normal que lee lo que se reciba
				//getInputStream = permite poder coger la informacion pasado como parametro
			while (!(theLine.equals("."))) {	
				//siempre que 'theline' que es el mensaje que le ha mandado
				//el cliente al servidor sea disinto de '.' un punto, sigue con el proceso
					server_respuesta=netIn.readLine();	
					//guarda el mensaje que ha leido del servidor (netIn) en la variable de server_respuesta
					if (!(server_respuesta==null)) {System.out.println(server_respuesta);}	
					//Siempre que server_respuesta no este vacio, escribe la respuesta
			}
		} catch (IOException e) {System.out.println("Error de Servidor");}	//Errores
	}
}