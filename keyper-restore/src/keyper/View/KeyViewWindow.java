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
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

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
import javax.swing.JScrollPane;
import javax.swing.JCheckBox;
import javax.swing.SpringLayout;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.CompoundBorder;
import javax.swing.ListSelectionModel;
import javax.swing.border.MatteBorder;

public class KeyViewWindow extends JFrame{
	private JTextField titleField;
	private JTextField userNameField;
	private JPasswordField passwordField;
	private JPasswordField repeatField;
	private JTextField urlField;
	private JCheckBox expiredcheckbox;
	private Key editkey;
	private SpringLayout springLayout;
	private Map<Integer ,Date> keyMap;
	private ActionListener viewHistoryKeyAction;
	private ActionListener deleteHistoryKeyAction;
    private ActionListener showPasswordAction;
    private ActionListener okAction;
    private ActionListener generateAction;
    private ActionListener cancelAction;
    private ActionListener restoreAction;
    private Boolean showPasswordStat=false;
    private JDatePickerImpl datePicker;
    private UtilDateModel model;
    private static KeyViewWindow frmEditKey;
    private Date selectedDate;
    private JTable table;
    private Object data[]=new Object[3];
    private Object [][] empty= {{"","",""}};
    private final String[] columnNames = {"Version", "Title","Username"};
    private boolean restore=false;
    DefaultTableModel emptyTable = new DefaultTableModel(empty,columnNames);
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
		
		keyMap=new HashMap<>();
		
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
		
		
		JButton generatebutton = new JButton("");
		generatebutton.setToolTipText("Generate password");
		generatebutton.setIcon(new ImageIcon(KeyViewWindow.class.getResource("/keyper/View/Icons/textfield-key-icon.png")));
		generatebutton.setBounds(450, 104, 24, 23);
		properties.add(generatebutton);
		generatebutton.addActionListener(generateAction);
		
		
		JPanel history = new JPanel();
		history.setBorder(new CompoundBorder());
		tabbedPane.addTab("History", null, history, null);
		tabbedPane.setEnabledAt(1, true);
		history.setLayout(null);
		
		
		JButton restoreBtn = new JButton("Restore");
		restoreBtn.setBounds(403, 257, 89, 23);
		history.add(restoreBtn);
		restoreBtn.addActionListener(restoreAction);
		
		JButton Viewbtn = new JButton("View");
		Viewbtn.setBounds(10, 257, 89, 23);
		history.add(Viewbtn);
		Viewbtn.addActionListener(viewHistoryKeyAction);
		
		JButton deletebtn = new JButton("Delete");
		deletebtn.setBounds(109, 257, 89, 23);
		history.add(deletebtn);
		deletebtn.addActionListener(deleteHistoryKeyAction);
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setEnabled(false);
		scrollPane.setBounds(10, 11, 482, 235);
		history.add(scrollPane);
		
		
		table = new JTable(){
			   @Override
			   public boolean isCellEditable(int row, int column) {
			    return false;
			   }
		};
		table.setFillsViewportHeight(true);
		scrollPane.setViewportView(table);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setSurrendersFocusOnKeystroke(true);
		table.setRowHeight(20);
		InitTable();
		fillHistoryTable();
		
		JButton Okbutton = new JButton("OK");
		Okbutton.setFont(new Font("Tahoma", Font.PLAIN, 11));
		Okbutton.setBounds(314, 374, 89, 23);
		getContentPane().add(Okbutton);
		Okbutton.addActionListener(okAction);
		
		
		JButton cancelbutton = new JButton("Cancel");
		cancelbutton.setBounds(413, 374, 89, 23);
		getContentPane().add(cancelbutton);
		cancelbutton.addActionListener(cancelAction);
		
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
	
	private void InitTable()
	{
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Version", "Title", "Username"
			}
		));
	}
	

	private void fillHistoryTable()
	{
		int i=0;
		InitTable();
		DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
		for(Date date:editkey.gethistorydates())
		{
			keyMap.put(i,date);
			data[0]=date;
			data[1]=editkey.getmHistory().get(date).getmTitle();
			data[2]=editkey.getmHistory().get(date).getmUsername();
			tableModel.addRow(data);
			i++;
		}	
			table.setModel(tableModel);
			table.setRowHeight(20);
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
				boolean changed=false;
				if(!(restore))
				{
					if(!(editkey.getmUsername().equals(userNameField.getText())))
				    {
				    	editkey.setmUsername(userNameField.getText());
				    	changed=true;
				    }
					
					
					if(!(editkey.getmPassword().equals(passwordField.getText())))
					{
						String pass=passwordField.getText();
						String rep=repeatField.getText();
						if(pass.equals(rep))
						{
							editkey.setmPassword(pass.toString());
							
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
						changed=true;
					}
					
					editkey.setmTitle(titleField.getText());
					editkey.setmUrl(urlField.getText());
					editkey.setExpiredCheck(expiredcheckbox.isSelected());
					selectedDate =  model.getValue();
					editkey.setmExpired(selectedDate.getYear()+1900, selectedDate.getMonth()+1,selectedDate.getDate());
					
					if(changed)
					{
						System.out.println("!!!");
						editkey.getmHistory().put(new Date(),editkey);
					}
					changed=false;
				}
					restore=false;
					closewindow();
			}
			
		};
		
		generateAction=new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				GenerateWindow generate=new GenerateWindow(passwordField, repeatField);
				generate.setVisible(true);
				generate.setAlwaysOnTop(true);
				
			}
		};
		
		cancelAction=new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				closewindow();
			}
		};
		
		restoreAction= new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				int selectedRow = table.getSelectedRow();
				editkey.Restore(keyMap.get(selectedRow));
				keyMap.clear();
				restore=true;
			}
		};
		
		deleteHistoryKeyAction=new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				int selectedRow = table.getSelectedRow();
				editkey.getmHistory().remove(keyMap.get(selectedRow));
				keyMap.remove(selectedRow);
				fillHistoryTable();
			}
		};
		
		viewHistoryKeyAction=new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				int selectedRow = table.getSelectedRow();
				HistoryKeyView view=new HistoryKeyView(editkey.getmHistory().get(keyMap.get(selectedRow)),keyMap.get(selectedRow));
				System.out.println(editkey.getmHistory().get(keyMap.get(selectedRow)).getmTitle());
				view.setVisible(true);
				view.setAlwaysOnTop(true);
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
