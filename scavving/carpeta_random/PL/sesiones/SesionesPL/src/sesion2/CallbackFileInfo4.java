package sesion2;

import java.io.*;
import java.text.SimpleDateFormat;

public class CallbackFileInfo4 {
	public CallbackFileInfo4() {}
	public void calculaFileInfo() {
		File[] listado = (new File(".")).listFiles();
		for (int i=0;i<listado.length;i++) {
			Callbackthread4 cbfi2 = new Callbackthread4 (listado[i],this);
			cbfi2.start();
		}
	} // Calcula Fileinfo
	
	void recibeFileInfo(String fi) {
		System.out.println(fi);
	}
	public static void main(String[] args) {
		CallbackFileInfo4 c = new CallbackFileInfo4();
		c.calculaFileInfo();
	}
}
