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
			
			socket_handler = null;
			msgCreator = null;
			msgReader = null;
			
			incomingMessage = null;
			outgoingMessage = null;
						
			int port = 10101;
			socketOpen = true;
			
			InetAddress thisIp = InetAddress.getLocalHost();
		    System.out.println("IP:"+thisIp.getHostAddress());
		    System.out.println("port:"+port);
		    
		    socket_handler = new SocketHandler(port);

			//socket_handler.acceptConnection();
			System.out.println("Waiting for a client");
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
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					break;
				}
				
				socketOpen = false;
			}
			
			//clean up
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			socket_handler.teardownSocket();
		}
	}

}
