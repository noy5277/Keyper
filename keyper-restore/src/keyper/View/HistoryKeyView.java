package keyper.View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;

import keyper.Key;

import java.awt.Panel;
import java.awt.SystemColor;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Toolkit;
import java.util.Date;

import javax.swing.JTextField;
import javax.swing.JCheckBox;

public class HistoryKeyView extends JFrame {

	private JPanel contentPane;
	private JTextField titleField;
	private JTextField usernameField;
	private JTextField passwordField;
	private JTextField urlField;
	private JTextField expiredField;
	private JTextField creationField;
	private Key editkey;
	private Date editDate;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Key k=new Key();
					Date d=new Date();
					HistoryKeyView frame = new HistoryKeyView(k,d);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public HistoryKeyView(Key key, Date date) {
		editkey=key;
		editDate=date;
		setTitle("Key View");
		setIconImage(Toolkit.getDefaultToolkit().getImage(HistoryKeyView.class.getResource("/keyper/View/Icons/history.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 523, 446);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Panel panel = new Panel();
		panel.setForeground(Color.WHITE);
		panel.setBackground(SystemColor.activeCaption);
		panel.setBounds(0, 0, 507, 44);
		contentPane.add(panel);
		
		JLabel lblViewKey = new JLabel("View Key");
		lblViewKey.setForeground(Color.WHITE);
		lblViewKey.setFont(new Font("Levenim MT", Font.BOLD, 18));
		panel.add(lblViewKey);
		
		JLabel titleLabel = new JLabel("Title:");
		titleLabel.setFont(new Font("Myanmar Text", Font.PLAIN, 14));
		titleLabel.setBounds(10, 124, 31, 17);
		contentPane.add(titleLabel);
		
		titleField = new JTextField();
		titleField.setEditable(false);
		titleField.setBounds(107, 120, 349, 20);
		contentPane.add(titleField);
		titleField.setColumns(10);
		
		JLabel usernameLabel = new JLabel("Username:");
		usernameLabel.setFont(new Font("Myanmar Text", Font.PLAIN, 14));
		usernameLabel.setBounds(10, 165, 65, 27);
		contentPane.add(usernameLabel);
		
		usernameField = new JTextField();
		usernameField.setEditable(false);
		usernameField.setBounds(107, 166, 349, 20);
		contentPane.add(usernameField);
		usernameField.setColumns(10);
		
		JLabel passwordLabel = new JLabel("Password:");
		passwordLabel.setFont(new Font("Myanmar Text", Font.PLAIN, 14));
		passwordLabel.setBounds(12, 210, 74, 27);
		contentPane.add(passwordLabel);
		
		passwordField = new JTextField();
		passwordField.setEditable(false);
		passwordField.setBounds(107, 211, 349, 20);
		contentPane.add(passwordField);
		passwordField.setColumns(10);
		
		JLabel urlLabel = new JLabel("URL:");
		urlLabel.setFont(new Font("Myanmar Text", Font.PLAIN, 14));
		urlLabel.setBounds(12, 256, 46, 22);
		contentPane.add(urlLabel);
		
		urlField = new JTextField();
		urlField.setEditable(false);
		urlField.setBounds(107, 255, 347, 20);
		contentPane.add(urlField);
		urlField.setColumns(10);
		
		JLabel expiredLabel = new JLabel("Expired Date:");
		expiredLabel.setFont(new Font("Myanmar Text", Font.PLAIN, 14));
		expiredLabel.setBounds(12, 306, 80, 20);
		contentPane.add(expiredLabel);
		
		expiredField = new JTextField();
		expiredField.setEditable(false);
		expiredField.setBounds(107, 304, 349, 20);
		contentPane.add(expiredField);
		expiredField.setColumns(10);
		
		JLabel creationLabel = new JLabel("Creation Date:");
		creationLabel.setFont(new Font("Myanmar Text", Font.PLAIN, 14));
		creationLabel.setBounds(12, 63, 88, 20);
		contentPane.add(creationLabel);
		
		creationField = new JTextField();
		creationField.setEditable(false);
		creationField.setBounds(107, 61, 342, 20);
		contentPane.add(creationField);
		creationField.setColumns(10);
		FillFields();
	}
	
	void FillFields()
	{
		creationField.setText(editDate.toString());
		titleField.setText(editkey.getmTitle());
		usernameField.setText(editkey.getmUsername());
		passwordField.setText(editkey.getmPassword());
		urlField.setText(editkey.getmUrl());
		if(editkey.ExpiredStatus())
		{
			expiredField.setText(editkey.getmExpired().toString());
		}
		else
		{
			expiredField.setText("");
		}
		
	}
}
