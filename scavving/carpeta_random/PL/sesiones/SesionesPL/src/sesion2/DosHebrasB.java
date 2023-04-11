package sesion2;


public class DosHebrasB {
	public static void main(String[] args) {
		Integer s1 = new Integer(0);
		Integer s2 = new Integer(0);
		HebraB h1 = new HebraB(s2,s1,1);
		HebraB h2 = new HebraB(s2,s1,2);
		
		
		
	}
}
	
	
class HebraB extends Thread{
	int i;
	Integer ss1,ss2;
	static int c = 0;
	public HebraB(Integer k,Integer j,int l) {
		i=l;
		ss1=k;
		ss2=j;
		this.start();
	}
	public void run() {
		try {
			while(true) {
				synchronized (ss1) {
					c++;
					System.out.println(i+ ": entro en rc1 - " +c);
				}
				synchronized (ss2) {
					c++;
					System.out.println(i+ ": entro en rc2 - " +c);
				}
			}
		}
		catch (Exception e) {}
	}
	
}

