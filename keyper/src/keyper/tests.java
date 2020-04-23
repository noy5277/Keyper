package keyper;

public class tests extends Autentication
{


	public static void main(String[] args) throws Exception 
	{
		Key key=new Key("gmail","email", "noy5277@gmail.com", "P@ssw0rd", "gmail.com");
	    MasterPassword master=new MasterPassword();
	    Configuration conf=new Configuration(master);
	    master.create("C:\\sqlite\\db\\database.db","buhnoy5667728", "bdika","11");
	    master.getmDatabase().create();
	   conf.createFiles(master);
	}
	


}