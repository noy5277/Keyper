package keyper.View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import keyper.MasterPassword;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.awt.SystemColor;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JPasswordField;
import javax.swing.JTextPane;
import javax.swing.UIManager;

public class NewDatabaseFram extends JFrame {

	private JPanel contentPane;
	private JTextField PathField;
	private JPasswordField passwordField;
	private JPasswordField repeatField;
	private JTextField keyfileField;
	private ActionListener PathdbAction;
	private ActionListener masterPasswordCbxAction;
	private ActionListener keyfileAction;
	private ActionListener windowsAccountAction;
	private ActionListener keyFileCbxAction;
	private ActionListener cancelBtnAction;
	private ActionListener okBtnAction;
	private JFilePicker dbfilePicker;
	private JFilePicker keyfilePicker;
	private JCheckBox keyFileCbx;
	private JCheckBox windowsAccountCbx;
	private JCheckBox masterPasswordCbx;
	private MasterPassword master;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewDatabaseFram frame = new NewDatabaseFram();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws Exception 
	 */
	public NewDatabaseFram() throws Exception {
		
		InitActionListeners();
		
		master=new MasterPassword();
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(NewDatabaseFram.class.getResource("/keyper/View/Icons/Misc-Database-3-icon.png")));
		setTitle("New Database");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 487, 373);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.activeCaption);
		panel.setBounds(0, 0, 471, 43);
		contentPane.add(panel);
		
		JLabel lblNewLabel = new JLabel("Create Master Key");
		lblNewLabel.setForeground(SystemColor.text);
		lblNewLabel.setFont(new Font("Myanmar Text", Font.BOLD, 18));
		panel.add(lblNewLabel);
		
		JLabel pathLabel = new JLabel("Path:");
		pathLabel.setFont(new Font("Myanmar Text", Font.PLAIN, 14));
		pathLabel.setBounds(25, 69, 36, 21);
		contentPane.add(pathLabel);
		
		PathField = new JTextField();
		PathField.setEditable(false);
		PathField.setBounds(77, 67, 332, 20);
		contentPane.add(PathField);
		PathField.setColumns(10);
		
		JButton pathdbBtn = new JButton("");
		pathdbBtn.setIcon(new ImageIcon(NewDatabaseFram.class.getResource("/keyper/View/Icons/folder-blue-icon.png")));
		pathdbBtn.setBounds(419, 66, 29, 23);
		contentPane.add(pathdbBtn);
		dbfilePicker = new JFilePicker(1,pathdbBtn);
		dbfilePicker.addFileTypeFilter(".db", "Keyper database file");
		dbfilePicker.setFileName("database.db");
		pathdbBtn.addActionListener(PathdbAction);
		
		JLabel masterpasswordLabel = new JLabel("Master Password:");
		masterpasswordLabel.setFont(new Font("Myanmar Text", Font.PLAIN, 14));
		masterpasswordLabel.setBounds(47, 110, 108, 33);
		contentPane.add(masterpasswordLabel);
		
		masterPasswordCbx = new JCheckBox("");
		masterPasswordCbx.setBounds(20, 110, 21, 23);
		contentPane.add(masterPasswordCbx);
		masterPasswordCbx.addActionListener(masterPasswordCbxAction);
		
		JLabel repeatLabel = new JLabel("Repeat:");
		repeatLabel.setFont(new Font("Myanmar Text", Font.PLAIN, 14));
		repeatLabel.setBounds(47, 142, 46, 21);
		contentPane.add(repeatLabel);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(164, 110, 267, 20);
		contentPane.add(passwordField);
		
		repeatField = new JPasswordField();
		repeatField.setBounds(164, 142, 267, 20);
		contentPane.add(repeatField);
		
		keyFileCbx = new JCheckBox("");
		keyFileCbx.setBounds(20, 184, 21, 23);
		contentPane.add(keyFileCbx);
		keyFileCbx.addActionListener(keyFileCbxAction);
		
		JLabel keyfileLabel = new JLabel("Key File:");
		keyfileLabel.setFont(new Font("Myanmar Text", Font.PLAIN, 14));
		keyfileLabel.setBounds(45, 185, 59, 33);
		contentPane.add(keyfileLabel);
		
		keyfileField = new JTextField();
		keyfileField.setEditable(false);
		keyfileField.setBounds(114, 187, 317, 20);
		contentPane.add(keyfileField);
		keyfileField.setColumns(10);
		
		JButton keyfileBtn = new JButton("");
		keyfileBtn.setIcon(new ImageIcon(NewDatabaseFram.class.getResource("/keyper/View/Icons/folder-key-icon.png")));
		keyfileBtn.setBounds(440, 184, 21, 23);
		keyfilePicker= new JFilePicker(1,keyfileBtn);
		keyfileBtn.addActionListener(keyfileAction);
		contentPane.add(keyfileBtn);
		
		JTextPane txtpn = new JTextPane();
		txtpn.setText("A Key file can be used as a part of the master key ; if the key file is lost the database cant be open anymore.");
		txtpn.setBackground(SystemColor.menu);
		txtpn.setEditable(false);
		txtpn.setBounds(25, 214, 406, 33);
		contentPane.add(txtpn);
		
		windowsAccountCbx = new JCheckBox("");
		windowsAccountCbx.setFont(new Font("Myanmar Text", Font.PLAIN, 14));
		windowsAccountCbx.setBounds(20, 263, 21, 21);
		contentPane.add(windowsAccountCbx);
		windowsAccountCbx.addActionListener(windowsAccountAction);
		
		JLabel windowsaccountLabel = new JLabel("Windows user account");
		windowsaccountLabel.setFont(new Font("Myanmar Text", Font.PLAIN, 14));
		windowsaccountLabel.setBounds(47, 263, 138, 33);
		contentPane.add(windowsaccountLabel);
		
		JButton okBtn = new JButton("OK");
		okBtn.setBounds(278, 300, 89, 23);
		contentPane.add(okBtn);
		okBtn.addActionListener(okBtnAction);
		
		JButton cancelBtn = new JButton("Cancel");
		cancelBtn.setBounds(372, 300, 89, 23);
		contentPane.add(cancelBtn);
		cancelBtn.addActionListener(cancelBtnAction);
	}
	
	private void InitActionListeners()
	{
		PathdbAction=new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				PathField.setText(dbfilePicker.buttonActionPerformed(e));
			}
		};
		
		keyfileAction=new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				keyfileField.setText(keyfilePicker.buttonActionPerformed(e));
			}
		};
		
		masterPasswordCbxAction=new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				if(keyFileCbx.isEnabled())
				    keyFileCbx.setEnabled(false);
				else
					keyFileCbx.setEnabled(true);
				if(windowsAccountCbx.isEnabled())
				    windowsAccountCbx.setEnabled(false);
				else
					windowsAccountCbx.setEnabled(true);
			}
		};
		
		keyFileCbxAction=new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				if(masterPasswordCbx.isEnabled())
				{
					masterPasswordCbx.setSelected(true);
					masterPasswordCbx.setEnabled(false);
				}
				else
				{
					masterPasswordCbx.setSelected(false);
					masterPasswordCbx.setEnabled(true);
				}
				if(windowsAccountCbx.isEnabled())
				    windowsAccountCbx.setEnabled(false);
				else
					windowsAccountCbx.setEnabled(true);
			}
		
		};
		
		windowsAccountAction=new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				if(keyFileCbx.isEnabled())
				    keyFileCbx.setEnabled(false);
				else
					keyFileCbx.setEnabled(true);
				if(masterPasswordCbx.isEnabled())
					masterPasswordCbx.setEnabled(false);
				else
					masterPasswordCbx.setEnabled(true);
				
			}
		};
		
		cancelBtnAction=new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				closewindow();
			}
		};
		
		okBtnAction=new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				if(masterPasswordCbx.isSelected())
				{
					if(passwordField.getText().equals(repeatField.getText()))
					{
						master.create(PathField.getText(),passwordField.getText());
					}
					
				}
				if(keyFileCbx.isSelected())
				{
						try {
							master.setMkeyfile(new File(keyfileField.getText()));
							master.getmConf().setmLastConnectionStat(2);
						} catch (NoSuchAlgorithmException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
		     	}
				try {
					CreateAll(master);
				} catch (InvalidKeyException | IllegalBlockSizeException | BadPaddingException | SQLException
						| ParserConfigurationException | TransformerException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				closewindow();
			}
		};
	}
	
	private void CreateAll(MasterPassword master) throws SQLException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, ParserConfigurationException, TransformerException, IOException
	{
		master.getmDatabase().create();
		master.getmConf().createFiles(master);
	    master.getmDatabase().pull(master.getmBank());
	}
	
	private void closewindow()
	{
		this.dispose();
	}
	
	
}
