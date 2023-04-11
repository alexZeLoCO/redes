package sesion4;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerFTP_TCP1 {
	private ServerSocket SSocketListen;
	private Socket SocketPeticion; 
	private DataInputStream InputBytes;
	private BufferedOutputStream OutputBytes;
	private BufferedReader TextInput;
	private PrintWriter TextOutput;
	
	ServerFTP_TCP1(){ //constructor
		try {
			this.SSocketListen = new ServerSocket(7777);
			System.out.println("FTP_TCP Server creater at port "+SSocketListen.getLocalPort());
		} catch (IOException e) {
		}
	}
	public void run() {
		String request;
		String[] commands;
		String filename;
		long size;
		boolean finish =false;
		
		while(!finish) {
			try {// esperamos por requests.
				System.out.println("TCP_IP server waiting for requests");
				SocketPeticion = SSocketListen.accept(); //Aceptamos la peticion que nos llegue
				InputBytes = new DataInputStream(SocketPeticion.getInputStream()); //Recibimos el mensaje
				OutputBytes = new BufferedOutputStream(SocketPeticion.getOutputStream());
				TextInput = new BufferedReader(new InputStreamReader(SocketPeticion.getInputStream()));
				TextOutput = new PrintWriter (SocketPeticion.getOutputStream(),true);
				System.out.println("FTP_TCP server, request for: "+ SocketPeticion.getInetAddress());
				//Procesamos el request que nos llego
				request = TextInput.readLine(); //leemos la primera linea (solo llega una linea)
				commands = request.split("");
				switch (commands[0]) {
				case "DIR": 
					ListFTP(); //llamamos al metodo ListFTP
					
				break;
				
				case "PUT":
					filename = commands[1];
					size = Integer.parseInt(commands[2]);
					ReceiveFile(filename,size);
				break;
				
				case "GET":
					filename = commands[1];
					SendFile(filename);
				break;	
				
				default:
					System.out.println(commands[0] + "unidentified");
				break;
				}
				
				SocketPeticion.close();
				
				

			} catch (Exception e) {
				finish = true;
			}
			System.out.println("TCP_TCP Server, request ended");
		}
	}
	

	void ListFTP() {
		int i;
		File nomdir = new File(".");
		File[] listfich = nomdir.listFiles();
		
		try {
			//Send the list of files
			for(i=0;i<listfich.length;i++) {
				if (listfich[i].isFile()){
					TextOutput.println(listfich[i].getName());
					
				}
				TextOutput.println("OUT");
				TextOutput.flush();
			}
			
		} catch (Exception e) {
			System.out.println("Error in the FTP site listing "+ e);
		}
	}
	//ME FALTA SENDFILE y SENDBYTES Y RECEIVEFILE
	
	public static void main(String[] args) {
		ServerFTP_TCP1 serv = new ServerFTP_TCP1();
		serv.run();
		
	}

}
