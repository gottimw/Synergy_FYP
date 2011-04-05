package db;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This class is a wraper for the general javaDB
 * It contains specific functions with queries to DB
 * It also returns processed data back to its parent
 * 
 * @author Gottimw
 *
 */

public class JavaDB_SynergyWraper {

	JavaDB javaDB;
	ResultSet results;
	
	public JavaDB_SynergyWraper(){
		javaDB = new JavaDB();
	}
	
	/**
	 * Method checks if user with pass exists in the database
	 * @param user
	 * @param pass
	 * @return
	 */
	public Boolean isUserInDB(String user, String pass){
	
		results = javaDB.executeQuery("SELECT username FROM users " +
									 "WHERE username=\""+user+"\" and password=\""+pass+"\"");
		try{
			results.next();
			
			if( results.getRow() > 0 )
				return true;
			
		}catch(SQLException e){
			e.printStackTrace();
		}
		return false;			
	}
	
}
