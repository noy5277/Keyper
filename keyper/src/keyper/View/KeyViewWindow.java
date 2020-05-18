package keyper.View;

import java.awt.EventQueue;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import java.awt.Toolkit;
import javax.swing.JLabel;
import java.awt.Window.Type;
import javax.swing.JButton;
import java.awt.Panel;
import java.awt.SystemColor;
import java.awt.Font;

public class KeyViewWindow {

	private JFrame frmEditKey;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					KeyViewWindow window = new KeyViewWindow();
					window.frmEditKey.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public KeyViewWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmEditKey = new JFrame();
		frmEditKey.setType(Type.POPUP);
		frmEditKey.setTitle("Edit key");
		frmEditKey.setIconImage(Toolkit.getDefaultToolkit().getImage(KeyViewWindow.class.getResource("/keyper/View/Icons/secrecy-icon.png")));
		frmEditKey.setBounds(100, 100, 523, 446);
		frmEditKey.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmEditKey.getContentPane().setLayout(null);
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 44, 507, 319);
		frmEditKey.getContentPane().add(tabbedPane);
		
		JPanel properties = new JPanel();
		tabbedPane.addTab("Properties", null, properties, null);
		properties.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setBounds(10, 46, 46, 14);
		properties.add(lblNewLabel_1);
		
		JPanel history = new JPanel();
		tabbedPane.addTab("History", null, history, null);
		history.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(10, 36, 46, 14);
		history.add(lblNewLabel);
		
		JButton Okbutton = new JButton("OK");
		Okbutton.setFont(new Font("Tahoma", Font.PLAIN, 11));
		Okbutton.setBounds(314, 374, 89, 23);
		frmEditKey.getContentPane().add(Okbutton);
		
		JButton cancelbutton = new JButton("Cancel");
		cancelbutton.setBounds(413, 374, 89, 23);
		frmEditKey.getContentPane().add(cancelbutton);
		
		Panel panel = new Panel();
		panel.setForeground(SystemColor.text);
		panel.setBackground(SystemColor.activeCaption);
		panel.setBounds(0, 0, 507, 44);
		frmEditKey.getContentPane().add(panel);
		
		JLabel lblNewLabel_2 = new JLabel("Edit Key");
		lblNewLabel_2.setFont(new Font("Myanmar Text", Font.BOLD, 18));
		panel.add(lblNewLabel_2);
		
		
	}
}
