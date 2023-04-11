package deliverable3;


import java.io.*;
import java.net.*;   //ES EL ECOCLIENT DEL GUION DE JAVA
	public class EcoClient {
		public static void main (String args[]) {			//MAIN
			String host = "localhost", theLine;
					if (args.length > 0) { host=args[0]; }
			Socket s=null;
			try {
				s = new Socket(host, 22569);
				LineNumberReader netIn = new LineNumberReader(
						new InputStreamReader(s.getInputStream()));
				PrintWriter netOut = new PrintWriter(s.getOutputStream(), true);
				LineNumberReader sysIn = new LineNumberReader(
						new InputStreamReader(System.in));
				while (true) {
					System.out.print("linea ('.' para acabar): ");
					theLine = sysIn.readLine();
					if (theLine.equals(".")) {
						netOut.println(".");	
						//Peque�a modificaci�n de cliente necesaria para que el servidor pueda saber 
						//que el cliente ha solicitado la desconexi�n
						break;					
						//Sin esta instrucci�n el Servidor funciona perfectamente pero 
						//dar�a un Connect Reset (Faltar�a confirmaci�n de desconexi�n)
						}
					netOut.println(theLine);
					System.out.println(netIn.readLine());
				}
			} catch (UnknownHostException e) {
				System.out.println(e);
			} catch (IOException e) {System.out.println("Error de Servidor");
			} finally {
				try{s.close();
				}catch(Exception e) {System.out.println("Error de Servidor");}
			}
		}
	}