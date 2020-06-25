package keyper;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import org.junit.Test;

public class AutenticationTest extends Autentication {

	@Test
	public void CompareEqualeFiles() throws NoSuchAlgorithmException, IOException
	{
		File source=new File("C:\\Test\\Source.txt");
		File destination=new File("c:\\Test\\Destination2.txt");
		boolean result=getFileChecksum(source).equals(getFileChecksum(destination));
		assertTrue("The files are equals, The result should be true",result);
	}
	
	@Test
	public void CompareDifferentFiles() throws NoSuchAlgorithmException, IOException
	{
		File source=new File("C:\\Test\\Source.txt");
		File destination=new File("c:\\Test\\Destination.txt");
		boolean result=getFileChecksum(source).equals(getFileChecksum(destination));
		assertFalse("The files are differents, The result should be false",result);
	}
}
