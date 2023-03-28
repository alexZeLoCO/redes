package sesion1;

import java.io.*;
import java.io.BufferedReader;
import java.nio.file.Files;
import java.util.*;

public class LDHTTPGET {

	
	
	static String[] readFile(String filename) throws Exception{
		String[] errorRet = new String[2];
		try {
			Scanner headerScanner = new Scanner(new File(filename));
			String header = headerScanner.nextLine();
			String [] lineSplited = header.split(" ");
			String fileFormat = lineSplited[1].substring(lineSplited[1].lastIndexOf(".")+1);
			fileFormat.trim();
			if(lineSplited.length == 3) {
				
				if(lineSplited[0].contentEquals("GET") == false) {
					System.out.println("Expected GET Parameter.");
					throw new Exception("");
				}
				
				else if(lineSplited[1].contentEquals("\\")){
					String[] noFileRet = new String[2];
					noFileRet[0] = "html";
					noFileRet[1] = System.getProperty("user.dir") + "\\" + "index.html";
					try {
						FileInputStream indexFinder = new FileInputStream(noFileRet[1]);
						return noFileRet;
					}
					catch (Exception indexFinderEx) {
						String[] ret = new String[2];
						System.out.println("File Not Found on Server");
						ret[0] = "html";
						ret[1] = null;
						return ret;
					}
				}
				
				else if(((fileFormat.contentEquals("jpg")) || (fileFormat.contentEquals("htm")) || (fileFormat.contentEquals("html")) || (fileFormat.contentEquals("txt"))) == false) {
					System.out.println("This file format is not supported");
					throw new Exception("");
				}
				
				else if(lineSplited[2].contentEquals("HTTP/1.1") == false){
					System.out.println("Wrong HTTP Version");
					throw new Exception("");
				}
				
				else {
					System.out.println(lineSplited[1]);;
					String[] ret = new String[2];
					try {
						FileInputStream finder = new FileInputStream(System.getProperty("user.dir") + lineSplited[1]);
						ret[0] = fileFormat;
						ret[1] = System.getProperty("user.dir") + lineSplited[1];
						return ret;
					}
					catch(Exception ex){
						System.out.println("File Not Found on Server");
						ret[0] = fileFormat;
						ret[1] = null;
					}
				}
			}
			else {
				System.out.println("Wrong HTTP Header.");
				throw new Exception("");
			}	
			
			return errorRet;
		}
		catch (Exception e) {
			System.out.println("Error handling the file"); 
			return errorRet;
			}
		
		
	}
	
	static void writeFile(String[] readFileOutput) throws Exception{
		if (readFileOutput[1] == null) {
			try {
				String content = ("HTTP/1.1 404 File Not Found");
				BufferedWriter out = new BufferedWriter(new FileWriter(System.getProperty("user.dir") + "\\" + "GET-response.txt"));
				out.write(content);
				out.close();
			}
			catch(Exception OutputException) {
				System.out.println("Error handling output");
			}
		}
		
		else{
			switch(readFileOutput[0]) {
				case "html":
					try {
						File f = new File(readFileOutput[1]);
						long size = f.length();
						String content = ("HTTP/1.1 200 OK");
						String date = ("Date: " + new Date().toString());
						String server = ("Server: Id-http-get");
						String contentType = ("Content-type: text/html");
						String contentFileSize = ("Content-Length: " + size);
						String defoString = (content + "\n" + date + "\n" + server + "\n" + contentType + "\n"+ contentFileSize + "\n"); 
						BufferedWriter out = new BufferedWriter(new FileWriter(System.getProperty("user.dir") + "\\" + "GET-response.txt"));
						out.write(defoString + "\n\n");
						Scanner sc = new Scanner(readFileOutput[1]);
						File file = new File(sc.nextLine());
						sc = new Scanner(file);
						while(sc.hasNextLine()) {
							String line = sc.nextLine();
							out.write(line + "\n");
						}
						out.close();
					}
					catch(Exception e) {
						System.out.println("Error handling output");
					}
					break;
				case "htm":
					try {
						File f = new File(readFileOutput[1]);
						long size = f.length();
						String content = ("HTTP/1.1 200 OK");
						String date = ("Date: " + new Date().toString());
						String server = ("Server: Id-http-get");
						String contentType = ("Content-type: text/htm");
						String contentFileSize = ("Content-Length: " + size);
						String defoString = (content + "\n" + date + "\n" + server + "\n" + contentType + "\n"+ contentFileSize + "\n"); 
						BufferedWriter out = new BufferedWriter(new FileWriter(System.getProperty("user.dir") + "\\" + "GET-response.txt"));
						out.write(defoString + "\n\n");
						Scanner sc = new Scanner(readFileOutput[1]);
						File file = new File(sc.nextLine());
						sc = new Scanner(file);
						while(sc.hasNextLine()) {
							String line = sc.nextLine();
							out.write(line + "\n");
						}
						out.close();
					}
					catch(Exception e) {
						System.out.println("Error handling output");
					}
					break;

				case "jpg":
					try {
						File f = new File(readFileOutput[1]);
						long size = f.length();
						String content = ("HTTP/1.1 200 OK");
						String date = ("Date: " + new Date().toString());
						String server = ("Server: Id-http-get");
						String contentType = ("Content-type: image/jpg");
						String contentFileSize = ("Content-Length: " + size);
						String defoString = (content + "\n" + date + "\n" + server + "\n" + contentType + "\n"+ contentFileSize + "\n"); 
						BufferedWriter out = new BufferedWriter(new FileWriter(System.getProperty("user.dir") + "\\" + "GET-response.txt"));
						out.write(defoString + "\n\n");
						out.close();
					}
					catch(Exception e) {
						System.out.println("Error handling output");
					}
					break;
					
				case "txt":
					try {
						File f = new File(readFileOutput[1]);
						long size = f.length();
						String content = ("HTTP/1.1 200 OK");
						String date = ("Date: " + new Date().toString());
						String server = ("Server: Id-http-get");
						String contentType = ("Content-type: text/txt");
						String contentFileSize = ("Content-Length: " + size);
						String defoString = (content + "\n" + date + "\n" + server + "\n" + contentType + "\n"+ contentFileSize + "\n"); 
						BufferedWriter out = new BufferedWriter(new FileWriter(System.getProperty("user.dir") + "\\" + "GET-response.txt"));
						out.write(defoString + "\n\n");
						Scanner sc = new Scanner(readFileOutput[1]);
						File file = new File(sc.nextLine());
						sc = new Scanner(file);
						while(sc.hasNextLine()) {
							String line = sc.nextLine();
							out.write(line + "\n");
						}
						out.close();
					}
					catch(Exception e) {
						System.out.println("Error handling output");
					}
					break;
					
			}
		}
	}
	
	
	
	
	public static void main(String[] args) throws Exception {
		String [] data = readFile(args[0]);
		writeFile(data);
	}
		
	
	
	
	
	
	

}
