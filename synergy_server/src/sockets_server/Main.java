package sockets_server;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Main {

	public static void main(String[] args) throws UnknownHostException{
		
		InetAddress thisIp = InetAddress.getLocalHost();
	    System.out.println("IP:"+thisIp.getHostAddress());

		
		Socket_handler socket_handler = null;
		//DummyClass dummyOutgoing = null, dummyIncoming = null;
		
		socket_handler = new Socket_handler(10101);
		socket_handler.acceptConnection();
		socket_handler.setupIOStreams();
		Object obj = socket_handler.receiveObject();
		//System.out.println( (String) socket_handler.receiveObject() );
		socket_handler.sendObject((Object) "server message");
		if(obj != null){
			System.out.println("received something");
		}else{
			System.out.println("received nothing ;(");
		}
	}
}
