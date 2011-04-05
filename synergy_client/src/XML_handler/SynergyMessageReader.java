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
	
	/**
	 * returns true if login was successful
	 * @param originalXML
	 * @return
	 */
	public Boolean parseHandshake_Res(String xml_message){
		
		String responce = getTagValue("Success", xml_message);
		
		if(responce.compareToIgnoreCase("true") == 0)
			return true;
		else
			return false;	
	}

	private String getTagValue(String tag, String msg){
		
		String stTag = "<"+tag+">";
		String endTag = "</"+tag+">";
		
		int start = msg.indexOf(stTag);
		start += stTag.length();
		
		int end = msg.indexOf(endTag);
		
		if(end == -1)
			return null;
		
		return (String) msg.subSequence(start, end);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
