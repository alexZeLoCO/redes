
import java.io.*;
import java.net.*;
import java.util.*;

class tftp1Servidor extends tftp1Frame implements Runnable { 
	FileInputStream in;		//fileinputstream se asocia al fichero que nos piden	
	DatagramPacket recibido; //port 69, como antes
	int state=espera;  
	int event;  
		
	tftp1Servidor(DatagramPacket p) //recibe lo que envia el cliente
	{
		recibido=p;	
	}
	
	int sendDATA () throws IOException { 
		//lee la trama y lo envia por el socket auxilia
		int len; 	
		byte b[] = new byte[516];
		len = in.read(b, 4, 512); //guarda desde la posicion 4 de b y leemos 512bytes
		if (len<0) len=0; 
		SendDP(auxsock, DATA(b, (len+4))); 
		this.seqnum++;
		return len;		
	}	// envía tramas de datos del iero

	public void run() {

		try{
			
			PipedOutputStream o = new PipedOutputStream(); 
			PipedInputStream i = new PipedInputStream(o); 
			auxsock=new DatagramSocket(); 
			LinkedList<DatagramPacket> listadp= new LinkedList<DatagramPacket>();  // lista donde guardamos lo recibido para que se envie
			tftp1Timer t = new tftp1Timer(o);  
			new tftp1Line(o,listadp,auxsock); //para comunicarnos con el cliente
			remoteTID =  recibido.getPort(); 
			a = recibido.getAddress(); 
			seqnum = 1; //primer recibido
			
			if (code(recibido) == RRQ){ 
				
				String nombrefich = file(recibido);  
				in = new FileInputStream(nombrefich);
				
				t.startTimers(); 
				state = recibiendo; 
				int le = sendDATA();
				
				if (le<512)  
					state = acabando; 
								
				while (state != espera) { 
					event = i.read(); 
					switch (event) {
						case frame:  
							synchronized (listadp) {	
								recibido = listadp.removeFirst();
							}
							if (code(recibido) == ACK){ 
								//si seqnum del servidor menos uno es correcto, después de enviar aumentamos por lo que el ack debe ser anterior
								if (seqnum == seqnum(recibido)+1) {
									if (state==acabando){ 
										state=espera;  
										t.stopTimers(); 
									}
									else{ 
										le = sendDATA();
										t.startTimers();
										if(le<512)  
											state=acabando;
									} 
								} 
							} 
							if (code(recibido)==ERROR) {  
								state = espera; 
								t.stopTimers();  
							} 
						break;
						
						case close: //8 segundos sin recibir nada (fin*plazo=8segundos)
							state = espera; 
							t.stopTimers(); 
							break;
							
						case tout: // 2 segundos sin recibir nada
							if (state == recibiendo) {
								t.startTout();} 
							break;
							
						default: break;
					} // switch
				} // while
				in.close();  
				i.close();	
				System.out.println("Archivo enviado..");
			} //Cerramos el if (code(recibido) == RRQ)
			
			else { // si lo que recibimos no es un RRQ
				SendDP(auxsock,ERROR(0,"PRIMERA TRAMA NO ES RRQ"));
			} //else
		} catch (IOException e) {
			System.err.println(".... Hebra");
		}//cath
	} //run
}