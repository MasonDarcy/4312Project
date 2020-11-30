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

//Currently movies are uniquely identified by an int videoID. So there could be multiple movies with the same name, so this should return a collection of movies.
public String retrieveVideoInformationByName(String name) throws SQLException {
return "?";
//TODO
}
	
//Retrieve information about a video by its ID
public String retrieveVideoInformationByID(int videoID) throws SQLException {
return "?";
//TODO
}	

//Returns all movies in a given category for the main menu, example: drama
public String retrieveAllVideosInCategory(String cat) throws SQLException {
return "?";
//TODO
}
	
//Create a new order
public void createOrder() throws SQLException  {
//TODO	
}

//Removes an order (must remove all existing orderItems corresponding to the order as well)
public void removeOrder() throws SQLException {
//TODO	
}

//Creates a new orderItem with respect to an existing order
public void addOrderItem() throws SQLException {
//TODO	
}

//Removes an existing orderItem
public void removeOrderItem() throws SQLException {
//TODO	
}
	
	
/*MANAGER related functions.-----------*/
public void removeVideo(int videoID) throws SQLException {
//TODO	
}

public void addVideo(String title, int year, String description, String directors, String producers) throws SQLException {
//TODO	
}

//Could have several of these for each attribute	
public void updateVideo() throws SQLException {
//TODO	
}
	
//Add a rating for a movie, ratings are uniquely identified by the videoID and the critic name	
public void addRating(String criticName, String ratingText, int videoID) throws SQLException {
//TODO
}	
	
//Given a videoID, return a list of all ratings with their critics names and the rating text
public void getRatings(int videoID) throws SQLException{
//TODO		
}	
	
//Scan through all current orders that are paid for. Retrieve the list of all orderItems from those orders, because they need to be shipped.
public void getToBeOrderedList() throws SQLException {
//TODO
}

//Need to update an order status from unpaid to paid, paid to delivered, etc
//could be multiple functions, may be dependent on the GUI
public void updateOrderStatus(int orderID) throws SQLException {
//TODO		
}

/*-------------------------------------*/	
	
	
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

/*Account creation related functions-------------------------------------------------------------------------------------------------------------.*/
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

/*------------------------------------------------------------------------s-----------------------------------------------------------------------.*/

}


	

