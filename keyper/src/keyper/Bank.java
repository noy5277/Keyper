package keyper;

import java.util.HashSet;
import java.util.Set;

public class Bank  {
	private Set<Key> bank;

	public Bank() {
		
		this.bank = new HashSet<>();
		
	}
	
	void addkey(Key key)
	{
		bank.add(key);
	}
	
	void removekey(Key key)
	{
		bank.remove(key);
	}
	
	boolean searchkey(Key key)
	{
		return bank.contains(key);
	}

}
