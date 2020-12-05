package database_access;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import data_objects.OrderItem;
import data_objects.Movie;
import data_objects.Rating;
import data_objects.CustomerOrder;

public class VideoRentalDA {

private String databaseURL = "jdbc:mysql://rds-mysql-eecs4312-smartshopper.csh5frcxe9ub.us-east-1.rds.amazonaws.com/smart";
private String dbUserName = "admin";
private String dbPassword = "Netherthistle1";

//Currently movies are uniquely identified by an int videoID. So there could be multiple movies with the same name, so this should return a collection of movies.
public ArrayList<Movie> getMoviesByTitle(String name) throws SQLException {
ArrayList<Movie> output = new ArrayList<Movie>();
	try (
	        Connection conn = DriverManager.getConnection(
	              databaseURL,
	              dbUserName, dbPassword);  
	        Statement stmt = conn.createStatement();
	)  {
		String strSelect = "select * from movies where title = " + "\'" + name + "\';";
		ResultSet rset = stmt.executeQuery(strSelect);
	     while(rset.next()) {   // Move the cursor to the next row, return false if no more row
	    	 int videoID = rset.getInt("videoID");
	    	 int year = rset.getInt("year");
	    	 String category = rset.getString("cat");
	    	 String introduction = rset.getString("introduction");
	    	 String directors = rset.getString("directors");
	    	 String producers = rset.getString("producers");
	    	 int stock = rset.getInt("stock"); 	
	    	 output.add(new Movie(videoID, year, name, category, introduction, directors, producers, stock));
	     }
		 conn.close();
	}
return output;
}
	
	
//Retrieve information about a video by its ID
public Movie getMovieByID(int videoID) throws SQLException {
	Movie output = null;
	try (
	        Connection conn = DriverManager.getConnection(
	              databaseURL,
	              dbUserName, dbPassword);  

	        Statement stmt = conn.createStatement();
	)  {
		String strSelect = "select * from movies where videoID = " + "\'" + videoID + "\';";

		ResultSet rset = stmt.executeQuery(strSelect);
		
	     while(rset.next()) {   // Move the cursor to the next row, return false if no more row
	       
	    	 String title = rset.getString("title");
	    	 int year = rset.getInt("year");
	    	 String category = rset.getString("cat");
	    	 String introduction = rset.getString("introduction");
	    	 String directors = rset.getString("directors");
	    	 String producers = rset.getString("producers");
	    	 int stock = rset.getInt("stock");
	    	 	
	    	 output = new Movie(videoID, year, title, category, introduction, directors, producers, stock));
	     }
	     
	     conn.close();
	}
	
return output;

}	

//Returns all movies in a given category for the main menu, example: drama
public ArrayList<Movie> getMoviesByCat(String cat) throws SQLException {
	ArrayList<Movie> output = new ArrayList<Movie>();
	try (
	        Connection conn = DriverManager.getConnection(
	              databaseURL,
	              dbUserName, dbPassword);  

	        Statement stmt = conn.createStatement();
	)  {
		String strSelect = "select * from movies where cat = " + "\'" + cat + "\';";

		ResultSet rset = stmt.executeQuery(strSelect);
		
	     while(rset.next()) {   // Move the cursor to the next row, return false if no more row
	       
	    	 String title = rset.getString("title");
	    	 int videoID = rset.getInt("videoID");
	    	 int year = rset.getInt("year");
	    
	    	 String introduction = rset.getString("introduction");
	    	 String directors = rset.getString("directors");
	    	 String producers = rset.getString("producers");
	    	 int stock = rset.getInt("stock");
	    	 	
	    	 output.add(new Movie(videoID, year, title, cat, introduction, directors, producers, stock));
	     }
	     
	     conn.close();
	}
	
return output;
}
	
