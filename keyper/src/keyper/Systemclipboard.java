package keyper;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

public class Systemclipboard 
{
	
	public static Clipboard clipboard=Toolkit.getDefaultToolkit().getSystemClipboard();
	
	
	        
	public static void copy(String text)
    {
		clipboard.setContents(new StringSelection(text), new StringSelection(text));
    }
			

	public static void clear()
    {
     	clipboard.setContents(new StringSelection(""), new StringSelection(""));
    }	
}


