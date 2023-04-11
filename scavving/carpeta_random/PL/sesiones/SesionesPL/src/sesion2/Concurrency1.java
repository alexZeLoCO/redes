package sesion2;

public class Concurrency1 {
		public static void main(String[] args) {
			HebraSS h1 = new HebraSS(1);
			HebraSS h2 = new HebraSS(2);
			HebraSS h3 = new HebraSS(3);
			
				
			
		}

	}
class HebraSS extends Thread{
	int l;
	static Integer s= new Integer(0);
	public HebraSS(int j) {
		l = j;
		this.start();
		System.out.println("This is Hebra: "+l);
		
	}
	public void run() {
		try {
			for (int i=1;i<16;i++) {
					System.out.println("Thread that is working: "+ l);
					Thread.yield();
			}
			
		}
		catch (Exception e) {}
			
	}
}


