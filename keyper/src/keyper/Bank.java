package keyper;

import java.util.HashSet;
import java.util.Set;

public class Bank  {
	private Set<Key> bank;

	public Bank() {
		
		this.bank=new HashSet<>();
	}
	
	public Set<Key> getBank() {
		return this.bank;
	}

	void addkey(Key key)
	{
		bank.add(key);
	}
	
	void removekey(Key key)
	{
		bank.remove(key);
	}
	
	boolean isexist(Key key)
	{
		return bank.contains(key);
	}
	
	public void sync(Bank bank)
	{
		
	}
	
	public void search(Bank bank)
	{
		
	}

	

}
