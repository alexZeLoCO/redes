package sesion2;

public class DosHebrasS {
	// La critical region es una region de la memoria en la que hay variables compartidas
	// Esto es peligroso porque si hay dos threads ejecutandose en esta region puede que cambien 
	//Esas variables
	// Para eso podemos bloquear el acceso a esas variables cuando una thread las esté usando, y 
	//hasta que no acabe no acceda otro thread
	public static void main(String[] args) {
		HebraS h1 = new HebraS(1);
		HebraS h2 = new HebraS(2);
		
		
			
		
	}

}
class HebraS extends Thread{
	static int i= 0;
	int l;
	static Integer s= new Integer(0);
	public HebraS(int j) {
		l = j;
		this.start();
		
	}
	public void run() {
		try {
			while(true) {
				synchronized(s){
					i++;
					sleep(10);
					System.out.println("Thread that is working: "+ l+" : Number of total executions:  " +i);
		
				}
			}
		}
		catch (Exception e) {}
		
	}
}