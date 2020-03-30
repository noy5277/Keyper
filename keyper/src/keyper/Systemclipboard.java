package keyper;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

public class Systemclipboard 
{
	
	public static Clipboard clipboard;
		
	public Systemclipboard()
	{
		Systemclipboard.clipboard= Toolkit.getDefaultToolkit().getSystemClipboard();
	}
		
	        
	public static void copy(String text)
    {
		clipboard.setContents(new StringSelection(text), null);
    }
			

	public static void clear()
    {
     	clipboard.setContents(null, null);
    }	
}


