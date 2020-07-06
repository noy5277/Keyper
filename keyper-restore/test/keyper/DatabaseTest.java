package keyper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.Set;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

public class DatabaseTest {
	
	@Before
	public  void Deletefiles() throws Exception
	{
		MasterPassword master=new MasterPassword();
		File f=new File("C:\\Users\\"+master.getUsername()+"\\AppData\\Roaming\\Keyper");
		f.delete();
	}
	
	@Test
	public void CreateDatabase() throws Exception
	{	
		MasterPassword master=new MasterPassword();
	    Key key=new Key("gmail","Email", "noy5277@gmail.com", "P@ssw0rd", "gmail.com");
	    master=new MasterPassword();
	    master.create("C:\\sqlite\\test\\database.db","buhnoy5667728");
	    master.getmDatabase().create();
	    master.getmBank().addkey(key);
	    master.getmDatabase().close(master.getmBank());
	    File f=new File("C:\\sqlite\\test\\database.db");
	    assertTrue("Check if database was created, it should be true",f.exists());  
	}
	
	
	@AfterClass
	public static void  DeleteDatabase()
	{
		File f=new File("C:\\sqlite\\db\\database.db");
		f.delete();
	}

}
