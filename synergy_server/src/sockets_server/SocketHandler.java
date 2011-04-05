package sockets_server;

import java.net.*;
import java.io.*;

public class SocketHandler{
   
	ServerSocket serverSocket;
    Socket clientSocket;
    InputStream  is; 
    OutputStream os;
    ObjectInputStream ois;
    ObjectOutputStream oos;
   
    /**
     * Simple constructor that creates ServerSocket with 'socket' param
     * @param port
     */  
	public SocketHandler(int port){
		try{
            serverSocket = new ServerSocket(port);
            System.out.println(
            		Thread.currentThread().getStackTrace()[2].getMethodName() +
            		": server socket ready");
        }catch(IOException e){
            System.err.println(
            		Thread.currentThread().getStackTrace()[2].getMethodName() +
            		": Could not listen on port: "+port+".");
            System.exit(1);
        }
	}
	
	/**
	 *
	 * method listens for a client to establish connection
	 * returns true if client found and accepted connection
	 * @return boolean connectionEstablished
	 */
	public boolean acceptConnection(){
		try {
			//serverSocket.setSoTimeout(30000);
            clientSocket = serverSocket.accept();
            System.out.println("server socket connection established");
            //serverSocket.setSoTimeout(0);
        } catch (IOException e) {
			return false;
        }
        return true;
	}

	/**
	 * Method sets up Input and Output streams
	 * @return true on success
	 */
	public boolean setupIOStreams(){
        try{
			is = clientSocket.getInputStream();
			os = clientSocket.getOutputStream();
		}catch(IOException e){
			System.err.println(
            		Thread.currentThread().getStackTrace()[2].getMethodName() +
            		": opening the input/output stream of server socket failed");
            System.exit(1);
			e.printStackTrace();
		}
		
		 try{
			ois = new ObjectInputStream(is);
			oos = new ObjectOutputStream(os);
		}catch(IOException e){
			System.err.println(
            		Thread.currentThread().getStackTrace()[2].getMethodName() +
            		": opening the OBJECT input/output stream of server socket failed");
            System.exit(1);
			e.printStackTrace();
		}	
		return true;
	}
	
	/**
	 * Method for releasing all resources and closing all streams
	 */
	public void teardownSocket(){
		
		 try {
			 ois.close();
			 oos.close();
			 is.close(); 
			 os.close();
			 clientSocket.close();
			 serverSocket.close();
		} catch (IOException e) {
			System.err.println(
            		Thread.currentThread().getStackTrace()[2].getMethodName() +
            		": tearing down of server socket failed failed");
            System.exit(1);
			e.printStackTrace();
		}
	     
	}
	
	/**
	 * Method used to receive an object of class Object
	 * Note that prior to receiving following methods must be run:
	 * - acceptConnection()
	 * - setupIOStreams()
	 * 
	 * @return instance of Object class 
	 */
	public Object receiveObject(){
		try {
			return ois.readObject();
		} catch (IOException e) {
			System.err.println(
            		Thread.currentThread().getStackTrace()[2].getMethodName() +
            		": failed to receive object IO exception");
            System.exit(1);
			e.printStackTrace();
			return null;
		} catch (ClassNotFoundException e) {
			System.err.println(
            		Thread.currentThread().getStackTrace()[2].getMethodName() +
            		": failed to receive object ClassNotFoundException exception");
            System.exit(1);
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Method used to send an object of class Object
	 * Note that prior to sending following methods must be run:
	 * - acceptConnection()
	 * - setupIOStreams()
	 * 
	 * returns VOID
	 */
	public void sendObject(Object obj){
		try {
			oos.writeObject(obj);
			System.out.println("Object Sent");
		} catch (IOException e) {
			System.err.println(
            		Thread.currentThread().getStackTrace()[1].getMethodName() +
            		": failed to send object");
            System.exit(1);
			e.printStackTrace();
		}
	}
}
