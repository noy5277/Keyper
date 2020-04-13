package keyper;

import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;



public class Key extends Generator {
	
	private final int mAutocleartime=12; //wait after copy to clipboard
	private String mId;
	private String mGroup;
	private String mTitle;
	private String mUsername;
	private String mPassword;
	private int mQuality;
	private String mUrl;
	private Date mExpired;
    private Map<Date, Key> mHistory;
    final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    
  	public Key (String mTitle,String mGroup, String mUsername, String mPassword, String mUrl){
  		this.mId=generate(5, false, false, true, false);
  		this.mGroup=mGroup;
		this.mTitle = mTitle;
		this.mUsername = mUsername;
		this.mPassword = mPassword;
		this.mQuality = 0;
		this.mUrl = mUrl;
		this.mExpired = null;
		this.mHistory = new HashMap<>();
		mHistory.put(new Date(), this);
	}


	@SuppressWarnings("unlikely-arg-type")
	public void deletehistorykey(Date date)
    {
		this.mHistory.remove(this.mHistory.get(date));
    }
    
	public Key viewhistorykey(Date date)
	{
		return (this.mHistory.get(date));
	}
	
	public String getmGroup() {
		return mGroup;
	}

	public void setmGroup(String mGroup) {
		this.mGroup = mGroup;
	}

	public void restore(Date date)
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
	
	
	public Set<Date> gethistorydates() {
		
		return this.mHistory.keySet();
		
	}

    
	public Set <?> gethistory()
	{
		return (this.mHistory.entrySet());
	}
	
	public String getmId() {
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
		this.mUsername=mUsername;
		mHistory.put(new Date(),new Key(this.mTitle,this.mGroup, mUsername, this.mPassword, this.mUrl));
		
	}


	public String getmPassword() {
		return mPassword;
		
	}


	public void setmPassword(String mPassword) {
		this.mPassword = mPassword;
		this.mHistory.put(new Date(), new Key(this.mTitle,this.mGroup, this.mUsername, mPassword, this.mUrl));

	}
	
	public void generatePassword(int lenght,boolean capital, boolean letter, boolean numbers, boolean specials) {
		this.mPassword=generate(lenght, capital, letter, numbers, specials);
		this.mHistory.put(new Date(), new Key(this.mTitle,this.mGroup, this.mUsername, this.mPassword, this.mUrl));
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
		Key k;
		Date ldc;
		Set<?> entries=this.mHistory.entrySet();
		Iterator<?> itr=entries.iterator();
		while(itr.hasNext())
		{
			@SuppressWarnings("rawtypes")
			Map.Entry e=(Map.Entry)itr.next();
			ldc=(Date)e.getKey();
			k=(Key)e.getValue();
			System.out.println(ldc+"="+k.getmPassword()+"="+k.getmId());
			
		}
		
	}

	public void setmId(String mid) {
		this.mId = mid;
		
	}


	public void setmHistory(Map<Date, Key> mHistory) {
		this.mHistory = mHistory;
	}


	public Map<Date, Key> getmHistory() {
		return mHistory;
	}
   
}
