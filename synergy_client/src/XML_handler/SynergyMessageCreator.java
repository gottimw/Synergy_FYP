package XML_handler;

import XML_handler.XML_constructor;
import XML_handler.MessageType;

public class SynergyMessageCreator {

	MessageType messageType;
	XML_constructor xML_Message;
	

	
	/**
	 * Creator of Synergy messages of type enum MessageType
	 * @param messageTypeEnum
	 */
	
	public SynergyMessageCreator(MessageType messageTypeEnum){
		//TODO: Refactor XML_constructor to not use hardcoded system params
		
		messageType = messageTypeEnum;
		xML_Message = new XML_constructor("0.1", "Marcin's Android", "Android");
		
	}

	public void createLoginMsg(){
		xML_Message.addAttributeToSynergyMessageTag(MessageType.LOGIN.toString());
		xML_Message.insertTagToMessage("Request", "Login Info");
		
	}
	
	public void createHandshakeMsg(String username, String password){
		xML_Message.addAttributeToSynergyMessageTag(MessageType.HANDSHAKE.toString());
		xML_Message.insertTagToMessage("Username", username);
		xML_Message.insertTagToMessage("Password", password);

	}
	
	/**
	 * Method returns ready pre made synergy xml message
	 * @return
	 */
	public String getReadyXML_Messsage(){
		return xML_Message.getXMLDoc();
	}
}
