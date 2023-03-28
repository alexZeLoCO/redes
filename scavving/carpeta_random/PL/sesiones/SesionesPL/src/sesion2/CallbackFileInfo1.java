package sesion2;
import java.io.File;
import java.util.GregorianCalendar;

public class CallbackFileInfo1 {
	public static void main(String[] args) {
		File[] listado = (new File(".")).listFiles();
		Callbackthread1[] cbfi = new Callbackthread1[listado.length];
		for (int i=0; i<listado.length;i++) {
			cbfi[i]= new Callbackthread1(listado[i]);
			cbfi[i].start();
			
		}
		for (int i=0;i<listado.length;i++) {
			String fileinfo = null;
			do {
				System.out.println("miro hebra: "+i);
				fileinfo = cbfi[i].getfileinfo();
			
			}while (fileinfo== null);
			System.out.println(fileinfo);
		}
	}
	

}
class Callbackthread1 extends Thread{
	private File fich;
	private String fileinfo;
	public Callbackthread1 (File f) {
		this.fich = f;	
	}
	public void run() {
		GregorianCalendar fechamodif = new GregorianCalendar();
		fechamodif.setTimeInMillis(fich.lastModified());
		if (fich.isDirectory()) {
			fileinfo = "[DIR ] "+ fich.getName();
		}
		else{
			fileinfo = "[FILE ] "+fich.getName() + " "+fich.length()+ "B. "+fechamodif.get(GregorianCalendar.DAY_OF_MONTH)+"\""+(fechamodif.get(GregorianCalendar.MONTH)+1)+ "/" +fechamodif.get(GregorianCalendar.YEAR);
		}
	}
	public String getfileinfo() {
		return fileinfo;
		
	}
}
