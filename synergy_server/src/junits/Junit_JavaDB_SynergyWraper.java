package junits;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import db.JavaDB_SynergyWraper;

public class Junit_JavaDB_SynergyWraper {
	//variables
	JavaDB_SynergyWraper javaDB;
	Boolean success; 
	
	@Before
	 public void setup() {
		javaDB = new JavaDB_SynergyWraper();
	 }
	
	 @After
	 public void teardown(){
		 
	 }

	 @Test
	 public void checkIfUserIsValid() {
		 
		 success = javaDB.isUserInDB("root", "root");

		 assertTrue(success);	 
	 }
	 
	
}
