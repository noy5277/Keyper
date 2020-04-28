package keyper.View;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import java.awt.Toolkit;
import java.util.HashSet;
import java.util.Set;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.JTable;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;
import keyper.Key;
import keyper.MasterPassword;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.Font;
import java.awt.List;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

@SuppressWarnings("serial")
public class BankListWindow extends JFrame {

	private JPanel contentPane;
    private MasterPassword master;
    private JTable table;
    private final String[] columnNames = {"Title", "UserName","Password", "URL"};
    private Object data[]=new Object[4];
    private Object [][] empty= {{"","","",""}};
    DefaultTableModel model = new DefaultTableModel(empty,columnNames);
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
	@SuppressWarnings({ "serial", "unused" })
	public BankListWindow(MasterPassword master) {
		this.master=master;
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
		        
		     //   if(selected=="Groups")
		       // {
		       // 	selectAll();
		       // }
		        //else
		        //{
		        	addColByGroup(selected);
		       // }
		    }
		});
		tree.setToolTipText("Keys Groups");
		tree.setBounds(10, 62, 167, 355);
		contentPane.add(tree);

		table = new JTable();
		table.setBounds(185, 61, 575, 355);
		contentPane.add(table);
		
		 JScrollPane scrollPane = new JScrollPane(table);
		 scrollPane.setBounds(187, 62, 573, 355);
		 getContentPane().add(scrollPane);
	}

	private void addColByGroup(String selected)
	{
	  Set<Key> keys=master.getmBank().getBank();
	  Set<Key>choosen=new HashSet();
     for(Key key: keys)
	 { 
        if(selected.equals(key.getmGroup()))
        {
        	choosen.add(key);
        }
        else
        {
        	System.out.println("there is no such group");
        }
     }
     populatetable(table,choosen);
	}
	
	public void selectAll()
	{
		Set<Key> keys=master.getmBank().getBank();
		 populatetable(table,keys);
	}
	
	public void removeRowSelection(JTable table)
	{
		table.setModel(model);
	}
	
	public  void populatetable(JTable table, Set<Key> keys) {
	    removeRowSelection(table);
	    DefaultTableModel tablemodel = (DefaultTableModel) table.getModel();
	    tablemodel.setRowCount(0);
	     for(Key key: keys) 
	     {
	    	 data[0]=key.getmTitle();
	    	 data[1]=key.getmUsername();
	    	 data[2]=key.getmPassword();
	    	 data[3]=key.getmUrl();
	    	 System.out.println(key.getmTitle());
	    	 tablemodel.addRow(data);
	     }
	     table.setModel(tablemodel);
	}
}
