package sesion2;

public class DosHebras3 {
	public static void main(String[] args) {
		Hebra3 h1=  new Hebra3(2);
		Hebra3 h2=new Hebra3(10);
		//Este programa lo que hace es ejecutar a la vez dos bucles. Imprime a la vez h1 y h22
	}
}
class Hebra3 implements Runnable {
	int i;
	Hebra3(int j){
		i=j;
		Thread h = new Thread(this);
		h.start();
	}
	public void run() {
		while(true) {
			System.out.println(i);
		}
	}
}