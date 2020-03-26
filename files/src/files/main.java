package files;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;


public class main 
{

	public static void main(String[] args)
	{

		try 
		{	
			FileOutputStream temp= new FileOutputStream("temp.dat");
			ObjectOutputStream write= new ObjectOutputStream(temp);
			write.writeChars("hellow!!");
			write.close();
            InputStream  inStr= new FileInputStream("temp.dat");//read
			OutputStream outStr= new FileOutputStream("output.txt");// Storage	
			Caesarcipher cipher = new Caesarcipher(5);
		    cipher.encrypt(inStr, outStr);
			inStr.close();
			outStr.close(); 
		
		}
		catch (IOException e) {
			System.out.println("error: " + e);
		}
		
	}
	
}


