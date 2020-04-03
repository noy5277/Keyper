package keyper;

import java.util.ArrayList;
import java.util.Random;


public abstract class Generator {

	enum stat{capital,letter,numbers,specials};
	private Random rand;
	private StringBuilder password;
	private ArrayList<stat> properties;
	private int ch;
	private int index;
	
	public Generator() {
		rand=new Random();
		password=new StringBuilder();
		properties=new ArrayList<>();
	}
	
	
	public String generate(int lenght,boolean capital, boolean letter, boolean numbers, boolean specials) {
	
		if(capital)
			properties.add(stat.capital);
		if(letter)
			properties.add(stat.letter);
		if(numbers)
			properties.add(stat.numbers);
		if(specials)
			properties.add(stat.specials);
		
		for(int i=0;i<lenght;i++)
		{
			index=rand.nextInt(properties.size());
			switch(properties.get(index))
			{
			  case capital:
				  ch=65+rand.nextInt(25);
				  password.insert(i,(char) ch);
				  break;
			  case letter:
				  ch=97+rand.nextInt(25);
				  password.insert(i,(char) ch);
				  break;
			  case numbers:
				  ch=48+rand.nextInt(9);
				  password.insert(i,(char) ch);
				  break;
			  case specials:
				  ch=33+rand.nextInt(14);
				  password.insert(i,(char) ch);
				  break;
			  //add error
			
			}
			
		}
		
		return password.toString();
	}

}
