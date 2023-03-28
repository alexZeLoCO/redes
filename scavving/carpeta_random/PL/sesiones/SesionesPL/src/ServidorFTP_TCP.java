import java.io.* ; import java.net.*; import java.util.concurrent.ExecutorService; import java.util.concurrent.Executors;


public class ServidorFTP_TCP {

		public static void main(String args[]) {
			
		ServerSocket SocketEscuchar;
		
		System.out.println("ServidorFTP_TCP esperando peticiones");
		
		try{
		
			//lanzamos el socket
			
			SocketEscuchar=new ServerSocket(7777);
			
			System.out.println("ServidorFTP_TCP created at port "+ SocketEscuchar.getLocalPort());
			
			//Generamos los hilos que darán servicio a los clientes y los lanzamos
			
			hilo_FTP[] arrayhilos=new hilo_FTP[3];
			
			for(int i=0; i<3;i++){
			
				arrayhilos[i]=new hilo_FTP(SocketEscuchar);
			
				ExecutorService pool=Executors.newFixedThreadPool(3);
			
				pool.execute(new hilo_FTP(SocketEscuchar));
			
			}
					
			//comprobamos que no hay errores	
		
		}catch (IOException excepcion) { System.out.println(excepcion); }
		
		}
}


	 class hilo_FTP extends Thread {
		
		private DataInputStream EntradaB;  //la entrada de datos ( en bytes )
		
		private BufferedOutputStream SalidaB;  //la salida de datos ( en bytes )
		
		private BufferedReader EntradaTexto; //para leer la entrada de datos
		
		private PrintWriter SalidaTexto;  //para mostrar desde el server
		
		private ServerSocket SocketEscuchar; //socket del servidor para escuchar lo que pide el cliente
		
		private Socket Peticion;          // puerto por donde nos va a llegar la peticion
		
		//definimos el constuctor
		
		hilo_FTP(ServerSocket SocketEscucharr){ SocketEscuchar=SocketEscucharr;	}



	public void run() {
		
		String peticion;       			 //lo que nos pide
		
		String[] comandos;     			 //la fragmentacion de la peticion
		
		String nombrefichero;   		 //nombre del fichero a subir o enviar
		
		long tamano;					 //tamaño del fichero
		
		boolean finalizar = false;		 //variable para 
		

		while(!finalizar) {				//el servidor esta todo el rato escuchando (en este caso, while (true))

			try {// esperar peticiones de los clientes



				System.out.println("ServidorFTP_TCP esperando peticiones");
				
				Peticion = SocketEscuchar.accept();								//aceptamos la peticion que llegue
				
				EntradaB= new DataInputStream(Peticion.getInputStream());		
				
				SalidaB= new BufferedOutputStream(Peticion.getOutputStream());
				
				EntradaTexto = new BufferedReader(new InputStreamReader(Peticion.getInputStream()));
				
				SalidaTexto = new PrintWriter(Peticion.getOutputStream(), true);
				
				System.out.println("ServidorFTP_TCP, peticion de "+Peticion.getInetAddress());
				
				peticion =EntradaTexto.readLine();		//el string peticion sera lo que entra al server
				
				comandos=peticion.split(" ");			//separamos la peticion por espacios para luego analizarla
				

				
				//aqui miramemos cual es la primera palabra de la peticion y asi hacer la accion correspondiente
				
				switch (comandos[0]) {
				
				case "DIR":
				case "dir":
					
					ListarFTP();				//si nos pide un directorio, listamos
					
					break;
					
				case "PUT":						//si nos pide PUT, recogemos el el fichero a subir
				case "put":
					
					nombrefichero = comandos[1];
					
					tamano = Integer.parseInt(comandos[2]);
					
					System.out.println("tamano: "+ tamano);
					
					RecibirFichero(nombrefichero, tamano);
					
					break;

				case "GET":							//si nos pide GET, le enviamos el fichero
				case "get":
					
					nombrefichero = comandos[1];
					
					EnviarFichero(nombrefichero);
					
					break;


				default:
					
					System.out.println(comandos[0]+ " no identificado");		
					//en caso de no ser ni PUT, GET o DIR, le decimos que no es valido
					break;

				} 
				
				Peticion.close();

			}
			
			catch(Exception e) {
				
				System.out.println("ERROR EN EL RUN " + e);
				
				finalizar=true;
			}
			System.out.println("ServidorFTP_TCP, fin peticion");

		}

	}


	void ListarFTP() {
		
		int i;
		
		
		File nomdir= new File(".");
		
		File[] listfich= nomdir.listFiles();

		try {// enviar la lista de ficheros
			
			SalidaTexto.println("#INICIO");
			
			for(i= 0; i < listfich.length; i++)		//recorremos el directorio mirando si estan los archivos
				
				if (listfich[i].isFile()){SalidaTexto.println(listfich[i].getName());}

			SalidaTexto.println("#FIN#");
			
			SalidaTexto.flush();

		} catch(Exception e) {System.out.println("Error en el listado del sitio FTP: "+e);}
		
	} // ListarFTP

	
	void EnviarFichero(String nombrefichero) {
		File fich= new File(nombrefichero);
		long tamano;
		try {
		 if(fich.isFile()) { 
		 tamano = fich.length(); // obtener el tamaño del fichero
		 SalidaTexto.println("OK " + tamano); // enviar contestación
		 SalidaTexto.flush();
		String resp = EntradaTexto.readLine(); // lee READY 
		 EnviarBytes (fich, tamano); // enviar el fichero
		 } else {
		SalidaTexto.println("ERROR");
		 }
		} catch(Exception e) {
		System.out.println("Error en el envio del fichero: " + e);
		 }
		 } // EnviarFichero
	
	
	void EnviarBytes(File fich, long tamano) {

		try {
			
			BufferedInputStream fichbis= new BufferedInputStream(new FileInputStream(fich));

			int dato;
			
			System.out.println("leo el fichero byte a byte ");
			
			for(long i= 0; i< tamano; i++) {
			
				dato = fichbis.read();
				
				System.out.println(dato);
				
				SalidaB.write(dato);
				
				//SalidaTexto.(dato);

			}
			
			SalidaB.flush();
			
			fichbis.close();

		} catch(Exception e) {System.out.println("Error en el envio del fichero binario: "+e);}

	} 


	void RecibirFichero(String nombrefichero, long tamano) {
		
		try {     // leer el tamaño del fichero
			
			SalidaTexto.println("READY");  // enviar comando READY al cliente
			
			SalidaTexto.flush();
			
			RecibirBytes(nombrefichero, tamano);
			
		} catch(Exception e) {System.out.println("Error en la recepción del fichero: " + e);}
		
	} // RecibirFicher


	void RecibirBytes(String nomfich, long size){
		
		int dato;
		
		nomfich="copiaenservidor_"+nomfich;
		
		System.out.println("cambio nombre fichero a: "+nomfich);
		
		try { 
			
			BufferedOutputStream fichbos= new BufferedOutputStream(new FileOutputStream(nomfich));
			
			//SalidaTexto.println("ESCRIBE SU CONTENIDO: ");
			
			for(long i= 0; i<size; i++) {		//leemos el fichero byte a byte
				
				dato = EntradaB.readByte();
			
				fichbos.write(dato);
			
			}
			
			fichbos.close();
			
		} catch(Exception e) {
			
			System.out.println("Error en la recepcion del fichero binario: " + e);
		}

	} // RecibirBytes
	}


