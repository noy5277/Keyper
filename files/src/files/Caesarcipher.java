package files;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Caesarcipher {
	private int key;
	
	public Caesarcipher (int givenkey)
	{
		key=givenkey;
		
	}
	
	public void encrypt(InputStream inStr, OutputStream outStr) throws IOException
	{
		boolean done = false;
		while(!done)
		{
			int next = inStr.read();
			if(next == -1)
			{
				done =true;
			}
			else
			{
			   byte b=(byte) next;
			   byte c=(byte) (b+key);
			   outStr.write(c);
			   
			} 
		}
	}
	

}
