package keyper;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Scanner;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

public class Database extends Generator{
	
	private static String myKey;
	final Cipher cipher;
	private static SecretKeySpec secretKey;
	private File db;

	@SuppressWarnings("static-access")
	public Database(File db) throws NoSuchPaddingException, NoSuchAlgorithmException{
		this.db = db;
		this.cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
		this.myKey = generate(15,true,true,true,true);

	}
	
	 private static SecretKeySpec setKey() 
	 {
	        MessageDigest sha = null;
	        try {
	            byte[] key = myKey.getBytes("UTF-8");
	            sha = MessageDigest.getInstance("SHA-1");
	            key = sha.digest(key);
	            key = Arrays.copyOf(key, 16); 
	            secretKey = new SecretKeySpec(key, "AES");
	        }
	        catch (NoSuchAlgorithmException e) {
	            e.printStackTrace();
	        } 
	        catch (UnsupportedEncodingException e) {
	            e.printStackTrace();
	        }
			return secretKey;
     
	 }
	 
	 
	 
	 private File encrypt (String strToEncrypt) throws IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException, IOException, InvalidKeyException
	 {
		 FileWriter myWriter=new FileWriter(db);
		 cipher.init(Cipher.ENCRYPT_MODE, setKey());
		 myWriter.write(Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8"))));
		 myWriter.close();
		 return db;
	 }
	 
	 private String decrypt () throws IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException, IOException, InvalidKeyException
	 {
		 File myObj=new File(db.getAbsolutePath());
		 Scanner myReader = new Scanner(myObj);
		 cipher.init(Cipher.DECRYPT_MODE, setKey());
		 String data = myReader.nextLine();
		 myReader.close(); 
		 return new String(cipher.doFinal(Base64.getDecoder().decode(data)));
	 }
	 
	 private void push()
	 {
		 
	 }

	 
	 
	 

}
