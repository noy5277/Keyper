package keyper.View;
import java.awt.Component;
import java.awt.EventQueue;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.plaf.ProgressBarUI;

import java.awt.Toolkit;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

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
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JProgressBar;
import java.awt.Color;
import javax.swing.JTextField;
import java.awt.SystemColor;

@SuppressWarnings("serial")
public class BankListWindow extends JFrame implements ActionListener {

	private JPanel contentPane;
    private MasterPassword master;
    private Map<Integer ,Key> keyMap;
    private JTable table;
    private final String[] columnNames = {"","Title", "UserName","Password", "URL"};
    private Object data[]=new Object[5];
    private Object [][] empty= {{"","","","",""}};
    private JProgressBar progressBar;
    private JPopupMenu popupMenu;
    JMenuItem menuItemView = new JMenuItem("View");
	JMenuItem menuItemDelete = new JMenuItem("Delete");
	JMenuItem menuItemEdit = new JMenuItem("Edit");
	JMenuItem menuItemCopyUsername = new JMenuItem("Copy Username");
	JMenuItem menuItemCopyPassword = new JMenuItem("Copy Password");
    
    DefaultTableModel emptyTable = new DefaultTableModel(empty,columnNames);
    private JTextField keytextField;
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
	@SuppressWarnings({ "unused" })
	
	public BankListWindow()
	{
		
	}
	public BankListWindow(MasterPassword master) {
		this.master=master;
		this.keyMap=new HashMap <Integer ,Key>();
		this.progressBar=new JProgressBar(0,master.getmConf().getmClipBoardSleepTime());
		this.popupMenu=new JPopupMenu();
		setTitle("Keyper");
		setIconImage(Toolkit.getDefaultToolkit().getImage(BankListWindow.class.getResource("/keyper/View/Icons/secrecy-icon.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 786, 511);
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
					add(new DefaultMutableTreeNode("Email"));
					add(new DefaultMutableTreeNode("Intenet"));
					add(new DefaultMutableTreeNode("Games"));
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
                
		       	addColByGroup(selected);
		        
		    }
		});
		tree.setToolTipText("Keys Groups");
		tree.setBounds(10, 62, 167, 355);
		contentPane.add(tree);

		table = new JTable(){
			   @Override
			   public boolean isCellEditable(int row, int column) {
			    return false;
			   }
		};
		table.setShowVerticalLines(false);
		table.setSurrendersFocusOnKeystroke(true);
		table.setFillsViewportHeight(true);
		table.setBounds(185, 61, 575, 355);
		contentPane.add(table);
		
		 JScrollPane scrollPane = new JScrollPane(table);
		 scrollPane.setBounds(187, 62, 573, 355);
		 getContentPane().add(scrollPane);
		
		 menuItemView.addActionListener(this);
		 menuItemDelete.addActionListener(this);
		 menuItemEdit.addActionListener(this);
		 menuItemCopyUsername.addActionListener(this);
		 menuItemCopyPassword.addActionListener(this);
		 
		 popupMenu.add(menuItemView);
		 popupMenu.add(menuItemDelete);
		 popupMenu.add(menuItemEdit);
		 popupMenu.add(menuItemCopyUsername);
		 popupMenu.add(menuItemCopyPassword);
		 
		 progressBar.setForeground(new Color(46, 139, 87));
		 progressBar.setBounds(596, 428, 152, 19);
		 contentPane.add(progressBar);
		 
		 keytextField = new JTextField();
		 keytextField.setEditable(false);
		 keytextField.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 10));
		 keytextField.setBackground(SystemColor.menu);
		 keytextField.setBounds(10, 428, 580, 33);
		 contentPane.add(keytextField);
		 keytextField.setColumns(10);
		 
		 table.addMouseListener(new TableMouseListener(table,keytextField,keyMap));
		 table.setComponentPopupMenu(popupMenu);
		 
	}
	

	
	private void addColByGroup(String selected)
	{
	  Set<Key> keys=master.getmBank().getBank();
	  Set<Key>choosen=new HashSet<Key>();
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
		table.setModel(emptyTable);
	}
	
	public  void populatetable(JTable table, Set<Key> keys)
	{
		int i=0;
		Renderer cell= new Renderer( );
	    removeRowSelection(table);
	    DefaultTableModel tablemodel = (DefaultTableModel) table.getModel();
	    TableColumnModel columnModel = table.getColumnModel();
	    columnModel.getColumn(0).setPreferredWidth(15);
	    tablemodel.setRowCount(0);
	     for(Key key: keys) 
	     {
	    	 keyMap.put(i, key);
	    	 System.out.println(key.getmExpired());
	    	 data[1]=key.getmTitle();
	    	 data[2]=key.getmUsername();
	    	 data[3]="**********";
	    	 data[4]=key.getmUrl();
	    	 System.out.println(key.getmTitle());
	    	 tablemodel.addRow(data);
	    	 if(key.ExpiredStatus())
	    	 {
	    		 cell.setKeyIcon(1);
	    	 } 
	    	 cell.getTableCellRendererComponent(table,data[0], true, true, i, 0); 
	    	 i++;
	    	 table.getColumnModel().getColumn(0).setCellRenderer(cell);
	    	 tablemodel=(DefaultTableModel) table.getModel();
	     }
	     table.setModel(tablemodel);
	     table.setRowHeight(20);
}


	class Renderer extends DefaultTableCellRenderer
    {  
	
      private int keyIcon;
      Renderer()
      {
     	this.keyIcon=0;
      }
	  JLabel lbl=new JLabel();
	  ImageIcon[] icons= {new ImageIcon(Login.class.getResource("/keyper/View/Icons/key-go-icon.jpg"))  , new ImageIcon(Login.class.getResource("/keyper/View/Icons/key-delete-icon.jpg"))};
	  @Override
	  public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row ,int column)
	  {
		  lbl.setText((String)value);
		  lbl.setIcon(icons[keyIcon]);
		  return lbl;
	  }
	  public int getKeyIcon()
	  {
		return keyIcon;
	  }
	  public void setKeyIcon(int keyIcon)
	  {
		 this.keyIcon = keyIcon;
	  }
	  
   }
	
	 @Override
	 public void actionPerformed(ActionEvent event)
	 {
	    JMenuItem menu = (JMenuItem) event.getSource();
	    int selectedRow = table.getSelectedRow();
	    
	    if (menu == menuItemView)
	    {
	    	System.out.println("menuItemView action!!");
	    }
	    else if(menu == menuItemDelete)
	    {
	    	System.out.println("menuItemDelete action!!");
	    }
	    else if(menu == menuItemEdit)
	    {
	    	KeyViewWindow keyview=new KeyViewWindow(keyMap.get(selectedRow));
	    	keyview.setVisible(true);
	    }
	    else if(menu == menuItemCopyUsername)
	    {
			copyUsername(selectedRow);
	    }
	    else if(menu == menuItemCopyPassword)
	    {
	    	copyPassword(selectedRow);
	    }
	 }
	 
	 private void copyPassword(int selectedRow)
	 {
		 try {
			keyMap.get(selectedRow).copypassword();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 powerOnProgessbar();
	 }
	 
	  private void copyUsername(int selectedRow)
	 {	
		 try {
			keyMap.get(selectedRow).copyusername();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 powerOnProgessbar();		
	 }
	  
	 private void powerOnProgessbar()
	 {
		 Thread thread=new ProgressBarThread(progressBar,master.getmConf().getmClipBoardSleepTime());
		 thread.start();
	 }
	 
	 public void RefreshTable(String group)
	 {
		 addColByGroup(group);	
	 }
	
}



