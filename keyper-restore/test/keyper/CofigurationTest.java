package keyper;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

public class CofigurationTest{
	private static MasterPassword master;
	
	@Before
	public  void Deletefiles() throws Exception
	{
		MasterPassword master=new MasterPassword();
		File f=new File("C:\\Users\\"+master.getUsername()+"\\AppData\\Roaming\\Keyper");
		f.delete();
		f=new File("\"C:\\\\sqlite\\\\db\\\\database.db\"");
		f.delete();
	}
	
	@Test
	public void FilesCreation() throws Exception
	{
		Key key=new Key("gmail","Email", "noy5277@gmail.com", "P@ssw0rd", "gmail.com");
	    master=new MasterPassword();
	    master.create("C:\\sqlite\\db\\database.db","buhnoy5667728", "","");
	    master.getmBank().addkey(key);
	    master.getmConf().createFiles(master);
		assertTrue("Check if xml conf file is created, the result should be true",master.getmConf().getmXmlFile().exists());
		assertTrue("Check if bin conf file is created, the result should be true",master.getmConf().getmBinFile().exists());
	}
	
	@Test
	public void ImportFiles() throws Exception
	{
		MasterPassword destMaster=new MasterPassword();
		destMaster.getmConf().importFiles();
		assertEquals("Check conf file after import, the results shoud be the same",master.getmConf().getmLastConnectionStat(),destMaster.getmConf().getmLastConnectionStat());
		assertEquals("Check conf file after import, the results shoud be the same",master.getmConf().getmClipBoardSleepTime(),destMaster.getmConf().getmClipBoardSleepTime());
		assertEquals("Check conf file after import, the results shoud be the same",master.getmConf().getmLockAfterTime(),destMaster.getmConf().getmLockAfterTime());
	}
	
	@AfterClass
    public static void deleteFolder() {
		File f=new File("C:\\Users\\"+master.getUsername()+"\\AppData\\Roaming\\Keyper");
		master.getmConf().getmXmlFile().delete();
		master.getmConf().getmBinFile().delete();
		f.delete();
		
    }
	
    
}

