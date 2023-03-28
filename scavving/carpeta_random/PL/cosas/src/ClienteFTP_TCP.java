import java.io.* ; import java.net.*;

public class ClienteFTP_TCP {





	public static void main(String args[]) {


		String host =args[0];					//el nombre del host es el argumento 0
		
		String Linea;
		
		boolean Verificar = false;

		Socket socket;						

		DataInputStream EntradaBytes;			//para leer la entrada de datos
		
		BufferedOutputStream SalidaBytes;		//para enviar datos
		
		BufferedReader EntradaTexto;			//para leer la entrada de texto
		
		PrintWriter SalidaTexto;				//para enviar texto


		if(args.length==0 || args.length>3)
			
			System.out.println("NUMERO DE ARGUMENTOS INVALIDO");

		else {


			try {
				
				socket = new Socket(host, 7777);
				
				EntradaTexto = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				
				SalidaTexto = new PrintWriter(socket.getOutputStream(), true);
				
				SalidaBytes= new BufferedOutputStream(socket.getOutputStream());
				
				EntradaBytes= new DataInputStream(socket.getInputStream());		

				
				
				
				
				
				if(args[1].equals("DIR") || args[1].equals("dir")) {
					
					
					SalidaTexto.println(args[1]);						//le enviamos a server el comando DIR
					
					

					try {

						//System.out.println("hemos entrado en el try");

						Linea = EntradaTexto.readLine();				//leemos lo que nos llega del server

						System.out.println("Los ficheros presentes en el directorio son:\n ");

						while(true) {
								
							
							if(Linea.contentEquals("#FIN#"))			//mostramos los ficheros y cuando leamos #FIN# paramos
								
								System.exit(0);
							
							System.out.println(Linea);
							
							Linea = EntradaTexto.readLine();
							
						}

					} catch (IOException e) {
						
						System.out.println("ERROR EN EL DIR DE CLIENTE: "+ e);
						
					}
					
					Verificar = true;

				}

				




				

				if(args[1].equals("PUT") || args[1].equals("put")) {

					String nombrefichero = args[2];				//el nombre del fichero es el argumento 2
					File fich= new File(nombrefichero);			//hacemos el fichero de tipo File
					long tamano;
					try {
						if(fich.isFile()) {   
							tamano = fich.length();    													// obtener el tamaño del fichero
							String tamano_str = Long.toString(tamano);									//obtenemos el tamaño del fichero
							SalidaTexto.println(args[1] + " " + args[2] + " "+ tamano_str );			//le enviamos a server el comando DIR

							System.out.println("Le enviamos al server el fichero "+nombrefichero);		
							System.out.println("SU TAMAÑO ES " + tamano_str);
							Linea = EntradaTexto.readLine();
							System.out.println(Linea);
							SalidaTexto.flush();
							//String resp = EntradaTexto.readLine();  		// lee READY  

							BufferedInputStream fichbis= new BufferedInputStream(new FileInputStream(fich));

							int leidos= 0;
							byte[] buffer= new byte [1024];
							System.out.println("leo el fichero por bloques de bytes ");
							while (leidos!=-1) {
								leidos= fichbis.read (buffer);
								if (leidos!=-1)								//mientras haya algo que leer, se escribe en la SalidaBytes
									SalidaBytes.write (buffer, 0, leidos);
							}  
							SalidaBytes.flush();							//
							fichbis.close();
							Verificar = true;


						} else if(!fich.exists()) {
							System.out.println("NO SE PUDO UBICAR EL FICHERO");

						}else {
							System.out.println("ERROR");
						}
					} catch(Exception e) {
						System.out.println("ERROR EN EL PUT DEL CLIETE: " + e);

					}

				}

				
				
				
				

				if(args[1].equals("GET") || args[1].equals("get")) {

					
					SalidaTexto.println(args[1] +" " + args[2]);				//enviamos al server el la sentencia con GET				
					String respuesta = EntradaTexto.readLine();					//leemos lo que nos llega del server
					
					System.out.println(respuesta);
					String [] palabras = respuesta.split(" ");					//separamos lo que nos llega
					
					long size = Integer.parseInt(palabras[1]);					//el tamaño nos llega en String, lo pasamos a int para poder operar con el
					int dato;
					
					System.out.println("el  tamano es "+size);
					SalidaTexto.println("READY");
					
					String nomfich="copiaencliente_"+args[2];					//el nomnbre del fichero que creara en cliente
					System.out.println("cambio nombre fichero a: "+nomfich);
					

					try {   


						BufferedOutputStream fichbos= new BufferedOutputStream(new FileOutputStream(nomfich));
						for(long i= 0; i<size; i++) {	
							dato = EntradaBytes.readByte();
							fichbos.write(dato);
						}
						fichbos.close();
						EntradaBytes.close();
						Verificar = true;


					} catch(Exception e) {
						System.out.println("ERROR EN EL GET DE CLIENTE: " + e);
					}
				}
				
				
				
				
				
				
				//despues de haber realizado cualquier peticion, cerramos el socket
				
				if(Verificar)	{
					System.out.println("SE HA CERRADO LA CONEXION EXITOSAMENTE");
					socket.close();		
				}
			
			}catch (Exception e) {
				System.out.println("ERROR ESTABLECIENDO CONEXION CON SERVER: "+e);
			}

		}


	}

}