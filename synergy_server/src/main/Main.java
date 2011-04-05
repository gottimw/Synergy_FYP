package main;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.*;


import sockets_server.SocketHandler;
import XML_handler.MessageType;
import XML_handler.SynergyMessageCreator;
import XML_handler.SynergyMessageReader;
import XML_handler.XML_constructor;

public class Main {

	public static void main(String[] args) throws UnknownHostException{

		SocketHandler socket_handler = null;
		SynergyMessageCreator msgCreator = null;
		SynergyMessageReader msgReader = null;
		
		String incomingMessage = null;
		String outgoingMessage = null;
		Boolean socketOpen = true;
		
		while(true){
			
			int port = 10101;
			
			InetAddress thisIp = InetAddress.getLocalHost();
		    System.out.println("IP:"+thisIp.getHostAddress());
		    System.out.println("port:"+port);
			socket_handler = new SocketHandler(port);
			
//*		
			//socket_handler.acceptConnection();
			System.out.print("Waiting for a client");
			socket_handler.acceptConnection();
			
			//Open IO streams for socket communication
			socket_handler.setupIOStreams();
	
		
		
			while(socketOpen){
				//main loop where client is served
				
				//Validate User's Device
				//receive HANDSHAKE message from user
				incomingMessage = (String) socket_handler.receiveObject();
				msgReader = new SynergyMessageReader();
				Boolean loginSucceded = msgReader.parseHandshake(incomingMessage);
				
			
				//send handshake response
				msgCreator = new SynergyMessageCreator(MessageType.HANDSHAKE_RES);
				msgCreator.createHandshakeResponce(loginSucceded.toString());
				outgoingMessage = msgCreator.getReadyXML_Messsage();
				
				socket_handler.sendObject((Object) outgoingMessage);
				
				if(loginSucceded){
					System.out.println("Login Success");
				}else{
					System.out.println("Login Fail - termination of socket");
					socket_handler.teardownSocket();
					socketOpen = false;
					break;
				}
				
	/*
				//Create message
				msgCreator = new SynergyMessageCreator(MessageType.LOGIN);
				
				//Send Message created above
				socket_handler.sendObject(msgCreator.getReadyXML_Messsage());
				
				//Receive Login information
				 incomingMessage = (String) socket_handler.receiveObject();
	*/			
				
			}
		}
//*/	
		//send login request
//		msgCreator = new SynergyMessageCreator(MessageType.LOGIN);
		//System.out.println(msgCreator.getReadyXML_Messsage());
		
		//receive login info
		 
		
		
		
		
		
		
		
		
		
		
		
		
/*
		XML_constructor xmlMsg = new XML_constructor();
		xmlMsg.insertTagToMessage("test", "testValue");
		xmlMsg.insertTagToMessage("test2", "testValue2");
		xmlMsg.insertTagWithAttributeToMessage("Test3", "testval3", "attr1", "testattr1");
		xmlMsg.insertTagWithAttributeToMessage("Test4", "testval4", "attr2", "testattr2");
		System.out.println(xmlMsg.getXMLDoc());
		xmlMsg._debug_getFormatedXMLDoc();
		
		
		
		InetAddress thisIp = InetAddress.getLocalHost();
	    System.out.println("IP:"+thisIp.getHostAddress());

		
		Socket_handler socket_handler = null;
		//DummyClass dummyOutgoing = null, dummyIncoming = null;
		
		socket_handler = new Socket_handler(10101);
		socket_handler.acceptConnection();
		socket_handler.setupIOStreams();
		Object obj = socket_handler.receiveObject();
		//System.out.println( (String) socket_handler.receiveObject() );
		socket_handler.sendObject((Object) "Fuck off john");
		if(obj != null){
			System.out.println("received something");
		}else{
			System.out.println("received nothing ;(");
		}
//*/		
	}

}
