package junits;

import static org.junit.Assert.*;

import java.io.IOException;

import nu.xom.Builder;
import nu.xom.Document;
import nu.xom.ParsingException;
import nu.xom.ValidityException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class Junit_XML_reader {

	//variables
	String rawXMLmessage;
	Document doc;
    Builder builder;
	
	@Before
	 public void setup() {
		 rawXMLmessage = "<?xml version=\"1.0\"?>\n" +
	 	  	"<SynergyEnvelope>" +
	 	  		"<SynergyHeader>" +
	 	  			"<Device>Server</Device>" +
	 	  			"<DeviceName>Synergy Server</DeviceName>" +
	 	  			"<SynergyVersion>0.1</SynergyVersion>" +
	 	  		"</SynergyHeader>" +
	 	  	"<SynergyMessage MessageType=\"Login\">" +
	 	  		"<username>root</username>" +
	 	  		"<password>root</password>" +
	 	  	"</SynergyMessage>" +
	 	  "</SynergyEnvelope>" +
	 	  "\n";
		 
		 builder = new Builder();
		 try {
			 doc = builder.build(rawXMLmessage, null);	
		} catch (ValidityException e) {
			e.printStackTrace();
		} catch (ParsingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	 }

	 @After
	 public void tearDown() {

	 }

	 @Test
	 public void simpleTests() {		 
		 assertTrue(doc != null);
	 }
	 

	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
}
