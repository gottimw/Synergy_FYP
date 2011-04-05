package junits;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import XML_handler.XML_constructor;


public class Junit_XML_constructor {
	
	XML_constructor xmlMsg;
	
	@Before
	 public void setup() {
		xmlMsg = new XML_constructor("0.1", "Synergy Server", "Server");
	 }

	 @After
	 public void tearDown() {

	 }

	 @Test
	 public void headersTest() {
		 
		 assertEquals("Produced XML msg does not match desired msg",
				 	  "<?xml version=\"1.0\"?>\n" +
				 	  	"<SynergyEnvelope>" +
				 	  		"<SynergyHeader>" +
				 	  			"<Device>Server</Device>" +
				 	  			"<DeviceName>Synergy Server</DeviceName>" +
				 	  			"<SynergyVersion>0.1</SynergyVersion>" +
				 	  		"</SynergyHeader>" +
				 	  	"<SynergyMessage />" +
				 	  "</SynergyEnvelope>" +
				 	  "\n",
				 	  
				 	  xmlMsg.getXMLDoc()
		 			);
	 }
	 
	 @Test
	 public void addTag(){
		 
		 xmlMsg.insertTagToMessage("tag", "TagValue");
		 
		 assertEquals("Produced XML msg does not match desired msg",
			 	  "<?xml version=\"1.0\"?>\n" +
			 	  	"<SynergyEnvelope>" +
			 	  		"<SynergyHeader>" +
			 	  			"<Device>Server</Device>" +
			 	  			"<DeviceName>Synergy Server</DeviceName>" +
			 	  			"<SynergyVersion>0.1</SynergyVersion>" +
			 	  		"</SynergyHeader>" +
			 	  	"<SynergyMessage>" +
			 	  		"<tag>TagValue</tag>" +
			 	  	"</SynergyMessage>" +
			 	  "</SynergyEnvelope>" +
			 	  "\n",
			 	  
			 	  xmlMsg.getXMLDoc()
	 			);
	 }
	 
	 @Test
	 public void addTagWithAttribute(){
		 
		 xmlMsg.insertTagWithAttributeToMessage("tag", "TagValue", "attr", "attrVal");
		 
		 
		 assertEquals("Produced XML msg does not match desired msg",
			 	  "<?xml version=\"1.0\"?>\n" +
			 	  	"<SynergyEnvelope>" +
			 	  		"<SynergyHeader>" +
			 	  			"<Device>Server</Device>" +
			 	  			"<DeviceName>Synergy Server</DeviceName>" +
			 	  			"<SynergyVersion>0.1</SynergyVersion>" +
			 	  		"</SynergyHeader>" +
			 	  	"<SynergyMessage>" +
			 	  		"<tag attr=\"attrVal\">TagValue</tag>" +
			 	  	"</SynergyMessage>" +
			 	  "</SynergyEnvelope>" +
			 	  "\n",
			 	  
			 	  xmlMsg.getXMLDoc()
	 			);
	 }
	 
	 @Test
	 public void addAtributeToExistingTag(){
		 
		 xmlMsg.insertTagToMessage("tag", "TagValue");
		 xmlMsg.addAttributeToExistingTag("tag", "attr", "attrVal");
		 
		 assertEquals("Produced XML msg does not match desired msg",
			 	  "<?xml version=\"1.0\"?>\n" +
			 	  	"<SynergyEnvelope>" +
			 	  		"<SynergyHeader>" +
			 	  			"<Device>Server</Device>" +
			 	  			"<DeviceName>Synergy Server</DeviceName>" +
			 	  			"<SynergyVersion>0.1</SynergyVersion>" +
			 	  		"</SynergyHeader>" +
			 	  	"<SynergyMessage>" +
			 	  		"<tag attr=\"attrVal\">TagValue</tag>" +
			 	  	"</SynergyMessage>" +
			 	  "</SynergyEnvelope>" +
			 	  "\n",
			 	  
			 	  xmlMsg.getXMLDoc()
	 			);
	 }
}
