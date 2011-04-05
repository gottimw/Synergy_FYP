package junits;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import XML_handler.MessageType;
import XML_handler.SynergyMessageCreator;

public class Junit_SynergyMessageCreator {

	//variables
	SynergyMessageCreator smc;
	
	@Before
	 public void setup() {

	 }

	 @After
	 public void tearDown() {

	 }

	 @Test
	 public void simpleTests() {
		 
		 smc = new SynergyMessageCreator(MessageType.LOGIN);
		 
		 
		 //Assert
		 //assertEquals("Error msg", var1, var2);
		 //assertEquals("Name of receive obj doesnt match ", "test pass", dummyIncoming.getName());
		 

	 }

}
