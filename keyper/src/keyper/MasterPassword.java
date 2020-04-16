package keyper;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import javax.crypto.NoSuchPaddingException;

public class MasterPassword extends Autentication{

	private String mpassword;
	private String mkeyfile;
	private String msid;
	private Bank mBank;
	private Database mDatabase;
	private Configuration mConf;
	private String path;
	private String username = System.getProperty("user.name");
	
	public MasterPassword()throws Exception {
		
		this.mConf=new Configuration();
		this.mBank=new Bank();
		this.mDatabase=new Database();	
	}
	
	public void create(String path, String password, String keyfile, String sid)
	{
		this.mpassword = password;
		this.path=path;
		this.mkeyfile = keyfile;
		this.msid = sid;
	}
	
	public String getMpassword() {
		return mpassword;
	}

	public void setMpassword(String mpassword) {
		this.mpassword = mpassword;
	}

	public String getMkeyfile() {
		return mkeyfile;
	}

	public void setMkeyfile(String mkeyfile) {
		this.mkeyfile = mkeyfile;
	}

	public String getMsid() {
		return msid;
	}

	public void setMsid(String msid) {
		this.msid = msid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void importFromConf() 
	{
		
	}


	public Configuration getmConf() {
		return mConf;
	}


	public String getPath() {
		return path;
	}


	public void setPath(String path) {
		this.path = path;
	}


	public Database getmDatabase() {
		return mDatabase;
	}


	public Bank getmBank() {
		return mBank;
	}

	public String getPassword() {
		return mpassword;
	}

	public void setPassword(String password) {
		this.mpassword = password;
	}

	public String getKeyfile() {
		return mkeyfile;
	}

	public void setKeyfile(String keyfile) {
		this.mkeyfile = keyfile;
	}

	public String getSid() {
		return msid;
	}

	public void setSid(String sid) {
		this.msid = sid;
	}
	
	public boolean signin(File infile,String inpassword,boolean password, boolean file, boolean sid) throws IOException, NoSuchAlgorithmException
	{
		if(file&&password)
			return (comparefiles(infile, mkeyfile)&&comparepasswords(inpassword, mpassword));
		if(password)
			return (comparepasswords(inpassword, mpassword));
		if(sid)
		    return (comparesids(msid));
			return false;
	}
	
	public void createfile()
	{
		
	}
	
}
