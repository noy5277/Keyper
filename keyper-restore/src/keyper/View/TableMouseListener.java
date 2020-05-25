package keyper.View;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Map;

import javax.swing.JTable;
import javax.swing.JTextField;

import keyper.Key;

public class TableMouseListener extends MouseAdapter {
    
    private JTable table;
    private JTextField text;
    private Map<Integer ,Key> keyMap;
    public TableMouseListener(JTable table) {
        this.table = table;
    }
    
    public TableMouseListener(JTable table, JTextField text, Map<Integer ,Key> keyMap) {
        this.table = table;
        this.text=text;
        this.keyMap=keyMap;
    }
     
    @Override
    public void mousePressed(MouseEvent event) {
        // selects the row at which point the mouse is clicked
        Point point = event.getPoint();
        int currentRow = table.rowAtPoint(point);
        String str,exp = null;
        if(currentRow>=0)
        {
        	 table.setRowSelectionInterval(currentRow, currentRow);
        
          if(text!=null)
          {
        	str="Title: " + keyMap.get(currentRow).getmTitle()+"   "
        	   +"Username: " +keyMap.get(currentRow).getmUsername()+"   "
        	   +"Password: "+ "********"+"   "
        	   +"Url: "+ keyMap.get(currentRow).getmUrl()+"   ";
        	if(keyMap.get(currentRow).ExpiredStatus())
        	{
        		exp="Expired: " + keyMap.get(currentRow).ExpiredDateToString();
        	}
        	else
        	{
        		exp="";
        	}
        	keyfieldshow(str,exp);
         }
    }  }
    
    public void keyfieldshow(String str,String exp)
    {
    	text.setText(str+exp);
    }
}