package keyper;

import java.util.HashSet;
import java.util.Set;

public class Bank  {
	private Set<Key> bank;
	private static Bank instance=new Bank();

	private Bank() {
		
		this.bank=new HashSet<>();
	}
	
	public static Bank GetInstance()
	{
		return instance;
	}
	
	public Set<Key> getBank() {
		return this.bank;
	}

	public void addkey(Key key)
	{
		bank.add(key);
	}
	
	public void removekey(Key key)
	{
		bank.remove(key);
	}
	
	public boolean isexist(Key key)
	{
		return bank.contains(key);
	}
	
	
	public void search(String keyid)
	{
		
	}

	

}
