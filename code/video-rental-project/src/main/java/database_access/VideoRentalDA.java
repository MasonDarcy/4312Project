package database_access;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class VideoRentalDA {

private String databaseURL = "jdbc:mysql://rds-mysql-eecs4312-smartshopper.csh5frcxe9ub.us-east-1.rds.amazonaws.com/smart";
private String dbUserName = "admin";
private String dbPassword = "Netherthistle1";

//Currently movies are uniquely identified by an int videoID
public void retrieveVideoInformationByName(int videoID) throws SQLException {
	
}


/*Untested*/
public void addManager(String email, String password) throws SQLException {
	try (
	         Connection conn = DriverManager.getConnection(
	               databaseURL,
	               dbUserName, dbPassword);  
	 
	         Statement stmt = conn.createStatement();
	)  {
	
		String strSelect = "insert into managers values (" + "\"" + email + "\", " + "\"" + password + ");";
		stmt.executeUpdate(strSelect);
	      
	        conn.close();
	    
}
}

/*Adds a new customer to the system.*/
public void addCustomer(String email, String password) throws SQLException {
	try (
	         Connection conn = DriverManager.getConnection(
	               databaseURL,
	               dbUserName, dbPassword);  
	 
	         Statement stmt = conn.createStatement();
	)  {
	
		String strSelect = "insert into customers values (" + "\"" + email + "\", " + "\"" + password + "\", " + 0 + ", null)" + ";";
		stmt.executeUpdate(strSelect);
	      
	        conn.close();
	    
}
}


/*Given an e-mail, returns true if the customer already exists. For use in GUI controller in rejecting duplicate account creation.*/
public boolean customerExists(String email) throws SQLException {
	try (
	         Connection conn = DriverManager.getConnection(
	               databaseURL,
	               dbUserName, dbPassword);  
	 
	         Statement stmt = conn.createStatement();
	)  {
	
		String strSelect = "select email from customers WHERE email = " + "\"" + email + "\"" + ";";
		ResultSet rset = stmt.executeQuery(strSelect);
	        int rowCount = 0;
	        while(rset.next()) {   // Move the cursor to the next row, return false if no more row
	           String fetchedEmail = rset.getString("email");
	           System.out.println(fetchedEmail);
	           ++rowCount;
	        }
	        conn.close();
	        if(rowCount > 0) {
	        	return true;
	        }
}
	return false;
}

/*Untested. Given an e-mail, returns true if the manager already exists. For use in the GUI controller in rejecting duplicate account creation. */
public boolean managerExists(String email) throws SQLException {
	try (
	         Connection conn = DriverManager.getConnection(
	               databaseURL,
	               dbUserName, dbPassword);  
	 
	         Statement stmt = conn.createStatement();
	)  {
	
		String strSelect = "select email from managers WHERE email = " + "\"" + email + "\"" + ";";
		ResultSet rset = stmt.executeQuery(strSelect);
	        int rowCount = 0;
	        while(rset.next()) {   // Move the cursor to the next row, return false if no more row
	           String fetchedEmail = rset.getString("email");
	           System.out.println(fetchedEmail);
	           ++rowCount;
	        }
	        conn.close();
	        if(rowCount > 0) {
	        	return true;
	        }
}
	return false;
	
}



}


	
