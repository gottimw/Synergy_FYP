package db;

import java.sql.*;

public class JavaDB {
 
	Connection con = null;
	Statement stmt = null;
	ResultSet rs   = null;
	
	public JavaDB(){
		//TODO: remove hardcode database (maybe config file?,or broker server?)
		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();

			//localhost user root pass root
			con = DriverManager.getConnection("jdbc:mysql:///synergy_db","root", "root");

			if(!con.isClosed())
				System.out.println("_DEBUG: Successfully connected to MySQL server using TCP/IP...");
		}catch(Exception e) {
			System.err.println("Exception: " + e.getMessage());
		}
	}
	
	/**
	 * Method checks if connection to db is still present
	 * @return
	 */
	public boolean isConnected(){
		try {
			return !(con.isClosed());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * Used to execute query on local DB
	 * No semicolon required
	 * ResultSet is a return message from db (java.sql.*)
	 * returns null on failed
	 * @param sqlQuery
	 * @return
	 */
	public ResultSet executeQuery(String sqlQuery){
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sqlQuery);

			return rs;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public void executeUpdate(String sqlQuery){
		try {
			stmt = con.createStatement();
			stmt.executeUpdate(sqlQuery);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
