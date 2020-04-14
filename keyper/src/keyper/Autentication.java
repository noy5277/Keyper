package keyper;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public abstract class Autentication {
	
	
	
	
	protected boolean comparepasswords(String input, String saved)
	{
		return input.equals(saved);
	}
	
	public static String getFileChecksum(File file) throws IOException, NoSuchAlgorithmException
	{
		MessageDigest shaDigest = MessageDigest.getInstance("SHA-1");
	    //Get file input stream for reading the file content
	    FileInputStream fis = new FileInputStream(file);
	     
	    //Create byte array to read data in chunks
	    byte[] byteArray = new byte[1024];
	    int bytesCount =0; 
	      
	    //Read file data and update in message digest
	    while ((bytesCount = fis.read(byteArray)) != -1) {
	    	shaDigest.update(byteArray, 0, bytesCount);
	    };
	     
	    //close the stream; We don't need it now.
	    fis.close();
	     
	    //Get the hash's bytes
	    byte[] bytes = shaDigest.digest();
	     
	    //This bytes[] has bytes in decimal format;
	    //Convert it to hexadecimal format
	    StringBuilder sb = new StringBuilder();
	    for(int i=0; i< bytes.length ;i++)
	    {
	        sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
	    }
	     
	    //return complete hash
	   return sb.toString();
	}
	
	
	protected boolean comparefiles(File input, String saved) throws IOException, NoSuchAlgorithmException {
		return getFileChecksum(input).equals(saved);
	}
	
	public boolean comparesids(String saved)
	{
		return true;
	}
	
	
	
	
};
	
