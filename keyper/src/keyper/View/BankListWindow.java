package keyper.View;
import java.awt.EventQueue;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.table.TableColumn;

import java.awt.Toolkit;
import java.util.Set;

import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.JTable;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;

import keyper.Bank;
import keyper.Key;
import keyper.MasterPassword;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.Font;

@SuppressWarnings("serial")
public class BankListWindow extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					MasterPassword master=new MasterPassword();
					BankListWindow frame = new BankListWindow(master);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @param master 
	 */
	@SuppressWarnings("serial")
	public BankListWindow(MasterPassword master) {
		setTitle("Keyper");
		setIconImage(Toolkit.getDefaultToolkit().getImage(BankListWindow.class.getResource("/keyper/View/Icons/secrecy-icon.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 786, 497);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTree tree = new JTree();
		tree.setShowsRootHandles(true);
		tree.setFont(new Font("Myanmar Text", Font.PLAIN, 12));
		
		ImageIcon leafIcon=new ImageIcon(BankListWindow.class.getResource("/keyper/View/Icons/folder-key-icon.png"));
		DefaultTreeCellRenderer renderer = new DefaultTreeCellRenderer();
		if (leafIcon != null)
		{	    
		    renderer.setLeafIcon(leafIcon);
		    tree.setCellRenderer(renderer);
		}
		tree.setModel(new DefaultTreeModel(
			new DefaultMutableTreeNode("Groups") {
				{
					add(new DefaultMutableTreeNode("Internet"));
					add(new DefaultMutableTreeNode("Email"));
					add(new DefaultMutableTreeNode("School"));
				}
			}
		));
		tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		tree.addTreeSelectionListener(new TreeSelectionListener() {
		    public void valueChanged(TreeSelectionEvent e) {
		        DefaultMutableTreeNode node = (DefaultMutableTreeNode)
		                           tree.getLastSelectedPathComponent();
		        if (node == null) return;
		        Object nodeInfo = node.getUserObject();
		        String selected=nodeInfo.toString();
		        Set<Key> keys=master.getmBank().getBank();
		        for(Key key: keys)
		        {
		        	if(selected==key.getmGroup())
		        	addToTable();
		        }
		    }
		});
		tree.setToolTipText("Keys Groups");
		tree.setBounds(10, 62, 167, 355);
		contentPane.add(tree);
		
		table = new JTable();
		table.setFont(new Font("Myanmar Text", Font.PLAIN, 12));
		table.setBounds(187, 63, 573, 355);
		contentPane.add(table);
		TableColumn column = null;
	}

	private void addToTable()
	{
		
	}
}
