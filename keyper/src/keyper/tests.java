package keyper;



import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.NotSerializableException;
import java.io.ObjectOutputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class tests extends Autentication{


	public static void main(String[] args) throws NotSerializableException,IOException, InterruptedException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException  {
		Key key=new Key("gmail","email", "noy5277@gmail.com", "P@ssw0rd", "gmail.com");
		Bank bnk=new Bank();
		bnk.addkey(key);
		MasterPassword master=new MasterPassword("c:/sqlite/db/database.db", "password", "", "");
		master.getmDatabase().insert(bnk);

	
	}
	


}