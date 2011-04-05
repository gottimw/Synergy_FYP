package XML_handler;

import nu.xom.Document;
import nu.xom.Element;

public class SynergyMessageReader {

	Document doc;
	XML_reader xML_reader;
	String originalXML;
	
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
	}
	
	public Boolean parseHandshake(String originalXML){
		
		setVariables(originalXML);
		
		Element message = (doc.getRootElement()).getFirstChildElement("SynergyMessage");
		String username = (message.getFirstChildElement("Username")).getValue();
		String password = (message.getFirstChildElement("password")).getValue();
		
		return null;
		
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
}
