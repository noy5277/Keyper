package keyper;

import java.io.IOException;
import java.io.NotSerializableException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;



public class tests extends Autentication{


	public static void main(String[] args) throws Exception  {
		Key key=new Key("gmail","email", "noy5277@gmail.com", "P@ssw0rd", "gmail.com");
	    MasterPassword master=new MasterPassword();
	    master.create("C:\\sqlite\\db\\database.db", "buhnoy1212434", "", "");
	    master.getmDatabase().create(master);
	    master.getmConf().createFile(master);
	    master.getmBank().addkey(key);
	    
	}
	


}