//Create a new order
public void createOrder() throws SQLException  {
	try (
	         Connection conn = DriverManager.getConnection(
	               databaseURL,
	               dbUserName, dbPassword);  
	 
	         Statement stmt = conn.createStatement();
	)  {
		String date = "\"" + LocalDate.now().toString() +"\"";
		String strSelect = "insert into customerOrders (dateOrdered, shippingDestination, period, status, email) values ( " + date + ", \"" + shippingDestination + "\", \"" + period + "\", \"UNPAID\", \'" + email + "\');";
		stmt.executeUpdate(strSelect);
	      
	        conn.close();
	    
}
}

//Removes an order (must remove all existing orderItems corresponding to the order as well)
public void removeOrder() throws SQLException {
try (
	         Connection conn = DriverManager.getConnection(
	               databaseURL,
	               dbUserName, dbPassword);  
	 
	         Statement stmt = conn.createStatement();
	)  {
		String strSelect = "delete from orderItems WHERE orderID = " + orderID + ";";
		stmt.executeUpdate(strSelect);
		strSelect = "delete from customerOrders WHERE orderID = " + orderID + ";";
		stmt.executeUpdate(strSelect);
		conn.close();
	}
}

//Given a customer email, return all of their orders	
public ArrayList<CustomerOrder> getOrders(String email) throws SQLException {
	return null;
	//TODO
}

//Given an orderID return the order information
public CustomerOrder getOrder(int orderID) throws SQLException {
//TODO
return null;
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
public ArrayList<Rating> getRatings(int videoID) throws SQLException{
//TODO	
return null;
}	

public ArrayList<OrderItem> getCurrentOrderItemsByEmail(String email) throws SQLException {
	
	ArrayList<OrderItem> output = new ArrayList<OrderItem>();

	try (
	        Connection conn = DriverManager.getConnection(
	              databaseURL,
	              dbUserName, dbPassword);  

	        Statement stmt = conn.createStatement();
	)  {

		String strSelect = "SELECT movies.title, movies.year, orderItems.quantity, orderItems.videoID, orderItems.orderID, customerOrders.email FROM orderItems INNER JOIN movies ON orderItems.videoID = movies.videoID INNER JOIN customerOrders ON customerOrders.orderID = orderItems.orderID WHERE customerOrders.email = \"" + email + "\" AND customerOrders.status = \"UNPAID\";";
		ResultSet rset = stmt.executeQuery(strSelect);
		 int rowCount = 0;
	     while(rset.next()) {   // Move the cursor to the next row, return false if no more row
	        int orderID = rset.getInt("orderID");
	        int videoID = rset.getInt("videoID");
	        int year = rset.getInt("year");
	        String title = rset.getString("title");
	        int quantity = rset.getInt("quantity");
	        
	        output.add(new OrderItem(title, year, quantity, orderID, videoID, email));
	        ++rowCount;
	     }
	       conn.close();  
	}
	return output;
}
	
	
//Returns an arraylist of OrderItem objects, whose order is PAID and are ready to be delivered.
public ArrayList<OrderItem> getToBeOrderedList() throws SQLException {
ArrayList<OrderItem> output = new ArrayList<OrderItem>();
try (
        Connection conn = DriverManager.getConnection(
              databaseURL,
              dbUserName, dbPassword);  
        Statement stmt = conn.createStatement();
)  {
	String strSelect = "SELECT movies.title, movies.year, orderItems.quantity, orderItems.videoID, orderItems.orderID, customerOrders.email FROM orderItems INNER JOIN movies ON orderItems.videoID = movies.videoID INNER JOIN customerOrders ON customerOrders.orderID = orderItems.orderID;";
	ResultSet rset = stmt.executeQuery(strSelect);
	 int rowCount = 0;
     while(rset.next()) {   // Move the cursor to the next row, return false if no more row
        String email = rset.getString("email");
        int orderID = rset.getInt("orderID");
        int videoID = rset.getInt("videoID");
        int year = rset.getInt("year");
        String title = rset.getString("title");
        int quantity = rset.getInt("quantity");
        output.add(new OrderItem(title, year, quantity, orderID, videoID, email));
        ++rowCount;
     }
       conn.close();  
}
return output;
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


	

