package keyper;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Base64;
import java.util.Iterator;
import java.util.Map;
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
	private String dbpath;
	private Connection conn = null;
	private static Statement stat = null;
	

	@SuppressWarnings("static-access")
	public Database(MasterPassword masterkey) throws NoSuchPaddingException, NoSuchAlgorithmException, InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
		this.cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
		this.myKey = generate(15,true,true,true,true);
		String driver = "org.apache.derby.jdbc.EmbeddedDriver";
		Class.forName(driver);
		this.connect(masterkey);
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
	 
	 
	
	public static String getMyKey() {
		return myKey;
	}

	public static void setMyKey(String myKey) {
		Database.myKey = myKey;
	}

	private String encrypt (String strToEncrypt) throws IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException, IOException, InvalidKeyException
	 {
		 
		 cipher.init(Cipher.ENCRYPT_MODE, setKey());
		 return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
		 
	 }
	 
	 private String decrypt (String strToDecrypt) throws IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException, IOException, InvalidKeyException
	 {
		 cipher.init(Cipher.DECRYPT_MODE, setKey());
		 return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
	 }
	 
	 
	
	 public void createtables() throws SQLException 
	 {
		 
		 String query = "CREATE TABLE Bank( "
		         + "KeyId VARCHAR(255), "
		         + "Title VARCHAR(255), "
		         + "GroupName VARCHAR(255), "
		         + "Username VARCHAR(255), "
		         + "Password VARCHAR(255), "
		         + "URL VARCHAR(255),"
		         + "Expired DATE)";
		         stat.execute(query);
		String query1 = "CREATE TABLE Archive( "
			     + "KeyId VARCHAR(255), "
		         + "Title VARCHAR(255), "
		         + "GroupName VARCHAR(255), "
		         + "Username VARCHAR(255), "
		         + "Password VARCHAR(255), "
			     + "URL VARCHAR(255),"
		         + "Expired DATE, "
			     + "Day INT, "
				 + "Month INT,"
				 + "Years INT)";
		         stat.execute(query1);
	 }
	 
	 
	
	public void connect(MasterPassword masterkey) throws SQLException, ClassNotFoundException
	{
		this.dbpath="jdbc:derby:"+masterkey.getPath()+";create=true";
		this.conn = DriverManager.getConnection(dbpath,"keyper",masterkey.getPassword());
	}
	
	
	public void close(Bank bnk) throws SQLException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException, IOException
	{
		  try
          {			  
              push(bnk);
			  DriverManager.getConnection("jdbc:sqlite:;shutdown=true");
              conn.close();
			  conn=null;
          }
          catch(SQLException se)
          {
        	  
          }
	}

	@SuppressWarnings("deprecation")
	public void push(Bank bnk) throws SQLException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException, IOException {
		
		PreparedStatement psInsert=null;
		 Set<Key> keys=bnk.getBank();
		 for(Key key :keys)
		 {
	       psInsert = conn.prepareStatement("insert into Bank"
	       	+ " (KeyId,Title,GroupName,Username,Password,URL,Expired) values (?,?,?,?,?,?,?)");
	       psInsert.setString(1, encrypt(key.getmId()));
		   psInsert.setString(2, encrypt(key.getmTitle()));
		   psInsert.setString(3, encrypt(key.getmGroup()));
		   psInsert.setString(4, encrypt(key.getmUsername()));
		   psInsert.setString(5, encrypt(key.getmPassword()));
		   psInsert.setString(6, encrypt(key.getmUrl()));
		   psInsert.setDate(7, (Date) key.getmExpired());
		   psInsert.executeUpdate();
		   Set<?> entries=key.gethistory();
			Iterator<?> itr=entries.iterator();
			while(itr.hasNext())
			{
				Key k;
				java.util.Date d;
				@SuppressWarnings("rawtypes")
				Map.Entry e=(Map.Entry)itr.next();
				k=(Key) e.getValue();
				d=(java.util.Date)e.getKey();
				psInsert = conn.prepareStatement("insert into Archive"
				       	+ " (KeyId,Title,GroupName,Username,Password,URL,Expired,Day,Month,Years) values (?,?,?,?,?,?,?,?,?,?)");
				psInsert.setString(1, encrypt(k.getmId()));
			    psInsert.setString(2, encrypt(k.getmTitle()));
				psInsert.setString(3, encrypt(k.getmGroup()));
				psInsert.setString(4, encrypt(k.getmUsername()));
				psInsert.setString(5, encrypt(k.getmPassword()));
				psInsert.setString(6, encrypt(k.getmUrl()));
			    psInsert.setDate(7, (Date) k.getmExpired());
				psInsert.setInt(8, d.getDate());
				psInsert.setInt(9, d.getMonth()+1);
				psInsert.setInt(10, d.getYear()+1900);
				psInsert.executeUpdate();
			}
		  }
		 
		 
	}
	 
	

	public void pull(Bank bnk) throws SQLException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException, IOException 
	{
		Set<Key> keys=bnk.getBank();
		String query = "SELECT KeyId,Title,GroupName,UserName,Password,URL,Expired FROM Bank";
	    ResultSet rs = stat.executeQuery(query);
		for(Key key :keys)
		{
			rs = stat.executeQuery(query);
			while(rs.next())
			{
				key.setmId(decrypt(rs.getString("KeyId")));
				key.setmTitle(decrypt(rs.getString("Title")));
				key.setmGroup(decrypt(rs.getString("GroupName")));
				key.setmUsername(decrypt(rs.getString("UserName")));
				key.setmPassword(decrypt(rs.getString("Password")));
				key.setmUrl(decrypt(rs.getString("URL")));
				key.setmExpired(rs.getDate("Expired"));
			}
			Map<java.util.Date, Key> History=key.getmHistory();
			String query1 = "SELECT KeyId,Title,GroupName,UserName,Password,URL,Expired,Day,Month,Years FROM Archive";
			ResultSet rs1 = stat.executeQuery(query1);
			while(rs1.next())
			{
				@SuppressWarnings("deprecation")
				
				java.util.Date date=new java.util.Date(rs1.getInt("Years"),rs1.getInt("Month"),rs1.getInt("Day"));
				Key k = new Key(null, null, null, null, null);
				k.setmId(decrypt(rs1.getString("KeyId")));
				k.setmTitle(decrypt(rs1.getString("Title")));
				k.setmGroup(decrypt(rs1.getString("GroupName")));
				k.setmUsername(decrypt(rs1.getString("UserName")));
				k.setmPassword(decrypt(rs1.getString("Password")));
				k.setmUrl(decrypt(rs1.getString("URL")));
				k.setmExpired(rs1.getDate("Expired"));
				History.put(date, k);
			}
	    }
		
	}

}
