package keyper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Key {
	
	public static SimpleDateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy");
	private static int num = 0;
	private int mId;
	private String mTitle;
	private String mUsername;
	private String mPassword;
	private int mQuality;
	private String mUrl;
	private Date mExpired;
    private Map<Date, Key> mHistory;
    
    
	public Key(String mId, String mTitle, String mUsername, String mPassword, int mQuality, String mUrl, Date mExpired) {
		this.mId=num++;
		this.mTitle = mTitle;
		this.mUsername = mUsername;
		this.mPassword = mPassword;
		this.mQuality = 0;
		this.mUrl = mUrl;
		this.mExpired = mExpired;
		this.mHistory = new HashMap<>();
	}


	public int getmId() {
		return mId;
	}


	public String getmTitle() {
		return mTitle;
	}


	public void setmTitle(String mTitle) {
		this.mTitle = mTitle;
	}


	public String getmUsername() {
		return mUsername;
	}


	public void setmUsername(String mUsername) {
		this.mUsername = mUsername;
	}


	public String getmPassword() {
		return mPassword;
	}


	public void setmPassword(String mPassword) {
		this.mPassword = mPassword;
	}


	public int getmQuality() {
		return mQuality;
	}


	public void setmQuality(int mQuality) {
		this.mQuality = mQuality;
	}


	public String getmUrl() {
		return mUrl;
	}


	public void setmUrl(String mUrl) {
		this.mUrl = mUrl;
	}


	public Date getmExpired() {
		return mExpired;
	}


	public void setmExpired(Date mExpired) {
		this.mExpired = mExpired;
	}


	public Map<Date, Key> getmHistory() {
		return mHistory;
	}


	public void setmHistory(Map<Date, Key> mHistory) {
		this.mHistory = mHistory;
	}
    
    
   
}
