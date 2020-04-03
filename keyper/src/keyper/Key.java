package keyper;


import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;



public class Key extends Generator {
	
	private int mAutocleartime;
	private static int num = 0;
	private int mId;
	private String mTitle;
	private String mUsername;
	private String mPassword;
	private int mQuality;
	private String mUrl;
	private LocalDateTime mExpired;
    private Map<LocalDateTime, Key> mHistory;
    
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
		mHistory.put(LocalDateTime.now(), this);
	}

	@SuppressWarnings("unlikely-arg-type")
	public void deletehistorykey(Date date)
    {
		this.mHistory.remove(this.mHistory.get(date));
    }
    
	public Key viewhistorykey(LocalDateTime date)
	{
		return (this.mHistory.get(date));
	}
	
	public void restore(LocalDateTime date)
	{
		Key r=this.mHistory.get(date);
		this.mId=r.mId;
		this.mTitle = r.mTitle;
		this.mUsername = r.mUsername;
		this.mPassword =r.mPassword;
		this.mQuality = r.mQuality;
		this.mUrl = r.mUrl;
		this.mExpired = r.mExpired;
		
	}
	
	
	public Set<LocalDateTime> gethistorydates() {
		
		return this.mHistory.keySet();
		
	}

    
	public Set<java.util.Map.Entry<LocalDateTime, Key>> gethistory()
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
		mHistory.put(LocalDateTime.now(), this);
	}


	public String getmPassword() {
		return mPassword;
	}


	public void setmPassword(String mPassword) {
		this.mPassword = mPassword;
		mHistory.put(LocalDateTime.now(), this);
	}
	
	public void generatePassword(int lenght,boolean capital, boolean letter, boolean numbers, boolean specials) {
		this.mPassword=generate(lenght, capital, letter, numbers, specials);
		mHistory.put(LocalDateTime.now(), this);
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


	public LocalDateTime getmExpired() {
		return mExpired;
	}

	public void setmExpired(LocalDateTime mexpired) {
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
	
	public void printhistory() {
		Set<?> entries=this.mHistory.entrySet();
		Iterator<?> itr=entries.iterator();
		while(itr.hasNext())
		{
			@SuppressWarnings("rawtypes")
			Map.Entry e=(Map.Entry)itr.next();
			System.out.println(e.getKey()+"="+e.getValue());
			
		}
		
	}
   
}
