package keyper;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Base64;

import java.util.Set;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;



public class Database extends Generator{
	
	private static String myKey;
	final Cipher cipher;
	private static SecretKeySpec secretKey;
	private String path;
	private static Connection conn = null;
	private static Statement stat = null;

	@SuppressWarnings("static-access")
	public Database(String path,String master) throws NoSuchPaddingException, NoSuchAlgorithmException, InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
		this.path=path;
		this.cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
		this.myKey = generate(15,true,true,true,true);
		String dbpath = "jdbc:derby:"+path+";create=true";
		String driver = "org.apache.derby.jdbc.EmbeddedDriver";
		Class.forName(driver).newInstance();
		this.conn = DriverManager.getConnection(dbpath,"shem",master);
		this.stat=conn.createStatement();
		this.createtables();

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
	 
	 
	 
	 public String encrypt (String strToEncrypt) throws IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException, IOException, InvalidKeyException
	 {
		 String temp;
		 cipher.init(Cipher.ENCRYPT_MODE, setKey());
		 temp=Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
		 return temp;
	 }
	 
	 public String decrypt (String strToDecrypt) throws IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException, IOException, InvalidKeyException
	 {
		 cipher.init(Cipher.DECRYPT_MODE, setKey());
		 return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
	 }
	 
	 
	
	 public void createtables() throws SQLException 
	 {
		 
		 String query = "CREATE TABLE Bank( "
		         + "Id INT NOT NULL GENERATED ALWAYS AS IDENTITY, "
		         + "Title VARCHAR(255), "
		         + "GroupName VARCHAR(255), "
		         + "Username VARCHAR(255), "
		         + "Password VARCHAR(255), "
		         + "URL VARCHAR(255),"
		         + "Expired DATE)";
		         stat.execute(query);
		String query1 = "CREATE TABLE History( "
	             + "Id INT NOT NULL GENERATED ALWAYS AS IDENTITY, "
			     + "KeyId VARCHAR(255), "
			     + "Date DATE, "
		         + "Title VARCHAR(255), "
		         + "GroupName VARCHAR(255), "
		         + "Username VARCHAR(255), "
		         + "Password VARCHAR(255), "
			     + "URL VARCHAR(255),"
		         + "Expired DATE)";
		         stat.execute(query1);
		         
	 }
	 
	 public void insert(Bank bnk) throws SQLException
	 {
		 PreparedStatement psInsert=null;
		 Set<Key> keys=bnk.getBank();
		 for(Key key :keys)
		 {
	       psInsert = conn.prepareStatement("insert into Bank"
	       	+ " (Title,GroupName,Username,Password,URL,Expired) values (?,?,?,?,?,?)");	
		   psInsert.setString(1, key.getmTitle());
		   psInsert.setString(2, key.getmGroup());
		   psInsert.setString(3, key.getmUsername());
		   psInsert.setString(4, key.getmPassword());
		   psInsert.setString(5, key.getmUrl());
		   psInsert.setDate(6, (Date) key.getmExpired());
		   psInsert.executeUpdate();
		 }
		 
		 
	 }
	 
	 
	 
	 

	 
	 
	 

}
