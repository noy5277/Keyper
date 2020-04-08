package keyper;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.function.Predicate;

import javax.crypto.NoSuchPaddingException;

public class MasterPassword extends Autentication{

	private String mpassword;
	private String mkeyfile;
	private String msid;
	private boolean valid=true;
	private ArrayList<Boolean> check=new ArrayList<>();
	private Bank mBank;
	private Database mDatabase;
	
	
	public MasterPassword(Database mDatabase, String password, String keyfile, String sid)throws NoSuchAlgorithmException, NoSuchPaddingException {
		this.mpassword = password;
		this.mkeyfile = keyfile;
		this.msid = sid;
		this.mBank=new Bank();
		this.mDatabase=mDatabase;
		
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
		if(password)
			check.add(comparepasswords(inpassword, mpassword));
		if(file&&password)
			check.add(comparefiles(infile, mkeyfile));
		if(sid)
		    check.add(comparesids(msid));
		 
		for(int i=0;i<check.size();i++)
		{
			valid=check.get(i) && valid;
		}
		return valid;

	}
	
	
}
