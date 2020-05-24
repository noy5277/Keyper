package keyper.View;

import java.awt.EventQueue;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import keyper.MasterPassword;

import javax.swing.JPanel;
import java.awt.Toolkit;
import javax.swing.JLabel;
import java.awt.Window.Type;
import javax.swing.JButton;
import java.awt.Panel;
import java.awt.SystemColor;
import java.awt.Font;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JCheckBox;

public class KeyViewWindow extends JFrame{
	private JTextField titleField;
	private JTextField userNameField;
	private JPasswordField passwordField;
	private JPasswordField repeatfield;
	private JTextField textField;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MasterPassword master=new MasterPassword();
					KeyViewWindow frmEditKey = new KeyViewWindow(master);
					frmEditKey.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public KeyViewWindow(MasterPassword master) {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		setAlwaysOnTop(true);
		setTitle("Edit key");
		setIconImage(Toolkit.getDefaultToolkit().getImage(KeyViewWindow.class.getResource("/keyper/View/Icons/edit-key.png")));
		setBounds(100, 100, 523, 446);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setFont(new Font("Tahoma", Font.PLAIN, 11));
		tabbedPane.setBackground(new Color(160, 160, 160));
		tabbedPane.setBounds(0, 44, 507, 319);
		getContentPane().add(tabbedPane);
		
		JPanel properties = new JPanel();
		properties.setToolTipText("Generate password");
		tabbedPane.addTab("Properties", null, properties, null);
		tabbedPane.setEnabledAt(0, true);
		properties.setLayout(null);
		
		JLabel titlelabel = new JLabel("Title: ");
		titlelabel.setFont(new Font("Myanmar Text", Font.PLAIN, 13));
		titlelabel.setBounds(20, 11, 46, 21);
		properties.add(titlelabel);
		
		titleField = new JTextField();
		titleField.setFont(new Font("Myanmar Text", Font.PLAIN, 13));
		titleField.setBounds(94, 11, 346, 20);
		properties.add(titleField);
		titleField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("UserName:");
		lblNewLabel_1.setFont(new Font("Myanmar Text", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(20, 43, 74, 21);
		properties.add(lblNewLabel_1);
		
		userNameField = new JTextField();
		userNameField.setBounds(94, 42, 346, 20);
		properties.add(userNameField);
		userNameField.setColumns(10);
		
		JLabel passwordlabel = new JLabel("Password:");
		passwordlabel.setFont(new Font("Myanmar Text", Font.PLAIN, 13));
		passwordlabel.setBounds(20, 80, 64, 14);
		properties.add(passwordlabel);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(94, 75, 346, 20);
		properties.add(passwordField);
		
		JLabel repeatlabel = new JLabel("Repeat:");
		repeatlabel.setFont(new Font("Myanmar Text", Font.PLAIN, 13));
		repeatlabel.setBounds(20, 110, 64, 14);
		properties.add(repeatlabel);
		
		repeatfield = new JPasswordField();
		repeatfield.setBounds(94, 105, 346, 20);
		properties.add(repeatfield);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("Expired:");
		chckbxNewCheckBox.setFont(new Font("Segoe UI Light", Font.PLAIN, 14));
		chckbxNewCheckBox.setBounds(20, 247, 97, 23);
		properties.add(chckbxNewCheckBox);
		
		JLabel lblNewLabel_3 = new JLabel("URL:");
		lblNewLabel_3.setFont(new Font("Myanmar Text", Font.PLAIN, 14));
		lblNewLabel_3.setBounds(20, 138, 46, 20);
		properties.add(lblNewLabel_3);
		
		textField = new JTextField();
		textField.setBounds(94, 136, 346, 20);
		properties.add(textField);
		textField.setColumns(10);
		
		JButton showpassword = new JButton("");
		showpassword.setToolTipText("Show or Hide password");
		showpassword.setIcon(new ImageIcon(KeyViewWindow.class.getResource("/keyper/View/Icons/Lock-Lock-icon-16.png")));
		showpassword.setBounds(450, 75, 24, 20);
		properties.add(showpassword);
		
		JButton ganaratebutton = new JButton("");
		ganaratebutton.setToolTipText("Generate password");
		ganaratebutton.setIcon(new ImageIcon(KeyViewWindow.class.getResource("/keyper/View/Icons/textfield-key-icon.png")));
		ganaratebutton.setBounds(450, 104, 24, 23);
		properties.add(ganaratebutton);
		
		JPanel history = new JPanel();
		tabbedPane.addTab("History", null, history, null);
		tabbedPane.setEnabledAt(1, true);
		history.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(10, 36, 46, 14);
		history.add(lblNewLabel);
		
		JButton Okbutton = new JButton("OK");
		Okbutton.setFont(new Font("Tahoma", Font.PLAIN, 11));
		Okbutton.setBounds(314, 374, 89, 23);
		getContentPane().add(Okbutton);
		
		JButton cancelbutton = new JButton("Cancel");
		cancelbutton.setBounds(413, 374, 89, 23);
		getContentPane().add(cancelbutton);
		
		Panel panel = new Panel();
		panel.setForeground(SystemColor.text);
		panel.setBackground(SystemColor.activeCaption);
		panel.setBounds(0, 0, 507, 44);
		getContentPane().add(panel);
		
		JLabel lblNewLabel_2 = new JLabel("Edit Key");
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setFont(new Font("Levenim MT", Font.BOLD, 18));
		panel.add(lblNewLabel_2);
		
		
	}
}
