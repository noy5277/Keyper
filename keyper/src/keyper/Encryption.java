package keyper;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

public abstract class Encryption extends Generator{
	
	private static String myKey;
	final Cipher cipher;
	private static SecretKeySpec secretKey;
	
	public Encryption() throws NoSuchAlgorithmException, NoSuchPaddingException {
		this.cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
		this.myKey="shhhhhh!!!!!!";
	}
	
	@SuppressWarnings("static-access")
	public String createPrivateKey()
	{
		this.myKey=generate(15, true, true, true, true);
		return this.myKey;
	}
	
	public String getMyKey() {
		return myKey;
	}

	public void setMyKey(String myKey) {
		Encryption.myKey = myKey;
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
	
	
	public String encrypt (String strToEncrypt) throws IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException, IOException, InvalidKeyException
	 {
		 
		 cipher.init(Cipher.ENCRYPT_MODE, setKey());
		 return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
		 
	 }
	 
	 public String decrypt (String strToDecrypt) throws IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException, IOException, InvalidKeyException
	 {
		 cipher.init(Cipher.DECRYPT_MODE, setKey());
		 return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
	 }


}
