package sesion2;

public class DosHebras4 {
	public static void main(String[] args) {
		Hebra4 h1= new Hebra4(1);
		Hebra4 h2= new Hebra4(2);
		new Thread(h2).start();
		new Thread(h1).start();
		
		}
}
class Hebra4 implements Runnable{
	int i;
	public Hebra4(int j) {
		i=j;
	}
	public void run() {
		do {
			System.out.println("hebra: "+i); //1 si se ejecutó h1 y 2 si se ejecutó h2
		}
		while (true);
	}
}
