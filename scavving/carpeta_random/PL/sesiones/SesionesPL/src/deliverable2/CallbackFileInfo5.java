package deliverable2;
import java.io.*;


public class CallbackFileInfo5 {
	private File file;
	private String word;
	public CallbackFileInfo5(File f,String n) {
		file = f;
		word =n;
	}

	public void calculaFileInfo() {
		File[] listado = (new File(".")).listFiles();
		for (int i=0;i<listado.length;i++) {
			Callbackthread5 cbfi2 = new Callbackthread5 (listado[i],word,this);
			cbfi2.start();
		}
	} // Calcula Fileinfo
	
	void recibeFileInfo(String fi) {


		File output = new File ("output.txt");
		if (!output.exists()) {
			FileWriter task2 = null;
			PrintWriter writer= null;
			try {
				task2 = new FileWriter(output.getAbsoluteFile(),true);
				writer = new PrintWriter(task2);
				writer.append(fi);
				
				
				
			} catch (Exception e) {
			} finally {
				try {
					task2.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		

	}
	public static void main(String[] args) {
		File directorio = new File(args[0]);
		String word= args[1];
		CallbackFileInfo5 c = new CallbackFileInfo5(directorio,word);
		c.calculaFileInfo();
	}
}
