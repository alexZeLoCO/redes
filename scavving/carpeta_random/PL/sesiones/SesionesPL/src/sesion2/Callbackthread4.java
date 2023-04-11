package sesion2;

import java.io.File;
import java.text.SimpleDateFormat;

public class Callbackthread4 extends Thread{
	private File fich;
	private String fileinfo;
	private CallbackFileInfo4 callback;
	
	public Callbackthread4 (File f,CallbackFileInfo4 c) {
		this.fich= f;
		this.callback=c;
	}
	public void run() {
		if (fich.isDirectory())
			fileinfo = "[DIR] "+fich.getName();
		else {
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy HH:MM");
			String fechaultmodif = dateFormat.format(fich.lastModified());
			fileinfo = "[FILE] "+fich.getName() + " "+fich.length() +"B. "+fechaultmodif;
			
		}
		callback.recibeFileInfo(fileinfo);
	}
	
	

} 
