package keyper;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;



public class Key {
	
	private int mAutocleartime;
	private static int num = 0;
	private int mId;
	private String mTitle;
	private String mUsername;
	private String mPassword;
	private int mQuality;
	private String mUrl;
	private Date mExpired;
    private Map<Date, String> mHistory;
    
  	public Key (String mTitle, String mUsername, String mPassword, String mUrl){
		this.mAutocleartime=12;
  		this.mId=num++;
		this.mTitle = mTitle;
		this.mUsername = mUsername;
		this.mPassword = mPassword;
		this.mQuality = 0;
		this.mUrl = null;
		this.mExpired = null;
		this.mHistory = new HashMap<>();
		mHistory.put(new Date(), this.mPassword);
	}

	@SuppressWarnings("unlikely-arg-type")
	public void deletehistorykey(Date date)
    {
		this.mHistory.remove(this.mHistory.get(date));
    }
    
	public String searchhistorykey(Date date)
	{
		return (this.mHistory.get(date));
	}
	
	public Set<Date> gethistorydates() {
		
		return this.mHistory.keySet();
		
	}

    
	public Set<java.util.Map.Entry<Date, String>> gethistory()
	{
		return (this.mHistory.entrySet());
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

	public void setmExpired(Date mexpired) {
	   this.mExpired=mexpired;
	}    
    
	public int getmAutocleartime() {
		return mAutocleartime;
	}

	public void setmAutocleartime(int mAutocleartime) {
		this.mAutocleartime = mAutocleartime;
	}

	public void copyusername() throws InterruptedException
	{
		Systemclipboard.copy(this.mUsername);
		TimeUnit.SECONDS.sleep(mAutocleartime);
		Systemclipboard.clear();
	}
	
	public void copypassword() throws InterruptedException
	{
		Systemclipboard.copy(this.mPassword);
		TimeUnit.SECONDS.sleep(mAutocleartime);
		Systemclipboard.clear();
	}
   
}
