package keyper.View;
import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;


public class JFilePicker {
	
	private JFileChooser fileChooser;
	private int mode;
	private JButton button;
	private String path;
    public static final int MODE_OPEN = 1;
    public static final int MODE_SAVE = 2;
    
    
    public JFilePicker(int mode,JButton button)
    {
    	this.button=button;
    	this.mode=mode;
    	fileChooser = new JFileChooser();
    	
	}

	String buttonActionPerformed(ActionEvent evt) 
    {
		
        if (mode == MODE_OPEN)
        {
            if(fileChooser.showOpenDialog(button) == JFileChooser.APPROVE_OPTION)
            {
            	path=(fileChooser.getSelectedFile().getAbsolutePath());
            	return path;
            }
        }
        else if (mode == MODE_SAVE)
        {
            if (fileChooser.showSaveDialog(button) == JFileChooser.APPROVE_OPTION)
            {
            	path=(fileChooser.getSelectedFile().getAbsolutePath());
            	return path;
            }
        }
        return "";
		
    }
    
    public void addFileTypeFilter(String extension, String description) {
        FileTypeFilter filter = new FileTypeFilter(extension, description);
        fileChooser.addChoosableFileFilter(filter);
    }
    
    @SuppressWarnings("unused")
	private String getSelectedFilePath()
    {	
           return path;
    }
    
    
	public void setFileName(String filename)
    {
		fileChooser.setSelectedFile(
				new File(fileChooser.getCurrentDirectory().getAbsolutePath() +
				"\\" + filename));
    }
    

	@SuppressWarnings("unused")
	private JFileChooser getFileChooser() {
        return this.fileChooser;
    }
    
    

}
