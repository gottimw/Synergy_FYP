package db;

import java.sql.*;

public class javaDB {
 
	public static void main(String[] args) throws ClassNotFoundException {
		Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
		
		Connection dbConnection = null;
		String strUrl = "jdbc:derby:DefaultAddressBook;create=true";

		try {
		    dbConnection = DriverManager.getConnection(strUrl);
		} catch (SQLException ex) {
		    ex.printStackTrace();
		}

	}
	
}
