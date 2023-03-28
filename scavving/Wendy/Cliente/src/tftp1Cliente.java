
import java.io.*;
import java.net.*;
import java.util.*;

class tftp1Cliente extends tftp1Frame implements Runnable, tftp1Cons {
//hereda de la clase frame, aquí definimos los codigos de trma, puertos por defecto y los estados

	String file, host;	// parámetros de activación	
	FileOutputStream fichout;	//stream de salida asociado a fichero
	PipedInputStream i;		
	tftp1Timer t;	// referencia al gestor de timers
	LinkedList<DatagramPacket> listrec;  // lista de paquetes recibidos
	int event, state = espera;	// variable de evento y estado por defecto espera
	
tftp1Cliente (String f, String h, PipedInputStream inp, tftp1Timer ti, LinkedList<DatagramPacket> ll ,DatagramSocket s) {
	//se encarga de cargar en estos atributos propios del cliente y creo el fichero que vamos a recibir	
	file = f;	// nombre de archivo
		host = h;	
		i = inp;	//pipedinputstream
		t = ti;		//gestor de timer
		listrec= ll;	//lista de datagramas
		sock = s;		//el socket
		try {
			fichout = new FileOutputStream("copiaencliente_" + file);
			a = InetAddress.getByName(host); 		//cargamos en a la ip del host (de la clase base tftp11frame)
			Thread tc = new Thread(this);			
			tc.start();
			} 
		catch (UnknownHostException e) { 
			System.out.println("tftp1Cliente excepcion: "+e);	
			}
		catch (IOException e) {
			System.out.println("tftp1Cliente excepcion: " + e);}
	} //constructor tftp1Cliente
	
public void run () {
	
	try {
		remoteTID = ServerPort;	
		SendDP(sock, (sent=RRQ(file, "netascii")));		
		//envia el datagrampacket a traves del socket asignado y saca una trama RRQ con el nombre file y el modo 

		t.startTimers();								
		state = recibiendo;								 
		while (state != espera) {
			event = i.read();	
			switch (event) {	
				case frame: 		
					 synchronized (listrec) {rec=listrec.removeFirst();}
					 if (firstDATAframe()){ remoteTID=rec.getPort();} // guarda nuevo TID remoto
					 if (rec.getPort() != remoteTID) { // trama no llega del servidor
					SendDP(sock, ERROR(0, "wrong TID"));// lo podemos quitar
					break;
					 }
					 if (code(rec)==DATA) {
					if ((seqnum(rec)) != seqnum) { // trama de datos nueva
						//en caso de que no sea la misma que teniamos crea la nueva
					 seqnum=seqnum(rec);
					 t.startTimers();
					 fichout.write(dat(rec)); //escribimos en el fichero los datos de la trama
					 }
					 SendDP(sock, (sent=ACK())); // envía (re)asentimiento
					 if (state == recibiendo) { t.startTout(); }
					 if (rec.getLength() < 516) { // última trama de datos
					 state =acabando;
					 t.stopTout();

					//redundante porque si me llega el ultimo paquete y no son 516 entonces para el Tout
					 }
					 }
					 if (code(rec)==ERROR) {
					 state = espera;
					 t.stopTimers();
					 }
					 break;
				case close:				
					state = espera;		
					break;
				case tout:				
					if (state == recibiendo) {  // reenvío de trama
						SendDP(sock, sent);		
						t.startTout();			 
					}
					break;
				default: break;
			}
		}
		fichout.close();			
		System.out.println("Archivo "+file+" recibido");
		} 
		catch( Exception e) { }	
	}  
}
