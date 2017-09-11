import java.net.*;
import java.io.*;
import java.util.*;

public class WavFileServer  {
 /* public static void main (String [] args ) throws IOException {
  	System.out.println("haii ,hello\n");
  
  }*/
    // create socket
    
    void run(String s) throws IOException{
      //ServerSocket servsock = new ServerSocket(8000);
   
     	System.out.println("Waiting...");
		for(int i=0;i<1;i++){
		Socket sock=ServerInterface.s[i];//new Socket();
       	//sock = servsock.accept();
      	System.out.println("Accepted connection : " + sock);
      	
      // sendfile
      File myFile = new File (s);
      byte [] mybytearray  = new byte [(int)myFile.length()];
      FileInputStream fis = new FileInputStream(myFile);
      BufferedInputStream bis = new BufferedInputStream(fis);
      bis.read(mybytearray,0,mybytearray.length);
      OutputStream os = sock.getOutputStream();
      System.out.println("Sending...");
      os.write(mybytearray,0,mybytearray.length);
     os.flush();
     
     sock.close();
		}
     //servsock.close();
     
     String[]args=null;
     JavaSoundRecorder jsr=new JavaSoundRecorder();
     jsr.main(args);
     System.out.println("ThanQ\n");
		
    }
}
