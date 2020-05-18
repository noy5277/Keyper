package keyper.View;

import javax.swing.JProgressBar;

import keyper.Key;

public class ProgressBarThread extends Thread
{
		private Key k=new Key();
    	private JProgressBar progressbar;
		private int seconds;
		private int status;
		public ProgressBarThread(JProgressBar pb, int sec)
		{
			progressbar=pb;
			seconds=sec;
			status=0;
		}
		@Override
		public void run()
		{
			while(status<=seconds)
			{
				status++;
		 		progressbar.setValue(status);
		 		try
		 		{
		 			
		 			Thread.sleep(500);
		 		}
		 		catch(Exception e)
		 		{
		 			e.printStackTrace();
		 		}
			}
			progressbar.setValue(0);
			k.clearClipBoard();
		}
}

