package DayTimeServerTCP;
import java.io. *;
import java.net. *;
import java.util.Date;

public class DayTimeServerTCP {
	public static void main(String[] args) {
		try {
			ServerSocket serv = new ServerSocket (13);
			System.out.println("Helloserver created at port 13");	
			while (true) {
				Socket c = serv.accept();
				PrintWriter p = new PrintWriter (c.getOutputStream(),true);
				String date = (new Date()).toString();
				String msg =  date;
				p.println(msg);
				System.out.println(msg + "sent to: " + date);
				String ip = c.getInetAddress().toString();
				int port = c.getPort();
				System.out.println("The IP is: " + ip + "and the port number is: " + port);
				c.close();
				}
		}
		catch (IOException e) {
			System.out.println("HelloServer error: "+ e);
			
		}
		
		
	}

}
