package keyper;

public class tests extends Autentication
{


	public static void main(String[] args) throws Exception 
	{
		Key key=new Key("gmail","Email", "noy5277@gmail.com", "P@ssw0rd", "gmail.com");
	    MasterPassword master=new MasterPassword();
	    master.create("C:\\sqlite\\1\\database.db","buhnoy5667728");
	    master.getmDatabase().create();
	    master.getmBank().addkey(key);
	    master.getmConf().createFiles(master);
	    master.getmDatabase().close(master.getmBank());
	}
	


}