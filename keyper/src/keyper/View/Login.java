package keyper.View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import keyper.Configuration;
import keyper.Key;
import keyper.MasterPassword;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JPasswordField;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JButton;
import javax.swing.ImageIcon;

public class Login extends JFrame {

	private JPanel contentPane;
	private JPasswordField passwordField;
	private JTextField pathview;
	private JLabel keyfileLabel;
	private JTextField keyfileField;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Key key=new Key("gmail","email", "noy5277@gmail.com", "P@ssw0rd", "gmail.com");
				    MasterPassword master=new MasterPassword();
				    Configuration conf=new Configuration(master);
				    master.getmConf().importFiles();
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
	public Login(MasterPassword master)  {
		setIconImage(Toolkit.getDefaultToolkit().getImage("\\\\10.0.0.138\\hd - \u05E4\u05E8\u05D5\u05D9\u05D9\u05E7\u05D8\\\u05D0\u05D9\u05D9\u05E7\u05D5\u05E0\u05D9\u05DD\\png\\secrecy-icon.png"));
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
		panel.setBackground(new Color(173, 216, 230));
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
		keyfilebutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		keyfilebutton.setIcon(new ImageIcon("\\\\10.0.0.138\\hd - \u05E4\u05E8\u05D5\u05D9\u05D9\u05E7\u05D8\\\u05D0\u05D9\u05D9\u05E7\u05D5\u05E0\u05D9\u05DD\\png\\folder-blue-icon (1).png"));
		keyfilebutton.setBounds(369, 86, 22, 20);
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
		
		JButton OKButton = new JButton("OK");
		OKButton.setBounds(231, 159, 89, 23);
		contentPane.add(OKButton);
		
	}

	
}
