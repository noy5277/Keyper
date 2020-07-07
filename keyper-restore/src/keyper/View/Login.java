package keyper.View;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import keyper.Key;
import keyper.MasterPassword;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;

import java.awt.Color;
import javax.swing.JPasswordField;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.swing.ImageIcon;


@SuppressWarnings("serial")
public class Login extends JFrame {

	private JPanel contentPane;
	private JPasswordField passwordField;
	private JTextField pathview;
	private JLabel keyfileLabel;
	private JTextField keyfileField;
	@SuppressWarnings("unused")
	private JCheckBox passwordcheckbox;
	private ActionListener cancelBtnAction;
	private BankListWindow fram;
	private MasterPassword editmaster;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					
				    MasterPassword master=new MasterPassword();
				    if(new File("C:\\Users\\"+master.getUsername()+"\\AppData\\Roaming\\Keyper").exists())
				    {
				    	master.importConfiguration();
				    	
				    }
				    Login frame = new Login(master);
				 	frame.setVisible(true);
				    
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws IOException 
	 * @throws NoSuchAlgorithmException 
	 */
	@SuppressWarnings("deprecation")
	public Login(MasterPassword master)  {
		
		InitActionListeners();
		
		editmaster=master;
		setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/keyper/View/Icons/secrecy-icon.png")));
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 438, 243);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		pathview = new JTextField();
		pathview.setToolTipText("");
		pathview.setText("Database: "+master.getmConf().getmLastDbPath());
		pathview.setBackground(SystemColor.menu);
		pathview.setFont(new Font("Myanmar Text", Font.PLAIN, 10));
		pathview.setBounds(0, 184, 422, 20);
		contentPane.add(pathview);
		pathview.setColumns(10);
		
		JLabel passwordLabel = new JLabel("Master Password:");
		passwordLabel.setFont(new Font("Myanmar Text", Font.PLAIN, 14));
		passwordLabel.setBounds(46, 58, 115, 20);
		contentPane.add(passwordLabel);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		passwordField.setBounds(164, 54, 201, 20);
		contentPane.add(passwordField);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.activeCaption);
		panel.setBounds(0, 0, 422, 39);
		contentPane.add(panel);
		
		JLabel title = new JLabel("Keyper");
		panel.add(title);
		title.setForeground(new Color(255, 255, 255));
		title.setFont(new Font("Tahoma", Font.BOLD, 24));
		
		keyfileLabel = new JLabel("Key File:");
		keyfileLabel.setFont(new Font("Myanmar Text", Font.PLAIN, 14));
		keyfileLabel.setBounds(46, 89, 115, 20);
		contentPane.add(keyfileLabel);
		
		JCheckBox passwordcheckbox = new JCheckBox("");
		passwordcheckbox.setBounds(16, 58, 22, 20);
		contentPane.add(passwordcheckbox);
		
		JCheckBox filekeycheckbox = new JCheckBox("");
		filekeycheckbox.setBounds(16, 90, 22, 19);
		contentPane.add(filekeycheckbox);
		
		JButton keyfilebutton = new JButton("");
		keyfilebutton.setIcon(new ImageIcon(Login.class.getResource("/keyper/View/Icons/folder-blue-icon.png")));
		keyfilebutton.setBounds(369, 87, 18, 18);
		contentPane.add(keyfilebutton);
		JFilePicker filePicker = new JFilePicker(1,keyfilebutton);
		keyfilebutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
            	keyfileField.setText(filePicker.buttonActionPerformed(evt));            
            }
        });
		
		JLabel lblNewLabel_1 = new JLabel("Windows Integration");
		lblNewLabel_1.setFont(new Font("Myanmar Text", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(46, 120, 138, 20);
		contentPane.add(lblNewLabel_1);
		
		JCheckBox sidcheckbox = new JCheckBox("");
		sidcheckbox.setBounds(16, 116, 22, 24);
		contentPane.add(sidcheckbox);
		
		keyfileField = new JTextField();
		keyfileField.setFont(new Font("Myanmar Text", Font.PLAIN, 14));
		keyfileField.setBounds(164, 85, 201, 20);
		contentPane.add(keyfileField);
		keyfileField.setColumns(10);
		
		JButton CancelButton = new JButton("Cancel");
		CancelButton.setBounds(323, 159, 89, 23);
		contentPane.add(CancelButton);
		CancelButton.addActionListener(cancelBtnAction);
		
		JButton OKButton = new JButton("OK");
		OKButton.setBounds(231, 159, 89, 23);
		contentPane.add(OKButton);
		OKButton.setActionCommand("login");
		OKButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				 try {
					okactionPerformed();
				} catch (NoSuchAlgorithmException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvalidKeyException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalBlockSizeException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (BadPaddingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		       }

			public void okactionPerformed() throws NoSuchAlgorithmException, IOException, SQLException, ClassNotFoundException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException
			{
			try
				{
			if(master.signin(new File(keyfileField.getText()), passwordField.getText() , passwordcheckbox.isSelected(),filekeycheckbox.isSelected() ,sidcheckbox.isSelected()))
			{
				
			   master.getmDatabase().connect();
			   master.getmDatabase().pull(master.getmBank());
			   fram=new BankListWindow(master);
			   fram.setVisible(true);
			   closewindow();
			 }
	     	else
	     	{
	     		JOptionPane.showMessageDialog(null,
	     			"Failed to load a specific file, "
				    + "The password key is invalid ",
				    "Login failed",
			    JOptionPane.WARNING_MESSAGE);
	     		System.out.println(master.getMpassword());
		   	}
			}
			catch(FileNotFoundException e)
			{
				JOptionPane.showMessageDialog(null,
		     			"Failed to load a specific file, "
		     			+"file does not exist",
					    "Error",
				    JOptionPane.ERROR_MESSAGE);
			}
			catch(SQLException e)
			{
				JOptionPane.showMessageDialog(null,
						"Failed to load a specific file, "
						+"another window is already open ",
					    "Error",
				    JOptionPane.ERROR_MESSAGE);
			}
			
		 }
    });
		
		switch(master.getmConf().getmLastConnectionStat())
	    {
	    case 1:
	    	passwordcheckbox.setSelected(true);
	    	break;
	    case 2:
	    	passwordcheckbox.setSelected(true);
	    	filekeycheckbox.setSelected(true);
	    	break;
	    case 3:
	    	sidcheckbox.setSelected(true);
	    	break;
	    }
		
   }

	private void InitActionListeners()
	{
		cancelBtnAction=new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				fram=new BankListWindow(editmaster);
				fram.DisableAllButtons();
				fram.setVisible(true);
				closewindow();
			}
		};
	}
 private void closewindow() 
 {
	this.dispose();
 }
	

}
