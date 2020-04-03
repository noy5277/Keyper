package keyper;


public class tests {

	
	public static void main(String[] args) throws InterruptedException {
		Key key=new Key("email", "noy5277@gmail.com", "P@ssw0rd", "gmail.com");
		key.generatePassword(15, true, false, true, false);
		
	}
	

}
