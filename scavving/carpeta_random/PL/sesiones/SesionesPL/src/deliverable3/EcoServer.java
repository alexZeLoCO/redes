package deliverable3;


import java.io.*;
import java.net.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.*;

									/*el programa debe funcionar como un servidor de eco
									devuelve el mensaje que le mande el cliente 
									hasta recibir un '.' que signifique fin de la conexion*/

public class EcoServer {
	static ServerSocket ecoserv = null;	//Iniciaci�n de Server Socket 'ecoserv'
	static String host = "EcoServer";	//Nombre de host predeterminado
	static ExecutorService pool_hebras= Executors.newFixedThreadPool(3);
	//Pool de 3 Hebras para atender a 3 Clientes al mismo tiempo
	//ExecutorService sirve para simplicar la ejecucuon de las tareas asincronas
	
					//INICIALIZACION DEL MAIN
	
	public static void main (String args[]) {	
		if (args.length > 0) { host=args[0]; }	
		//en caso de que args tenga contenido
		//Cambia el host por defecto, escogiendo el primer par�metro de args y lo mete como host
		try {
			ecoserv = new ServerSocket(22569);	
			//Crea el socket del servidor indicando el puerto 
			System.out.println ("["+host+" created at port 22569]");
			System.out.println ("[Servidor en funcionamiento]");
			System.out.println ("--------------------------------");
			while (true) {
				Socket conn = ecoserv.accept();	
				// Espera a que se conecte un cliente, guarda el socket del client en conn
				pool_hebras.submit(new EcoThread(conn));	
				//.submit = lanza la hebra para cada cliente que se conecte
				//EcoThread es un subclase dentro de la clase de EcoServer (definida mas abajo) con conn que es
				//donde esta almacenado el coket del cliente 
				//vamos al RUNNABLE
			}
		} catch (IOException e) {	//salta un error si alguna parte de la conexion no ha ido bien
			System.out.println ("[Error producido en "+host+"]: "+e);
		} finally {
			try {
				if (ecoserv!=null) ecoserv.close();		
				//Cierra el servidor, siempre que no sea null ya que daria un error
			} catch (Exception ex) {}
		} // finally
}}

//subclase ECOTHREAD e implemnetacion del Runnable para las Hebras que atienden a los clientes

class EcoThread extends EcoServer implements Runnable {	
private Socket c;	
//Socket de cliente
EcoThread (Socket conn) {c = conn;}  //Constructor de la subclase    c = conn es donde esta guardado el socket del cliente
	//INICIA EL RUN
	public void run() {   
	try {
		LineNumberReader i = new LineNumberReader(new InputStreamReader(c.getInputStream())); 
		//Lector de linea que va escribiendo el cliente (almacena en i)
		//LineNumberReader crea un secuencia de entrada de caracteres almacenada en el bufer
		//y realiza un seguimiento
		//InputStreamReader es como un Reader normal que lee lo que se reciba
		//getInputStrem = permite poder coger la informacion pasado como parametro
		System.out.println ("->[Connection accepted: "+c.getRemoteSocketAddress()+" at port 22569]");	
		//Confirmaci�n de conexi�n con el cliente
		//getRemoteSocketAddress = sirve para obtener la direccion ip del socket
		PrintWriter p = new PrintWriter(c.getOutputStream(), true);
		//PrintWriter = nos permite imrimir salir en streamde texto
		//getOutputStrem = devuelve la informacion del socket
		String input_client="start";	
		//Le pone un valor diferente a "." o a null para comenzar el bucle
		while (!(input_client.equals("."))) { 
			//siempre que el mensaje sea distinto de un punto '.' se lee las lineas y las saca
			input_client = i.readLine();	
			//Lectura de linea que ha mandado el cliente
			//y que se almacena en input_client ya que es el mensaje mandado por el cliente
			Date date = new Date();	
			//obtener la fecha y hora y almacenarla en la variable date
			DateFormat hourdateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			//cambiamos el formado de presentacion de la fecha y la hora
			String msg= "-->["+hourdateFormat.format(date)+"] "+host+" devuelve \""+input_client+"\" ";	
			//Mensaje a enviar para el cliente
			//Guarda en la variable msg la fecha y hora, el host y el mensaje que mand� el cliente
			//ya que tiene que enviar lo mismo porque es un servidor de eco
			if (!(input_client.equals("."))) {	
				p.println(msg);
				//imprime el contenido de msg
				System.out.println (msg + " al destinatario: "+c.getRemoteSocketAddress());
				//getRemoteSocketAddress = sirve para obtener la direccion ip del socket
			}
		}
		//cuando el mensaje que recibe el servidor si que es '.' un punto, manda mensaje de desconexio
		System.out.println ("->[Conexion finalizada de cliente: "+c.getRemoteSocketAddress()+"]");	
		//Confirmaci�n de desconexi�n con el cliente
		} catch (Exception e) {	//Errores
			System.out.println("EcoServer excepcion: " + e);
		} finally {
			try {c.close();	
			//Cierra el socket del cliente
		}catch(Exception e) {System.out.println("EcoServer excepcion: " + e);}
		}
}}

