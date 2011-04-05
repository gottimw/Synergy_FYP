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
		xML_Message = new XML_constructor("0.1", "Synergy Server", "Server");

	}
	

	
	private void createLoginMsg(){
		xML_Message.addAttributeToSynergyMessageTag(MessageType.LOGIN.toString());
		xML_Message.insertTagToMessage("Request", "Login Info");
		
	}
	
	public void createHandshakeResponce(String success){
		xML_Message.addAttributeToSynergyMessageTag(MessageType.LOGIN.toString());
		xML_Message.insertTagToMessage("Success", success);
	}
	
	/**
	 * Method returns ready pre made synergy xml message
	 * @return
	 */
	public String getReadyXML_Messsage(){
		return xML_Message.getXMLDoc();
	}
}
