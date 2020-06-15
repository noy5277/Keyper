package keyper.View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import keyper.Gen;
import keyper.Generator;
import keyper.Key;

import javax.swing.JSpinner;
import javax.swing.JLabel;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.ImageIcon;
import javax.swing.SpinnerNumberModel;

public class GenerateWindow extends JFrame implements Gen {

	private JPanel contentPane;
	private JTextField ResultField;
	private JSpinner numOfCharField;
	private ActionListener generateAction;
	private JCheckBox upperCasechckbx;
	private JCheckBox lowerCasechckbx;
	private JCheckBox specialsChckbx;
	private JCheckBox numChckbx;
	private String password;
	private SpinnerNumberModel numModel;
	private Key editkey;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Key k=new Key();
					GenerateWindow frame = new GenerateWindow(k);
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
	public GenerateWindow(Key key) {
		
		InitActionListeners();
		editkey=key;
		setIconImage(Toolkit.getDefaultToolkit().getImage(GenerateWindow.class.getResource("/keyper/View/Icons/textfield-key-icon.png")));
		setTitle("Generate Password");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		numModel=new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1));
		numOfCharField = new JSpinner();
		numOfCharField.setModel(numModel);
		numOfCharField.setBounds(129, 25, 39, 20);
		contentPane.add(numOfCharField);
		
		JLabel numOfCharsLabel = new JLabel("Namber of charecters:");
		numOfCharsLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		numOfCharsLabel.setBounds(10, 27, 109, 17);
		contentPane.add(numOfCharsLabel);
		
		ResultField = new JTextField();
		ResultField.setEditable(false);
		ResultField.setBounds(60, 180, 264, 20);
		contentPane.add(ResultField);
		ResultField.setColumns(10);
		
		JLabel resultLabel = new JLabel("Result:");
		resultLabel.setFont(new Font("Myanmar Text", Font.PLAIN, 11));
		resultLabel.setBounds(10, 185, 50, 17);
		contentPane.add(resultLabel);
		
		JButton okButton = new JButton("OK");
		okButton.setBounds(244, 227, 89, 23);
		contentPane.add(okButton);
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.setBounds(335, 227, 89, 23);
		contentPane.add(cancelButton);
		
		upperCasechckbx = new JCheckBox("Upper-case {A,B,C,....,Y,Z}");
		upperCasechckbx.setBounds(10, 51, 163, 23);
		contentPane.add(upperCasechckbx);
		
		lowerCasechckbx = new JCheckBox("Lower-case {a,b,c,....,y,z}");
		lowerCasechckbx.setBounds(10, 103, 157, 23);
		contentPane.add(lowerCasechckbx);
		
		numChckbx = new JCheckBox("Numbers {1,2,3,4,.....,8,9}");
		numChckbx.setBounds(10, 77, 170, 23);
		contentPane.add(numChckbx);
		
		specialsChckbx = new JCheckBox("Specials {!,@.#.$.....,*,&}");
		specialsChckbx.setBounds(10, 129, 157, 23);
		contentPane.add(specialsChckbx);
		
		JButton generateButton = new JButton("Generate");
		generateButton.setBounds(334, 179, 90, 23);
		contentPane.add(generateButton);
		generateButton.addActionListener(generateAction);
	}
	
	private void InitActionListeners()
	{
		generateAction=new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{ 
				password=" ";
				System.out.println(password);
				password=Gen.generate((int)numOfCharField.getModel().getValue(),upperCasechckbx.isSelected(), lowerCasechckbx.isSelected(),numChckbx.isSelected(),specialsChckbx.isSelected());
				ResultField.setText(password);
				System.out.println(password);
			}
		};
		
		
		
		
	}
}
