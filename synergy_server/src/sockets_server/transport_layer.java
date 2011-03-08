package sockets_server;

public interface transport_layer {

	public Object receiveObject();
	public void sendObject(Object obj);

}
