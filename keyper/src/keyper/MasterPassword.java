package keyper;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.function.Predicate;

import javax.crypto.NoSuchPaddingException;

public class MasterPassword extends Autentication{

	private String mpassword;
	private String mkeyfile;
	private String msid;
	private Bank mBank;
	private Database mDatabase;
	
	public MasterPassword(String DBpath, String password, String keyfile, String sid)throws NoSuchAlgorithmException, NoSuchPaddingException, InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		this.mpassword = password;
		this.mkeyfile = keyfile;
		this.msid = sid;
		this.mBank=new Bank();
		this.mDatabase=new Database(DBpath, password);
		
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
	
	
}
