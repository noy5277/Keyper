package keyper.View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Panel;
import java.awt.SystemColor;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;

public class HistoryKeyView extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HistoryKeyView frame = new HistoryKeyView();
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
	public HistoryKeyView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
	}
}
