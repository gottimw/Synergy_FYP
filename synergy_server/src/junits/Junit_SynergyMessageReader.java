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

import XML_handler.SynergyMessageReader;

public class Junit_SynergyMessageReader {

	//variables
	String rawXMLmessage;
	SynergyMessageReader msgReader;
	
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
		 
		 msgReader = new SynergyMessageReader();
	 }

	 @After
	 public void tearDown() {

	 }

	 @Test
	 public void userWantsToLogIn() {		 

		
		 
		 
	 }
	 

	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
}
