package keyper;

import java.io.IOException;
import java.io.NotSerializableException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;



public class tests extends Autentication{


	public static void main(String[] args) throws NotSerializableException,IOException, InterruptedException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException  {
		Key key=new Key("gmail","email", "noy5277@gmail.com", "P@ssw0rd", "gmail.com");
		MasterPassword master=new MasterPassword("c:/sqlite/db/database.db", "buhnoy5667728", null, null);
		master.getmBank().addkey(key);
		master.getmDatabase().push(master.getmBank());
		master.getmDatabase().close();
	  

	}
	


}