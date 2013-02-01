import java.net.*;
import java.util.HashMap;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import java.io.*;

import javax.activation.*;
import org.omg.CosNaming.NamingContextExtPackage.AddressHelper;

public class TCPServ {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		ServerSocket s=new ServerSocket(Integer.parseInt("5100"));
		System.out.println("Server started at localhost:"+5100);
		while(true){
		new ServThread(s.accept()).start();
		}
		
	}
}
class ServThread extends Thread{
	Socket s=null;
	public ServThread(Socket s) {
		// TODO Auto-generated constructor stub
		this.s=s;
		
	}
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		System.out.println("Connection established from "+s.getInetAddress());
		
		try {
			OutputStream output = new StringOutStream();
			BufferedReader r=new BufferedReader(new InputStreamReader(s.getInputStream()));
			 while(r.ready()){
				 output.write(r.read());
				 
			 }
			 String s=output.toString();
			 output.close();
			System.out.println(s);
			Header h=new Header(s);
			
			String content="";
			PrintWriter w=new PrintWriter(this.s.getOutputStream());
			
			 
			 if(h.operation.equals("GET")){
			 try{
				 content=get_content(h);
			 }catch(IOException e){
				 w.print("HTTP/1.1 404 Not Found\r\n");
				 w.print("Server: MoinakServ/dev0.01 (Java)\r\n");
				 w.print("\r\n");
				 w.print("That page was not found!");
				 w.flush();
				 w.close();
				 return;
			 }catch (ArrayIndexOutOfBoundsException e) {
				// TODO: handle exception
				 w.print("HTTP/1.1 404 Not Found\r\n");
				 w.print("Server: MoinakServ/dev0.01 (Java)\r\n");
				 w.print("\r\n");
				 w.print("An error occurred!!");
				 w.flush();
				 w.close();
				 return;
			}finally{
				//this.s.close();
			}
			 
			
			 w.print("HTTP/1.1 200 OK\r\n");
			 w.print("Server: MoinakServ/dev0.01 (Java)\r\n");
			 w.print("Connection: Close\r\n");
			 w.print("Content-Type: "+getMimeType("."+h.location)+"\r\n");
			// w.print("Content-Encoding: gzip\r\n");
			 w.print("\r\n");
			 w.print(content);
			 w.flush();
			 w.close();
			 }if(){
				 
			 }
			 else {
				 w.print("HTTP/1.1 501 Not Implemented\r\n");
				 w.print("Server: MoinakServ/dev0.01 (Java)\r\n");
				 w.print("\r\n");
				 w.print("That type of request was not recognized!");
				 w.flush();
				 waitc(100);
				 
				 w.close();
			}
			 
			
		
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			
		}
		
	}
	
	public void waitc(long mills){
		long l=System.currentTimeMillis();
		while (System.currentTimeMillis()-l<mills) {
			
			
		}
		return;
	}
	public String unencode(String s) throws IOException{
		GZIPInputStream in=new GZIPInputStream(new StringInStream(s));
		int i=0;
		StringBuilder sb=new StringBuilder();
		while ((i=in.read())!=-1) {
			sb.append((char)i);
		}
		return sb.toString();
	}
	public String get_gzip(String s) throws IOException{
		StringOutStream so=new StringOutStream();
		GZIPOutputStream o=new GZIPOutputStream(so);
		
			o.write(s.getBytes());
			o.flush();so.flush();
			
			
			System.out.println(so.toString());
			
			o.close();
			return so.toString();
		
	}
	public String get_content(Header h) throws IOException{
		BufferedReader in=new BufferedReader(new FileReader("."+h.location));
		StringBuilder sb=new StringBuilder();
		while(in.ready()){
			sb.append((char)in.read());
		}
		in.close();
		return sb.toString();
		
	}
	public String getencode(int c){
		if(c!='!'){
			return ""+((char)c);
		}
		String sb=Integer.toHexString(c);
		if(sb.length()!=2){
			sb="0"+sb;
		}
		sb="%"+sb;
		return sb;
		
		
	}
	public  String getMimeType(String fileUrl)
		      throws java.io.IOException
		    {
		      FileNameMap fileNameMap = URLConnection.getFileNameMap();
		      String type = fileNameMap.getContentTypeFor(fileUrl);

		      return type;
		    }
} class Header extends HashMap<String, String>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3085104963813507281L;
	String version="";
	String operation="";
	String location="";
	public Header(String s) {
		if(s.equals("")){
			return;
		}
		// TODO Auto-generated constructor stub
		String[] buf=s.split("\r\n");
		for (int i = 1; i < buf.length; i++) {
			String[] h=buf[i].split(": ");
			StringBuilder b=new StringBuilder();
			for (int j = 1; j < h.length; j++) {
				b.append(h[j]);
				
			}
			put(h[0], b.toString());
			
		}
		String[] head=buf[0].split(" ");
		version=head[2];
		operation=head[0];
		location=head[1];
		System.out.println(buf[0]);
	}
	public String getHTTPVer(){
		return version;
		
	}
	public String getHTTPOp(){
		return operation;
		
	}
	public String getHTTPLoc(){
		return location;
		
	}
	
	
}
class StringOutStream extends OutputStream{
    private StringBuilder string = new StringBuilder();
    @Override
    public void write(int b) throws IOException {
        this.string.append((char) b );
    }

    @Override
    public String toString(){
    	
        return this.string.toString();
    }
    @Override
    public void flush() throws IOException {
    	// TODO Auto-generated method stub
    	
    }
}
class StringInStream extends InputStream{
    private String string = null;
    int pointer=0;
   public StringInStream(String s) {
	// TODO Auto-generated constructor stub
	   string=s;
}
    

    @Override
    public String toString(){
    	
        return this.string.toString();
    }


	@Override
	public int read() throws IOException {
		// TODO Auto-generated method stub
		if(pointer<string.length()){
			pointer++;
			return (int)string.charAt(pointer);
		}else {
			return -0x1;
		}
	}
	@Override
	public  void reset() throws IOException {
		// TODO Auto-generated method stub
		pointer=0;
	}
    
}