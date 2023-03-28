
import java.io.*;
import java.net.*;
import java.util.*;

interface tftp1Cons {
	static final int tout=0, close=1, frame=2;	// eventos
	static final int espera=0, recibiendo=1, acabando=2;	// estados
	static final int RRQ=1, WRQ=2, DATA=3, ACK=4,ERROR=5; // códigos de trama
	static final int ServerPort=69;  //puerto de escucha por defecto
} //tftp1Cons

public class tftp1 {
	//se encarga de levantar todos los objetos para la adjudicación
	//al cliente le enviamos outputstream
	//a line y  timer inputstream 
	 
	 public static void main (String args[]) throws IOException {
		 if (args.length!= 3){
			 throw(new RuntimeException ("argumentos: host get fichero"));
		 } else
			 if (args[1].equals("get")){
				 PipedOutputStream o = new PipedOutputStream();  //tuberia de salida
				 PipedInputStream i = new PipedInputStream(o);  //tuberia de entrada asociada a las salida
				 LinkedList<DatagramPacket> listdp=new LinkedList<DatagramPacket>();
				 DatagramSocket sock = new DatagramSocket();
				// tftp1Servidor ts = new tftp1Servidor(); // Test server
				 tftp1Timer ti = new tftp1Timer(o); // Timer manager
				 tftp1Cliente tf =new tftp1Cliente(args[2], args[0], i, ti, listdp, sock);
				 tftp1Line l = new tftp1Line(o, listdp, sock); // Line manager
				 
			} else {
				 System.out.println("comando desconocido: "+args[0]+args[1]+args[2]);}
	 } //main
} //tftp1


	
	
	