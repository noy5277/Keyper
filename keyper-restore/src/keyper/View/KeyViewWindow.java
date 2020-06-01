package keyper.View;

import java.awt.EventQueue;

import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import keyper.Key;
import keyper.MasterPassword;

import javax.swing.JPanel;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Window.Type;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.Properties;

import javax.swing.JButton;
import java.awt.Panel;
import java.awt.SystemColor;
import java.awt.Font;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Desktop.Action;

import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.UIManager;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JCheckBox;
import javax.swing.SpringLayout;

public class KeyViewWindow extends JFrame{
	private JTextField titleField;
	private JTextField userNameField;
	private JPasswordField passwordField;
	private JPasswordField repeatField;
	private JTextField urlField;
	private JCheckBox expiredcheckbox;
	private Key editkey;
	private SpringLayout springLayout;
    private ActionListener showPasswordAction;
    private ActionListener okAction;
    private Boolean showPasswordStat=false;
    private JDatePickerImpl datePicker;
    private UtilDateModel model;
    private static KeyViewWindow frmEditKey;
    private Date selectedDate;
    
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Key k=new Key();
					frmEditKey = new KeyViewWindow(k);
					frmEditKey.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public KeyViewWindow(Key key) {
		editkey=key;
		selectedDate=new Date();
		model = new UtilDateModel();
		expiredcheckbox = new JCheckBox("Expired:");
		initialize();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		InitActionListeners();
		
		setAlwaysOnTop(true);
		setTitle("Edit key");
		setIconImage(Toolkit.getDefaultToolkit().getImage(KeyViewWindow.class.getResource("/keyper/View/Icons/edit-key.png")));
		setBounds(100, 100, 523, 446);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
		
		JLabel titleLabel = new JLabel("Title: ");
		titleLabel.setFont(new Font("Myanmar Text", Font.PLAIN, 13));
		titleLabel.setBounds(20, 11, 46, 21);
		properties.add(titleLabel);
		
		titleField = new JTextField();
		titleField.setFont(new Font("Tahoma", Font.PLAIN, 11));
		titleField.setBounds(94, 11, 346, 20);
		properties.add(titleField);
		titleField.setColumns(10);
		
		JLabel usernameLabel = new JLabel("UserName:");
		usernameLabel.setFont(new Font("Myanmar Text", Font.PLAIN, 13));
		usernameLabel.setBounds(20, 43, 74, 21);
		properties.add(usernameLabel);
		
		userNameField = new JTextField();
		userNameField.setBounds(94, 42, 346, 20);
		properties.add(userNameField);
		userNameField.setColumns(10);
		
		JLabel passwordLabel = new JLabel("Password:");
		passwordLabel.setFont(new Font("Myanmar Text", Font.PLAIN, 13));
		passwordLabel.setBounds(20, 80, 64, 14);
		properties.add(passwordLabel);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(94, 75, 346, 20);
		properties.add(passwordField);
		
		JLabel repeatLabel = new JLabel("Repeat:");
		repeatLabel.setFont(new Font("Myanmar Text", Font.PLAIN, 13));
		repeatLabel.setBounds(20, 110, 64, 14);
		properties.add(repeatLabel);
		
		repeatField = new JPasswordField();
		repeatField.setBounds(94, 105, 346, 20);
		properties.add(repeatField);
		
		
		expiredcheckbox.setFont(new Font("Segoe UI Light", Font.PLAIN, 14));
		expiredcheckbox.setBounds(6, 187, 78, 23);
		properties.add(expiredcheckbox);
		
		JLabel urlLebal = new JLabel("URL:");
		urlLebal.setFont(new Font("Myanmar Text", Font.PLAIN, 14));
		urlLebal.setBounds(20, 138, 46, 20);
		properties.add(urlLebal);
		
		urlField = new JTextField();
		urlField.setBounds(94, 136, 346, 20);
		properties.add(urlField);
		urlField.setColumns(10);
		
		JButton showpassword = new JButton("");
		showpassword.setToolTipText("Show or Hide password");
		showpassword.setIcon(new ImageIcon(KeyViewWindow.class.getResource("/keyper/View/Icons/Lock-Lock-icon-16.png")));
		showpassword.setBounds(450, 75, 24, 20);
		properties.add(showpassword);
		showpassword.addActionListener(showPasswordAction);
		
		
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
		Okbutton.addActionListener(okAction);
		
		
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
		
		
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
		JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		springLayout=new SpringLayout();
		springLayout.putConstraint(SpringLayout.WEST, datePicker.getJFormattedTextField(), 0, SpringLayout.WEST, datePicker);
		springLayout.putConstraint(SpringLayout.EAST, datePicker.getJFormattedTextField(), 157, SpringLayout.WEST, datePicker);
		springLayout = (SpringLayout) datePicker.getLayout();
		datePicker.getJFormattedTextField().setFont(new Font("Tahoma", Font.PLAIN, 10));
		datePicker.setBackground(Color.WHITE);
		datePicker.getJFormattedTextField().setBackground(Color.WHITE);
		datePicker.getJFormattedTextField().setBounds(0, 0, 271, 23);
		datePicker.setBounds(88, 187, 183, 23);
		properties.add(datePicker);
		
		FillFields();
		
		
	}
	
	
	
	private void InitActionListeners()
	{
		showPasswordAction=new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				if(showPasswordStat==false)
				{
					passwordField.setEchoChar((char)0);
					repeatField.setEchoChar((char)0);
					showPasswordStat=true;
				}
				else
				{
					passwordField.setEchoChar((char)9679);
					repeatField.setEchoChar((char)9679);
					showPasswordStat=false;
     			}
			}
		};
		
		okAction=new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
			    
				editkey.setmUsername(userNameField.getText());
				editkey.setmTitle(titleField.getText());
				String pass=passwordField.getText();
				String rep=repeatField.getText();
				editkey.setmUrl(urlField.getText());
				editkey.setExpiredCheck(expiredcheckbox.isSelected());
				selectedDate =  model.getValue();
				editkey.setmExpired(selectedDate.getYear()+1900, selectedDate.getMonth()+1,selectedDate.getDate());
				if(pass.equals(rep))
				{
					editkey.setmPassword(pass.toString());
					closewindow();
				}
				else
				{
					final JDialog dialog = new JDialog();
					dialog.setAlwaysOnTop(true);   
					JOptionPane.showMessageDialog(dialog,
			     			"The passwords are not equals, "
			     			+"please try again",
						    "Error",
					    JOptionPane.WARNING_MESSAGE);
				}
				
				
				
			}
			
		};
		
	}
	
	private void FillFields()
	{
		titleField.setText(editkey.getmTitle());
		userNameField.setText(editkey.getmUsername());
		passwordField.setText(editkey.getmPassword());
		repeatField.setText(editkey.getmPassword());
		urlField.setText(editkey.getmUrl());
		model.setValue(editkey.getmExpired());
		expiredcheckbox.setSelected(editkey.getExpiredCheck());
	}
	
	private void closewindow()
	{
		this.dispose();
	}
	
	
}
