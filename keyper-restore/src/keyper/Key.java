package keyper;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Observable;
import java.util.Set;
import java.util.Vector;
import java.util.concurrent.TimeUnit;

import javax.swing.JProgressBar;

import keyper.View.BankListWindow;



public class Key extends Observable implements Gen{
	
	private final int mAutocleartime=12; //wait after copy to clipboard
	private String mId;
	private String mGroup;
	private String mTitle;
	private String mUsername;
	private String mPassword;
	private int mQuality;
	private String mUrl;
	private Date mExpired;
	private boolean expiredCheck;
    private Map<Date, Key> mHistory;


  	@SuppressWarnings("deprecation")
	public Key (String mTitle,String mGroup, String mUsername, String mPassword, String mUrl){
  		
  		this.mId=Gen.generate(5, false, false, false, false);
  		this.mGroup=mGroup;
		this.mTitle = mTitle;
		this.mUsername = mUsername;
		this.mPassword = mPassword;
		this.mQuality = 0;
		this.mUrl = mUrl;
		this.mExpired = new Date();
		this.expiredCheck=false;
		this.mHistory = new HashMap<>();
		mHistory.put(new Date(), this);
		
	}


	public Key() {
		
	}

	public void notifyChanges()
	{
		System.out.println(super.countObservers());
		super.setChanged();
		super.notifyObservers(this);
	}
	
	public void setExpiredCheck(boolean expiredCheck) {
		this.expiredCheck = expiredCheck;
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
	}


	public String getmPassword() {
		return mPassword;
		
	}


	public void setmPassword(String mPassword) {
		this.mPassword = mPassword;
	}
	
	public void generatePassword(int lenght,boolean capital, boolean letter, boolean numbers, boolean specials) {
	this.mPassword=Gen.generate(lenght, capital, letter, numbers, specials);
		
	}
	
	public void InsertToHistory()
	{
		this.mHistory.put(new Date(), this);
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
	
	public String ExpiredDateToString()
	{
		return mExpired.getDate()+"/"+(mExpired.getMonth()+1)+"/"+(mExpired.getYear()+1900);
	}

	@SuppressWarnings("deprecation")
	public void setmExpired(int year,int month, int date) {
	   this.mExpired=new Date(year-1900,month-1,date);
	}    
    
	public int getmAutocleartime() {
		return mAutocleartime;
	}

	public void clearClipBoard()
	{
		Systemclipboard.clear();
	}
	
	public void copyusername() throws InterruptedException
	{
		Systemclipboard.copy(this.mUsername);
	}
	
	public void copypassword() throws InterruptedException
	{
		Systemclipboard.copy(this.mPassword);
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
	
	public boolean ExpiredStatus()
	{
		if((expiredCheck)&&(mExpired.before(new Date())))
			return true;
		else
			return false;
	}


	public boolean getExpiredCheck() {
		return expiredCheck;
	}
	
	
	public void Restore(Date date)
	{
		this.mId=this.getmHistory().get(date).mId;
		this.mTitle=this.getmHistory().get(date).mTitle;
		this.mUsername=this.getmHistory().get(date).mUsername;
		this.mPassword=this.getmHistory().get(date).mPassword;
		this.mUrl=this.getmHistory().get(date).mUrl;
		this.mExpired=new Date();
		this.expiredCheck=false;
		this.mHistory.clear();
		this.mHistory.put(new Date(), this);
	}
}
