import java.io.*;
import java.net.*;
import java.util.*;

class tftp1Line implements Runnable, tftp1Cons {
	//tftp1Line para lanzarse como hilo
	//Runnable identifica eventos, estdos,ect
	LinkedList <DatagramPacket> listrec;	
	DatagramSocket sock;					
	PipedOutputStream o; //el cliente recibe el pipedinstream y el objeto Line el pipedoutstream para comunicarse					
	
	
	tftp1Line ( PipedOutputStream ot, LinkedList<DatagramPacket> ll, DatagramSocket s) {
		o= ot;			
		listrec= ll;	
		sock= s;		
		Thread l = new Thread(this);	
		l.start();							
	} 


	public void run () {
		//bucle infinito esperado a recibir un paquete y lo mete en los datagrmas para que el cliente reciba la indicaci�n
		
		while (true) {			
			try {
				DatagramPacket rec =new DatagramPacket(new byte[516],516); 
											
				sock.receive(rec);	 // espera llegada de datagrama
				synchronized (listrec){ 
					listrec.addLast(rec);}	//deja datagrama recibido en la lista	
				o.write((byte)frame);// envia evento �frame� (cte=2) indicando que se ha recibido el datagrma		
				o.flush();						
			} 
			catch (IOException e) { 
				System.out.println("Line: " + e);}	 
		} //while 
	} //run
} // tftp1Line