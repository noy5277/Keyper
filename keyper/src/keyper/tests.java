package keyper;


import java.time.LocalDateTime;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import javax.security.auth.*;

public class tests {

	
	public static void main(String[] args) throws InterruptedException {
		Key key=new Key("email", "noy5277@gmail.com", "P@ssw0rd", "gmail.com");
		System.out.println(key.generate(18, true, false, false, true));
		
	}
	

}
