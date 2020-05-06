package keyper;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.crypto.BadPaddingException;

import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;



import java.io.IOException;



public class Database extends Encryption{
	
	private String dbpath;
	private Connection conn = null;
	private static Statement stat = null;
	private MasterPassword master;

	public Database(MasterPassword master) throws NoSuchAlgorithmException, NoSuchPaddingException, ClassNotFoundException, SQLException {
		super();
		String driver = "org.apache.derby.jdbc.EmbeddedDriver";
		Class.forName(driver);
		this.master=master;
		
		
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
		         + "E_Day INT, "
				 + "E_Month INT,"
				 + "E_Years INT)";
		         stat.execute(query);
		String query1 = "CREATE TABLE Archive( "
			     + "KeyId VARCHAR(255), "
		         + "Title VARCHAR(255), "
		         + "GroupName VARCHAR(255), "
		         + "Username VARCHAR(255), "
		         + "Password VARCHAR(255), "
			     + "URL VARCHAR(255),"
			     + "E_Day INT, "
				 + "E_Month INT,"
				 + "E_Years INT,"
			     + "Day INT, "
				 + "Month INT,"
				 + "Years INT)";
		         stat.execute(query1);
	 }
	 
	@SuppressWarnings("static-access")
	public void create() throws SQLException
	{
		this.dbpath="jdbc:derby:"+master.getPath()+";create=true";
		this.conn = DriverManager.getConnection(dbpath,"keyper",master.getPassword());
		this.stat=conn.createStatement();
		this.createtables();
		//createPrivateKey();
		
	}
	
	@SuppressWarnings("static-access")
	public void connect() throws SQLException, ClassNotFoundException
	{
		this.dbpath="jdbc:derby:"+master.getPath()+";create=false";
		this.conn = DriverManager.getConnection(dbpath,"keyper",master.getPassword());
		this.stat=conn.createStatement();
	}
	
	private void droptables()
	{
		String query = "DROP TABLE bank";
		String query1 = "DROP TABLE archive";
	    try {
			this.stat.execute(query);
			this.stat.execute(query1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void close(Bank bnk) throws SQLException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException, IOException
	{
	      droptables();
          createtables();
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
	public void push(Bank bnk) throws SQLException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException, IOException  {
		
		PreparedStatement psInsert=null;
		 Set<Key> keys=bnk.getBank();
		 for(Key key :keys)
		 {
	       psInsert = conn.prepareStatement("insert into Bank"
	       	+ " (KeyId,Title,GroupName,Username,Password,URL,E_Day,E_Month,E_Years) values (?,?,?,?,?,?,?,?,?)");
	       psInsert.setString(1, encrypt(key.getmId()));
		   psInsert.setString(2, encrypt(key.getmTitle()));
		   psInsert.setString(3, encrypt(key.getmGroup()));
		   psInsert.setString(4, encrypt(key.getmUsername()));
		   psInsert.setString(5, encrypt(key.getmPassword()));
		   psInsert.setString(6, encrypt(key.getmUrl()));
		   psInsert.setInt(7, key.getmExpired().getDate());
			psInsert.setInt(8, key.getmExpired().getMonth()+1);
			psInsert.setInt(9, key.getmExpired().getYear()+1900);
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
				       	+ " (KeyId,Title,GroupName,Username,Password,URL,E_Day,E_Month,E_Years,Day,Month,Years) values (?,?,?,?,?,?,?,?,?,?,?,?)");
				psInsert.setString(1, encrypt(k.getmId()));
			    psInsert.setString(2, encrypt(k.getmTitle()));
				psInsert.setString(3, encrypt(k.getmGroup()));
				psInsert.setString(4, encrypt(k.getmUsername()));
				psInsert.setString(5, encrypt(k.getmPassword()));
				psInsert.setString(6, encrypt(k.getmUrl()));
				psInsert.setInt(7, k.getmExpired().getDate());
				psInsert.setInt(8, k.getmExpired().getMonth()+1);
				psInsert.setInt(9, k.getmExpired().getYear()+1900);
				psInsert.setInt(10, d.getDate());
				psInsert.setInt(11, d.getMonth()+1);
				psInsert.setInt(12, d.getYear()+1900);
				psInsert.executeUpdate();
			}
		  }
		 
		 
	}
	 
	

	@SuppressWarnings("deprecation")
	public void pull(Bank bnk) throws SQLException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException, IOException  
	{
		String query = "SELECT KeyId,Title,GroupName,UserName,Password,URL,E_Day,E_Month,E_Years FROM Bank";
	    ResultSet rs = stat.executeQuery(query);
		rs = stat.executeQuery(query);
		Key key=null;
		while(rs.next())
		{
			java.util.Date date1=new java.util.Date(rs.getInt("E_Years")-1900,rs.getInt("E_Month")-1,rs.getInt("E_Day"));
			key=new Key();
			key.setmId(decrypt(rs.getString("KeyId")));
			key.setmTitle(decrypt(rs.getString("Title")));
			key.setmGroup(decrypt(rs.getString("GroupName")));
			key.setmUsername(decrypt(rs.getString("UserName")));
			key.setmPassword(decrypt(rs.getString("Password")));
			key.setmUrl(decrypt(rs.getString("URL")));
			key.setmExpired(date1);
			bnk.addkey(key);
		}	
		for(Key k: bnk.getBank())
		{
			
			String query1 = "SELECT KeyId,Title,GroupName,UserName,Password,URL,E_Day,E_Month,E_Years,Day,Month,Years FROM Archive WHERE KeyId LIKE "
			+ "'"+encrypt(k.getmId())+ "'";
			ResultSet rs1 = stat.executeQuery(query1);
			while(rs1.next())
			{
				@SuppressWarnings("deprecation")
				
				Date date=new  Date(rs1.getInt("Years")-1900,rs1.getInt("Month")-1,rs1.getInt("Day"));
				Date date2=new Date(rs1.getInt("E_Years")-1900,rs1.getInt("E_Month")-1,rs1.getInt("E_Day"));
				Key hk = new Key();
				Map<Date, Key> history=new HashMap<>();
				hk.setmId(decrypt(rs1.getString("KeyId")));
				hk.setmTitle(decrypt(rs1.getString("Title")));
				hk.setmGroup(decrypt(rs1.getString("GroupName")));
				hk.setmUsername(decrypt(rs1.getString("UserName")));
				hk.setmPassword(decrypt(rs1.getString("Password")));
				hk.setmUrl(decrypt(rs1.getString("URL")));
				hk.setmExpired(date2);
				history.put( date, hk);
				k.setmHistory(history);
			}
		}
			

	}

}
