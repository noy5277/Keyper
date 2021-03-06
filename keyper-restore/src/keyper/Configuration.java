package keyper;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;

import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class Configuration extends Encryption {
	
	private String mLastDbPath;
	private int mClipBoardPauseTime;
	private int mLockAfterTime;
	private int mLastConnectionStat;
	private File mXmlFile;
	private File mBinFile;
	private MasterPassword master;
	private String mSid;
	private Document conf;
	private DocumentBuilderFactory dbFactory; 
    private DocumentBuilder dBuilder;
    private String  mPrivatekey;
  
	
	public Configuration(MasterPassword master) throws NoSuchAlgorithmException, NoSuchPaddingException, ParserConfigurationException
	{
      super();
      this.master=master;
      this.mLastDbPath=" ";
      this.mXmlFile=new File("C:\\Users\\"+master.getUsername()+"\\AppData\\Roaming\\Keyper\\configuration.xml");
      this.mBinFile=new File("C:\\Users\\"+master.getUsername()+"\\AppData\\Roaming\\Keyper\\ProtectedUserKey.dat");
      this.dbFactory = DocumentBuilderFactory.newInstance();
      this.mClipBoardPauseTime=12;
	   this.mLockAfterTime=300;
	   this.mLastConnectionStat=1;
	  this.dBuilder = dbFactory.newDocumentBuilder();
      
	}
	
	
	public File getmBinFile() {
		return mBinFile;
	}


	public void setmClipBoardPauseTime(int mClipBoardPauseTime) {
		this.mClipBoardPauseTime = mClipBoardPauseTime;
	}

	public String getmSid() {
		return mSid;
	}

	public void setmSid(String mSid) {
		this.mSid = mSid;
	}

	public File getmXmlFile() {
		return mXmlFile;
	}

	public void setmXmlFile(File mXmlFile) {
		this.mXmlFile = mXmlFile;
	}

	public String getmLastDbPath() {
		return mLastDbPath;
	}

	public void setmLastDbPath(String mLastDbPath) {
		
		this.mLastDbPath = mLastDbPath;
	}

	public int getmClipBoardSleepTime() {
		return mClipBoardPauseTime;
	}

	public void setmClipBoardSleepTime(int mClipBoardSleepTime) {
		this.mClipBoardPauseTime = mClipBoardSleepTime;
	}

	public int getmLockAfterTime() {
		return mLockAfterTime;
	}

	public void setmLockAfterTime(int mLockAfterTime) {
		this.mLockAfterTime = mLockAfterTime;
	}

	public int getmLastConnectionStat() {
		return mLastConnectionStat;
	}

	public void setmLastConnectionStat(int mLastConnectionStat) {
		this.mLastConnectionStat = mLastConnectionStat;
	}
	
	private boolean createdirectory(MasterPassword master)
	{
		return(new File("C:\\Users\\"+master.getUsername()+"\\AppData\\Roaming\\Keyper").mkdir());
	}
	
	private void createXmlFile(MasterPassword master)throws ParserConfigurationException, TransformerException
	{
		try {
		   System.out.println(createdirectory(master));
		   this.mLastDbPath=master.getPath();
		   this.conf = dBuilder.newDocument();
           Element rootElement = conf.createElement("Configuration");
           conf.appendChild(rootElement);
           rootElement.appendChild(stringnode(conf,"LastDBPath", this.mLastDbPath));
           rootElement.appendChild(intnode(conf,"ClipBoardPauseTime",this.mClipBoardPauseTime));
           rootElement.appendChild(intnode(conf, "LockAfterTime", this.mLockAfterTime));
           rootElement.appendChild(intnode(conf, "LastConnectionStat",this.mLastConnectionStat));
           StreamResult file = new StreamResult(this.mXmlFile);
           TransformerFactory transformerFactory = TransformerFactory.newInstance();
           Transformer transformer = transformerFactory.newTransformer();
           transformer.setOutputProperty(OutputKeys.INDENT, "yes");
           DOMSource source = new DOMSource(conf);
           transformer.transform(source, file);
	     }
	     catch (Exception e)
	     {
            e.printStackTrace();
         }
	}
	

	private void createBinFile(MasterPassword master) throws IOException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException
	{
		this.mPrivatekey=getMyKey();
		FileWriter temp= new FileWriter(mBinFile);
		BufferedWriter bf = new BufferedWriter(temp);
		bf.write(encrypt(master.getPassword()));
		bf.newLine();
		bf.write(encrypt(master.getKeyfile()));
		bf.newLine();
		bf.write(encrypt(master.getSid()));
		bf.newLine();
		bf.write(encrypt(this.mPrivatekey));
		bf.newLine();
		bf.close();
		
	}
	
	
	public void createFiles(MasterPassword master) throws ParserConfigurationException, TransformerException, IOException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException 
	{ 
		 if(createdirectory(master))
		 {
		    createXmlFile(master);
		    createBinFile(master);
	     } 
	}
	
	private Node stringnode(Document doc,String name, String value)
	{
	   
	   Element node = doc.createElement(name);
       node.appendChild(doc.createTextNode(value));
       return node;
	}
	
	private Node intnode(Document doc,String name, int value)
	{
	   Element node = doc.createElement(name);
       node.appendChild(doc.createTextNode(String.valueOf(value)));
       return node;
	}
	
	
	private static String getTagValue(Element element) {
		
        NodeList nodeList = element.getChildNodes();
        Node node = (Node) nodeList.item(0);
        return node.getNodeValue();
    }
	
	
	private void importXml() throws SAXException, IOException, ParserConfigurationException
	{
		
        Document doc = dBuilder.parse(mXmlFile);
        doc.getDocumentElement().normalize();
        System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
        NodeList nodeList = doc.getDocumentElement().getChildNodes();
        
        Element element1 = (Element) nodeList.item(1);
        this.mLastDbPath=getTagValue(element1);
        master.setPath(this.mLastDbPath);
        Element element3 = (Element) nodeList.item(3);
        this.mClipBoardPauseTime=Integer.parseInt(getTagValue(element3));
        
        Element element5 = (Element) nodeList.item(5);
        this.mLockAfterTime=Integer.parseInt(getTagValue(element5)); 
        
        Element element7 = (Element) nodeList.item(7);
        this.mLastConnectionStat=Integer.parseInt(getTagValue(element7));  
        
	}
	
	
	
	private void importBin() throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException, IOException 
	{
		FileReader temp= new FileReader(mBinFile);
		BufferedReader bf = new BufferedReader(temp);
		master.setMpassword(decrypt(bf.readLine()));
		master.setKeyfile(decrypt(bf.readLine()));
		master.setMsid(bf.readLine());
		this.mPrivatekey=decrypt(bf.readLine());
		bf.close();
	}

	public void importFiles() throws SAXException, IOException, ParserConfigurationException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		importXml();
		importBin();
		
	}

}
