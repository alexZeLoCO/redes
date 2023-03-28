
import java.io.*;
import java.net.*;
import java.util.*;

 class tftp1Timer implements Runnable, tftp1Cons { 
	static final int plazo=2, fin=4;					
	int[] timer = {0,0} ;  // array con contadores de los dos timers
	PipedOutputStream o; // stream para envío de timeouts
	
	tftp1Timer (PipedOutputStream ot) {		
		o = ot;		//asocia el pideoutput con o					
		Thread t = new Thread(this); 	
		t.start(); 		
		//el hilo ejecuta el emtrodo rn
	} //Timer constructor
	
	synchronized void startTout () {	
		timer[tout] = plazo; 	
	} 
	
	synchronized void stopTout () { 
		timer[tout] = 0; 			
	}

	synchronized void startTimers (){ // arranca los dos timers 
		timer[tout] = plazo;			//en el timer tout coloca plazo  (2)
		timer[close] = plazo*fin;		//en el timer close coloca plazo*fin (2*4)
	}
	
	synchronized void stopTimers () { 
		timer[tout] = 0;		
		timer[close] = 0;
	}

	
	public void run () {
		try {
			while (true) {	
				Thread.sleep (1000);	// espera 1 segundo (no se ejecuta)
				synchronized (this) {	//para no poder acceder al timer desde el cliente
					for (int i=0; i < timer.length; i++) {		
						if (timer[i] > 0)				 
							if (--timer[i] == 0) {	// decrementa timer[i] y compara
								o.write((byte)i); o.flush();  // envía timeout	
																
							}
					}  
				} 
			}
		} 
		catch (IOException e) {
			System.out.println("tftp1Timer: " + e);
		}
		catch (InterruptedException e) {
			System.out.println("tftp1Timer: "+e); 
		}
	} 
}

