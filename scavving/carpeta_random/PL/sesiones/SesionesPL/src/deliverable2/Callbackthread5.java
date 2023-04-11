package deliverable2;



import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Callbackthread5 extends Thread{
	private File fich;
	private String word;
	private String fileinfo;
	private CallbackFileInfo5 callback;
	
	public Callbackthread5 (File f,String word,CallbackFileInfo5 c) {
		this.fich= f;
		this.word=word;
		this.callback=c;
	}

	public void run() {
		String fileName = fich.toString();
		String fe = "";
		String totalfiles = "";
		int i = fileName.lastIndexOf('.');
		if (i>0) {
			fe = fileName.substring(i+1);
		}
		String fichNAME = fich.getName();
		if (fe.equals("txt") && ( !fichNAME.equals("output.txt"))){
			int totalcoincidences=0;
			try {
				if (fich.exists()) {
					BufferedReader readFile = new BufferedReader(new FileReader(fich));
					String readLine;
					fileinfo = " ";
					int iteration = 1;
					 while((readLine=readFile.readLine()) != null){
						 String[] parts = readLine.split(" ");
						 for (int k = 0;k<parts.length;k++) {
							 if(parts[k].equals(word)) {
								 totalcoincidences = totalcoincidences+1;
								 fileinfo = fileinfo+"\n Name of the file: " + fich.getName()+ " Line number: " + iteration + "\n line in which the word is located: "+ readLine;
								 totalfiles = totalfiles +" " + fich.getName();
							 }
							 else {
								 fileinfo = fileinfo + "";
							 }
							 
						 	 
						 }
			
					iteration = iteration +1;
					 }
				}
				
				
			} catch (Exception e) {	}
			
			callback.recibeFileInfo(fileinfo);
			System.out.println("Number of coincidences in the file: " +fich.getName()+ ": "+ totalcoincidences);
		}
	
		else {
			return;
		}
		
	}

	
	

}
