package keyper;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class tests extends Autentication{

	
	public tests() throws NoSuchAlgorithmException {
		super();
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws IOException, InterruptedException, NoSuchAlgorithmException  {
		Key key=new Key("email", "noy5277@gmail.com", "P@ssw0rd", "gmail.com");
		File f=new File("C:\\Users\\noyz\\Desktop\\github.txt");
		MasterPassword k=new MasterPassword("P@ssw0rd", getFileChecksum(f), null);
		System.out.println(k.login(new File("C:\\Users\\noyz\\Desktop\\New Bitmap Image.bmp"), "P@ssw0rd", false, true, false));
		
	}
	

}
