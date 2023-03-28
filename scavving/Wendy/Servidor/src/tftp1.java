
import java.io.*;
import java.net.*;
import java.util.*;

interface tftp1Cons {
	static final int tout=0, close=1, frame=2;	
	static final int espera=0, recibiendo=1, acabando=2;	
	static final int RRQ=1, WRQ=2, DATA=3, ACK=4,ERROR=5; 
	static final int ServerPort=69; 
} //tftp1Cons
//funciona como en el caso del cliente

public class tftp1 extends tftp1Frame implements tftp1Cons {

	public static void main (String args[]) throws IOException { 
		try{
			DatagramSocket servidor=new DatagramSocket(ServerPort);	//socket en el puerto 69
			System.out.println("Servidor creado en el puerto "+ServerPort);	
			while(true){   
				DatagramPacket p=new DatagramPacket(new byte[516],516);  
				servidor.receive(p);  
				System.out.println("Peticion de la direccion "+p.getAddress()+" en el puerto "+p.getPort());
				Runnable r=new tftp1Servidor(p);	
				Thread hilo=new Thread(r);
				hilo.start();	
			} //true		
		}catch (Exception e) {
			System.out.println("Fallo en el servidor "+e.getMessage()); 
		} //cath
	} //main } //tftp1
}