package XML_handler;

public enum MessageType {
	DEFAULT			{public String toString(){return "Default";} },
	LOGIN			{public String toString(){return "Login";} },
	BACKUP			{public String toString(){return "Backup";} },
	ERASE_DEVICE	{public String toString(){return "Erase_Device";} },
	HANDSHAKE		{public String toString(){return "Handshake";} },
	
}
