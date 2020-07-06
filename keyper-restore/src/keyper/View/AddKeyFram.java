package keyper.View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import keyper.Key;
import keyper.MasterPassword;

import java.awt.SystemColor;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.Properties;

import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.JSpinner;

public class AddKeyFram extends JFrame {

	private JPanel contentPane;
	private JTextField groupField;
	private JTextField titleField;
	private JTextField usernameField;
	private JPasswordField passwordField;
	private JTextField urlField;
	private Key editkey;
	private MasterPassword editmaster;
	private ActionListener okBtnAction;
	private ActionListener generateBtnAction;
	private UtilDateModel model;
	private Date selectedDate;
	private SpringLayout springLayout;
	private JCheckBox expiredCheckBox;
	private JDatePickerImpl datePicker;
	private ActionListener cancelBtnAction;
	private JPasswordField repeatField;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Key k=new Key();
					MasterPassword master=new MasterPassword();
					AddKeyFram frame = new AddKeyFram(master, k);
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
	public AddKeyFram(MasterPassword master,Key k) {
		InitListeners();
		
		editmaster=master;
		editkey=k;
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(AddKeyFram.class.getResource("/keyper/View/Icons/addkey.png")));
		setTitle("Add Key");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 523, 446);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.activeCaption);
		panel.setBounds(0, 0, 507, 43);
		contentPane.add(panel);
		
		JLabel addKeyLabel = new JLabel("Add Key");
		addKeyLabel.setIcon(null);
		addKeyLabel.setForeground(SystemColor.text);
		addKeyLabel.setFont(new Font("Levenim MT", Font.BOLD, 18));
		panel.add(addKeyLabel);
		
		JLabel groupLabel = new JLabel("Group:");
		groupLabel.setFont(new Font("Myanmar Text", Font.PLAIN, 12));
		groupLabel.setBounds(33, 75, 46, 20);
		contentPane.add(groupLabel);
		
		groupField = new JTextField();
		groupField.setBounds(107, 73, 367, 20);
		contentPane.add(groupField);
		groupField.setColumns(10);
		
		JLabel TitleLabel = new JLabel("Title:");
		TitleLabel.setFont(new Font("Myanmar Text", Font.PLAIN, 12));
		TitleLabel.setBounds(33, 118, 59, 20);
		contentPane.add(TitleLabel);
		
		titleField = new JTextField();
		titleField.setBounds(107, 116, 367, 20);
		contentPane.add(titleField);
		titleField.setColumns(10);
		
		JLabel usernameLabel = new JLabel("Username:");
		usernameLabel.setFont(new Font("Myanmar Text", Font.PLAIN, 12));
		usernameLabel.setBounds(33, 163, 59, 20);
		contentPane.add(usernameLabel);
		
		usernameField = new JTextField();
		usernameField.setBounds(107, 161, 367, 20);
		contentPane.add(usernameField);
		usernameField.setColumns(10);
		
		JLabel passwordLabel = new JLabel("Password:");
		passwordLabel.setFont(new Font("Myanmar Text", Font.PLAIN, 12));
		passwordLabel.setBounds(33, 210, 59, 19);
		contentPane.add(passwordLabel);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(107, 207, 367, 20);
		contentPane.add(passwordField);
		passwordField.setColumns(10);
		
		JLabel urlLabel = new JLabel("URL:");
		urlLabel.setFont(new Font("Myanmar Text", Font.PLAIN, 12));
		urlLabel.setBounds(33, 294, 46, 20);
		contentPane.add(urlLabel);
		
		urlField = new JTextField();
		urlField.setBounds(107, 292, 367, 20);
		contentPane.add(urlField);
		urlField.setColumns(10);
		
		JLabel expiredLabel = new JLabel("Expired Date:");
		expiredLabel.setFont(new Font("Myanmar Text", Font.PLAIN, 12));
		expiredLabel.setBounds(33, 333, 74, 21);
		contentPane.add(expiredLabel);
		
		expiredCheckBox = new JCheckBox("");
		expiredCheckBox.setBounds(6, 330, 21, 24);
		contentPane.add(expiredCheckBox);
		
		JButton okBtn = new JButton("OK");
		okBtn.setBounds(315, 373, 89, 23);
		contentPane.add(okBtn);
		okBtn.addActionListener(okBtnAction);
		
		JButton CancelBtn = new JButton("Cancel");
		CancelBtn.setBounds(408, 373, 89, 23);
		contentPane.add(CancelBtn);
		CancelBtn.addActionListener(cancelBtnAction);
		
		Date date=new Date();
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		model = new UtilDateModel();
		model.setDate(date.getYear()+1900,date.getMonth(),date.getDate());
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
		springLayout=new SpringLayout();
		datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		contentPane.add(datePicker);
		springLayout.putConstraint(SpringLayout.WEST, datePicker.getJFormattedTextField(), 0, SpringLayout.WEST, datePicker);
		springLayout.putConstraint(SpringLayout.EAST, datePicker.getJFormattedTextField(), 157, SpringLayout.WEST, datePicker);
		springLayout = (SpringLayout) datePicker.getLayout();
		datePicker.getJFormattedTextField().setFont(new Font("Tahoma", Font.PLAIN, 10));
		datePicker.setBackground(Color.WHITE);
		datePicker.getJFormattedTextField().setBackground(Color.WHITE);
		datePicker.getJFormattedTextField().setBounds(0, 0, 271, 23);
		datePicker.setBounds(107, 333, 151, 21);
		getContentPane().add(datePicker);
		
		JButton GenerateButton = new JButton("");
		GenerateButton.setToolTipText("Generate Password");
		GenerateButton.setIcon(new ImageIcon(AddKeyFram.class.getResource("/keyper/View/Icons/textfield-key-icon.png")));
		GenerateButton.setBounds(476, 206, 21, 23);
		contentPane.add(GenerateButton);
		
		JLabel RepeatLabel = new JLabel("Repeat:");
		RepeatLabel.setFont(new Font("Myanmar Text", Font.PLAIN, 12));
		RepeatLabel.setBounds(33, 245, 46, 24);
		contentPane.add(RepeatLabel);
		
		repeatField = new JPasswordField();
		repeatField.setBounds(107, 249, 367, 20);
		contentPane.add(repeatField);
		GenerateButton.addActionListener(generateBtnAction);
		model.setSelected(true);
	}
	
	private void InitListeners()
	{
		okBtnAction=new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				selectedDate = (Date) datePicker.getModel().getValue();
				editkey.setmTitle(titleField.getText());
				editkey.setmGroup(groupField.getText());
				editkey.setmUsername(usernameField.getText());
				editkey.setmPassword(passwordField.getText());
				editkey.setmUrl(urlField.getText());
				editkey.setExpiredCheck(expiredCheckBox.isSelected());
				if(expiredCheckBox.isSelected())
				{
					editkey.setmExpired(selectedDate.getYear()+1900, selectedDate.getMonth()+1, selectedDate.getDate());
				}
				editmaster.getmBank().addkey(editkey);
				editkey.notifyChanges();
				closeWindow();
			}
			
		};
		
		cancelBtnAction=new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				closeWindow();
			}
		};
		
		generateBtnAction=new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				GenerateWindow win=new GenerateWindow(passwordField,repeatField);
				win.setVisible(true);
				win.setAlwaysOnTop(true);
				
			}
		};
	}
	
	private void closeWindow()
	{
		this.dispose();
	}
}
