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

public class KeyViewWindow extends JFrame{

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
		tabbedPane.addTab("Properties", null, properties, null);
		tabbedPane.setEnabledAt(0, true);
		properties.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setBounds(10, 46, 72, 23);
		properties.add(lblNewLabel_1);
		
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
		lblNewLabel_2.setFont(new Font("Myanmar Text", Font.BOLD, 18));
		panel.add(lblNewLabel_2);
		
		
	}
}
