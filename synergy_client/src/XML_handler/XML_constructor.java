package XML_handler;

import java.io.IOException;

import nu.xom.*;

public class XML_constructor {

	Document xmlDocument;
	Element root;
	Element header;
	Element message;
	
	String protocolVersion;
	String deviceName;
	String device;
	
	/**
	 * Default constructor
	 */
	public XML_constructor(){
		
		this.protocolVersion = "0.1";
		this.deviceName = "Synergy Server";
		this.device = "Server";
		
		constructEmptyMessage();
	}
	
	/**
	 * Constructor with headers params
	 * Use to create customised messages
	 */
	public XML_constructor(String protocolVersion, String deviceName, String device){
		
		this.protocolVersion = protocolVersion;
		this.deviceName = deviceName;
		this.device = device;
		
		constructEmptyMessage();
	}
	
	/**
	 * Creates empty message with header
	 * XML document has following structure
	 * root
	 * *header
	 * *message
	 */
	private void constructEmptyMessage(){
		
		//create basic elements of XML doc
		root = new Element("SynergyEnvelope");
		header = new Element("SynergyHeader");
		message = new Element("SynergyMessage");
		
		//append header and message to root element
		root.appendChild(header);
		root.appendChild(message);
		
		//create xml elements
		Element deviceElem = new Element("Device");
		Element deviceNameElem = new Element("DeviceName");
		Element versionElem = new Element("SynergyVersion");
		
		//append values to each element
		deviceElem.appendChild(device);
		deviceNameElem.appendChild(deviceName);
		versionElem .appendChild(protocolVersion);
		
		//append each element to header
		header.appendChild(deviceElem);
		header.appendChild(deviceNameElem);
		header.appendChild(versionElem);
	}
	
	/**
	 * Final method to call when the XML 
	 * doc has been finished to get XML doc
	 * @return XMLdoc
	 */ 	
	public String getXMLDoc(){
		xmlDocument = new Document(root);
		return xmlDocument.toXML();		
	}
		
	
	/**
	 * DEBUG METHOD
	 * Prints out current XML message in readable form
	 */
	public void _debug_getFormatedXMLDoc(){

		if(xmlDocument == null)
			xmlDocument = new Document(root);
		
		try{
			Serializer serializer = new Serializer(System.out, "ISO-8859-1");
			serializer.setIndent(4);
			serializer.setMaxLength(64);
			serializer.write(xmlDocument);  
		}
		catch(IOException ex){
			System.err.println("getFormatedXMLDoc() failed to format XML");
			System.err.println(ex); 
		}
		
	}
	
	/**
	 * Method that inserts one tag into Synergy message tag
	 * Use to build up a message
	 * 
	 * <tagName> tagValue </tagName>
	 * 
	 */
	public void insertTagToMessage(String tagName, String tagValue){
		
		Element elem = new Element(tagName);
		elem.appendChild(tagValue);
		message.appendChild(elem);
	}
	
	public void insertTagForBackup(String name, String phone, String email){
		
		Element contactE = new Element("Contact");
		Element nameE = new Element("Name");
		Element phoneE = new Element("Phone");
		Element emailE = new Element("Email");
		
		nameE.appendChild(name);
		phoneE.appendChild(phone);
		emailE.appendChild(email);
		
		contactE.appendChild(nameE);
		contactE.appendChild(phoneE);
		contactE.appendChild(emailE);
		
		message.appendChild(contactE);
	}
	
	/**
	 * Method that inserts one tag into Synergy message tag
	 * Additionally an attribute is added to tag as well
	 * Use to build up a message
	 * 
	 * <tagName attributeName="attributeValue"> tagValue </tagName>
	 * 
	 */
	public void insertTagWithAttributeToMessage(String tagName, String tagValue, 
												String attributeName, String attributeValue){
		//create tag
		Element elem = new Element(tagName);
		//add value of the tag to it
		elem.appendChild(tagValue);
		//create new attribute with its value
		Attribute attr = new Attribute(attributeName, attributeValue);
		//attach attribute to tag
        elem.addAttribute(attr);
        //attach tag to root (message)
		message.appendChild(elem);
	}
	
	/**
	 * Method Used for adding additional attribute to an existing tag
	 * ONLY WORKS FOR DIRECT CHILDREN ON MESSAGE and ROOT (NOT FINISHED)
	 */
	public void addAttributeToExistingTag(String existingTagName, String attributeName,
										  String attributeValue){
		//TODO:make it recursive now only check for children in message tag (sorry future me)
		Element elem = null;
		
		if( existingTagName == "SynergyMessage"){
			elem = root.getFirstChildElement(existingTagName);
			
			if(elem != null){
				Attribute attr = new Attribute(attributeName, attributeValue);
				elem.addAttribute(attr);		
			}else{
				System.out.println("Error: Failed to add attribute to tag; tag not found in xml body");
			}
		}else{
			elem = message.getFirstChildElement(existingTagName);
			
			if(elem != null){				
				Attribute attr = new Attribute(attributeName, attributeValue);
				elem.addAttribute(attr);		
			}else{
				System.out.println("Error: Failed to add attribute to tag; tag not found in xml body2");
			}	
		}	
		
	}
	
	/**
	 * Method for seting Synergy Message Type or setting it
	 * @param messageTypeValue
	 */
	public void addAttributeToSynergyMessageTag(String messageTypeValue){
		addAttributeToExistingTag("SynergyMessage", "messageType", messageTypeValue);
	}

}
























