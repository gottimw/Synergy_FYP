package com.android.mainLogic;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OptionalDataException;
import java.io.OutputStream;
import java.io.StreamCorruptedException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class ObjectReceiveControler {

	Object obj;
	
	InetAddress serverAddress;
	Socket clientSocket;
	String socketIp;
	int socketPort;
	InputStream is;
	OutputStream os;
	ObjectInputStream ois;
	ObjectOutputStream oos;
	
	public ObjectReceiveControler(String ip, int port){
		socketIp = ip;
		socketPort = port;	
		setUpSocket();
	}
	
	public ObjectReceiveControler(){
		socketIp = "10.150.9.30";
		socketPort = 10101;
		setUpSocket();	
	}
	
	private void setUpSocket(){
		clientSocket = null;
		try {
			//find server
			serverAddress = InetAddress.getByName(socketIp);
			
			//connect to the server socket
			Socket socket = new Socket(serverAddress, socketPort);
			System.out.println("client socket established"); 
			
            //is = clientSocket.getInputStream();
            //os = clientSocket.getOutputStream();
            System.out.println("Input Output Streams opened"); 
            
            oos = new ObjectOutputStream(socket.getOutputStream());
            ois = new ObjectInputStream(socket.getInputStream());
            
            //oos = new ObjectOutputStream(os);
            //ois = new ObjectInputStream(is);
            System.out.println("Input Output Object Streams opened");          
            
        }catch (UnknownHostException e){
            System.err.println("Don't know about host: "+socketIp);
            System.exit(1);
        }catch (IOException e){
            System.err.println("Couldn't get I/O for the connection to:"+socketIp);
            System.exit(1);
        } 


	}
	
	public Object receiveObject(){
        try {
			obj = ois.readObject();
		} catch (OptionalDataException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return obj;
	}
	
	public void sendObject(Object obj){

		try {
			oos.writeObject(obj);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void closeConnection(){
		try {
			is.close();
			os.close();
			oos.close();
			ois.close();
			clientSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
  
}
