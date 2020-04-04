package keyper;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class MasterPassword extends Autentication{

	private String mpassword;
	private String mkeyfile;
	private String msid;
	private boolean valid=true;
	private ArrayList<Boolean> check=new ArrayList<>();
	
	public MasterPassword(String password, String keyfile, String sid)throws NoSuchAlgorithmException {
		this.mpassword = password;
		this.mkeyfile = keyfile;
		this.msid = sid;
		
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
	
	public boolean login(File infile,String inpassword,boolean password, boolean file, boolean sid) throws IOException, NoSuchAlgorithmException
	{
		if(password)
			check.add(comparepasswords(inpassword, mpassword));
		if(file)
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
