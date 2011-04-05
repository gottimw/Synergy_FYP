package junits;

import org.junit.*;
import sockets_server.*;
import static org.junit.Assert.*;


public class Junit_SocketHandler {

	SocketHandler socket_handler = null;
	DummyClass dummyOutgoing = null, dummyIncoming = null;
	
	@Before
	 public void setup() {
		socket_handler = new SocketHandler(10101);
		socket_handler.acceptConnection();
		socket_handler.setupIOStreams();
		dummyOutgoing = new DummyClass(111, "test pass");
	 }

	 @After
	 public void tearDown() {
	   socket_handler.teardownSocket();
	 }

	 @Test
	 public void simpleTests() {
		 socket_handler.sendObject(dummyOutgoing);
		 dummyIncoming = (DummyClass) socket_handler.receiveObject();
		 
		 assertEquals("Age of receive obj doesnt match ", 111, dummyIncoming.getAge());
		 assertEquals("Name of receive obj doesnt match ", "test pass", dummyIncoming.getName());
		 
		 System.out.println("End of simpleTest()");
	 }


}
