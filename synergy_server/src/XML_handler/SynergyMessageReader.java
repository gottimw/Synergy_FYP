package XML_handler;

import db.JavaDB_SynergyWraper;
import nu.xom.Document;
import nu.xom.Element;
import nu.xom.Node;

public class SynergyMessageReader {

	Document doc;
	XML_reader xML_reader;
	String originalXML;
	JavaDB_SynergyWraper db_handler;
	
	/**
	 * Class used for reading all synergy messages targeted to server
	 * This class does all the dirty work so main class call neatly couple
	 * methods to do the work
	 * 
	 */
	public SynergyMessageReader(){
		
	}
	
	private void setVariables(String originalXML){
		this.originalXML = originalXML;
		xML_reader = new XML_reader(originalXML);
		doc = xML_reader.getXMLDocument();
		db_handler = new JavaDB_SynergyWraper();
	}
	
	/**
	 * Method used to validate username password and login
	 * To be used with a LoginResponce Message initiated by Login 
	 * message sent to user
	 * @param originalXML
	 * @return
	 */	
	public Boolean parseHandshake(String originalXML){
		
		setVariables(originalXML);
		
		Element message = (doc.getRootElement()).getFirstChildElement("SynergyMessage");
		String username = (message.getFirstChildElement("Username")).getValue();
		String password = (message.getFirstChildElement("Password")).getValue();
		
		return ( db_handler.isUserInDB(username, password) );
		
	}
	
	public void parseBackupContacts(String originalXML){
		
		setVariables(originalXML);
		
		Element message = (doc.getRootElement()).getFirstChildElement("SynergyMessage");
		
		for(int i = 0 ; i < message.getChildCount() ; i++){
			
			Node contact = message.getChild(i);
			Node nameN = contact.getChild(0);
			Node phoneN = contact.getChild(1);
			Node emailN = contact.getChild(2);
			
			String name  = nameN.getValue();
			String phone = phoneN.getValue();
			String email = emailN.getValue();
			
			db_handler.addContactToDB(name, phone, email);
		}
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
}
