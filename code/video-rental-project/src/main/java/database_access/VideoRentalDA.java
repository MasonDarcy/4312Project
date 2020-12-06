package database_access;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import data_objects.CustomerOrder;
import data_objects.Movie;
import data_objects.OrderItem;
import data_objects.Rating;

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
public Movie retrieveVideoInformationByID(int videoID) throws SQLException {
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
	    	 	
	    	 output = new Movie(videoID, year, title, category, introduction, directors, producers, stock);
	     }
	     
	     conn.close();
	}
	
return output;

}	

//Returns all movies in a given category for the main menu, example: drama
public ArrayList<Movie> retrieveMoviesByCat(String cat) throws SQLException {
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

//Checks if a customer has an unpaid order, implicitely this order is open, so we can add order items to it
public int hasUnpaidOrder(String email) throws SQLException {
	int output = 0;
	try (
	         Connection conn = DriverManager.getConnection(
	               databaseURL,
	               dbUserName, dbPassword);  
	 
	         Statement stmt = conn.createStatement();
	)  {
	
		String strSelect = "select orderID from customerOrders WHERE email = " + "\"" + email + "\" AND status = \"UNPAID\"" + ";";
		ResultSet rset = stmt.executeQuery(strSelect);
	        int rowCount = 0;
	        while(rset.next()) {   // Move the cursor to the next row, return false if no more row
	           output = rset.getInt("orderID");
	           
	        }
	        conn.close();
	       
	        	
	        }
	return output;
}


//Create a new order
public void createCustomerOrder(String email) throws SQLException  {
	try (
	         Connection conn = DriverManager.getConnection(
	               databaseURL,
	               dbUserName, dbPassword);  
	 
	         Statement stmt = conn.createStatement();
	)  {
		String date = LocalDate.now().toString();
		String strSelect = "insert into customerOrders (dateOrdered, status, email) values ( \'" + date + "\', \"UNPAID\", \'" + email + "\');";
		stmt.executeUpdate(strSelect);
	      
		
	        conn.close();
	    
}
	
}

public boolean movieAlreadyExistsOnUnpaidOrder(int orderID, int videoID) {
	
//	try (
//	        Connection conn = DriverManager.getConnection(
//	              databaseURL,
//	              dbUserName, dbPassword);  
//
//	        Statement stmt = conn.createStatement();
//	)  {
//
//		String strSelect = "select * from orderItems WHERE orderID = " + orderID + " AND videoID = " + videoID ;
//		ResultSet rset = stmt.executeQuery(strSelect);
//		 int rowCount = 0;
//	     while(rset.next()) {   // Move the cursor to the next row, return false if no more row
//	        
//	       
//	      
//	        ++rowCount;
//	     }
//	     
//	       conn.close();
	return true;
}


//Removes an order (must remove all existing orderItems corresponding to the order as well)
public void removeOrder(int orderID) throws SQLException {
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

public ArrayList<CustomerOrder> getOrders(String email) throws SQLException {
	ArrayList<CustomerOrder> output = new ArrayList<CustomerOrder>();
	try (
	        Connection conn = DriverManager.getConnection(
	              databaseURL,
	              dbUserName, dbPassword);  

	        Statement stmt = conn.createStatement();
	)  {
		String strSelect = "select * from customerOrders where email = \"" + email + "\" AND status <> \"UNPAID\";";

		ResultSet rset = stmt.executeQuery(strSelect);
		
	     while(rset.next()) {   // Move the cursor to the next row, return false if no more row
	    	 int orderID = rset.getInt("orderID");
	    	 String dateOrdered = rset.getString("dateOrdered");
	    	 String shippingDestination = null;
	    	 int period = rset.getInt("period");
	    	 String status = rset.getString("status");
	    	 
	    	 CustomerOrder n = new CustomerOrder(orderID, dateOrdered, null, period, status, null, email);
	    	output.add(n);
	     }
	     
	     conn.close();
	}
	
return output;

}

public CustomerOrder getOrder(int orderID) throws SQLException {
	//TODO
	return null;
}

//Creates a new orderItem with respect to an existing order
public void addOrderItem(int orderID, int videoID, int quantity, int period) throws SQLException {
	try (
	         Connection conn = DriverManager.getConnection(
	               databaseURL,
	               dbUserName, dbPassword);  
	 
	         Statement stmt = conn.createStatement();
	)  {
	
		String strSelect = "insert into orderItems(orderID, videoID, quantity, period) values (" + orderID + ", " + videoID + ", " + quantity + ", " + period + ");";
		//System.out.println(strSelect);
		stmt.executeUpdate(strSelect);
	      
	        conn.close();
	    
}
}

public int getStock(int videoID) throws SQLException{
	
	int stock = 0;
	try (
	         Connection conn = DriverManager.getConnection(
	               databaseURL,
	               dbUserName, dbPassword);  
	 
	         Statement stmt = conn.createStatement();
			
	)  {
	
		String strSelect = "select stock from movies where videoID = " + videoID + ";";
		//System.out.println(strSelect);
		stmt.executeQuery(strSelect);
		ResultSet rset = stmt.executeQuery(strSelect);
		 while(rset.next()) { 
		stock = rset.getInt("stock");
	      
		 }
	        conn.close();
	}
	
	return stock;
	
}

public int getLoyalty(String email) throws SQLException {
	int points = 0;
	try (
	         Connection conn = DriverManager.getConnection(
	               databaseURL,
	               dbUserName, dbPassword);  
	 
	         Statement stmt = conn.createStatement();
			
	)  {
	
		String strSelect = "select loyaltyPoints from customers WHERE email = \"" + email + "\";";
		ResultSet rset = stmt.executeQuery(strSelect);
	       
	        while(rset.next()) {   // Move the cursor to the next row, return false if no more row
	        	
	           points = rset.getInt("loyaltyPoints");
	        }
	        conn.close();  
}
	
	return points;
}
public void setZeroLoyalty(String email) throws SQLException {
	
	try (
	         Connection conn = DriverManager.getConnection(
	               databaseURL,
	               dbUserName, dbPassword);  
	 
	         Statement stmt = conn.createStatement();
			
	)  {
	
		String strSelect = "update customers SET loyaltyPoints = " + 0 + " where email = \"" + email + "\";"; 
		stmt.executeUpdate(strSelect);
	       
	   
	        conn.close();  
}
	
}
public int updateLoyalty(String email, double dollars) throws SQLException {
	int points = (int)((dollars * 100) + getLoyalty(email));
	try (
	         Connection conn = DriverManager.getConnection(
	               databaseURL,
	               dbUserName, dbPassword);  
	 
	         Statement stmt = conn.createStatement();
			
	)  {
	
		String strSelect = "update customers SET loyaltyPoints = " + points + " where email = \"" + email + "\";"; 
		stmt.executeUpdate(strSelect);
	       
	   
	        conn.close();  
}
	
	return points;
}

public void updateStock(int videoID, int quant) throws SQLException{
	int stockToBe = getStock(videoID) - quant;
	try (
	         Connection conn = DriverManager.getConnection(
	               databaseURL,
	               dbUserName, dbPassword);  
	 
	         Statement stmt = conn.createStatement();
	)  {
	
		String strSelect = "update movies set stock = " + stockToBe + " WHERE videoID = " + videoID + ";";
		
		stmt.executeUpdate(strSelect);
	      
	        conn.close();
	
	}
}

//Removes an existing orderItem
public void removeOrderItem() throws SQLException {
//TODO	
}
	


/*MANAGER related functions.-----------*/
public void removeVideo(int videoID) throws SQLException {
	try (
	         Connection conn = DriverManager.getConnection(
	               databaseURL,
	               dbUserName, dbPassword);  
	 
	         Statement stmt = conn.createStatement();
	)  {
	
		String strSelect = "DELETE from movies where videoID = " + videoID + ";";
		stmt.executeUpdate(strSelect);
	      
	        conn.close();
	}	
}

public void addVideo(String title, int year, String introduction, String directors, String producers, int stock) throws SQLException {
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
	

public ArrayList<OrderItem> getOrderItemsByOrderID(int orderID) throws SQLException {
	ArrayList<OrderItem> output = new ArrayList<OrderItem>();

	try (
	        Connection conn = DriverManager.getConnection(
	              databaseURL,
	              dbUserName, dbPassword);  

	        Statement stmt = conn.createStatement();
	)  {

		String strSelect = "SELECT movies.title, movies.year, orderItems.quantity, orderItems.period, orderItems.videoID, orderItems.orderID, customerOrders.period, customerOrders.email FROM orderItems INNER JOIN movies ON orderItems.videoID = movies.videoID INNER JOIN customerOrders ON customerOrders.orderID = orderItems.orderID WHERE customerOrders.orderID = " + orderID + ";";
		ResultSet rset = stmt.executeQuery(strSelect);
		 int rowCount = 0;
	     while(rset.next()) {   // Move the cursor to the next row, return false if no more row
	        String email = rset.getString("email");
	        int videoID = rset.getInt("videoID");
	        int year = rset.getInt("year");
	        String title = rset.getString("title");
	        int quantity = rset.getInt("quantity");
	        int period = rset.getInt("period");
	        
	        output.add(new OrderItem(title, year, quantity, orderID, videoID, period, email));
	      
	        ++rowCount;
	     }
	     
	       conn.close();
	   
	}

	return output;
}



public ArrayList<OrderItem> getCurrentOrderItemsByEmail(String email) throws SQLException {
	
	ArrayList<OrderItem> output = new ArrayList<OrderItem>();

	try (
	        Connection conn = DriverManager.getConnection(
	              databaseURL,
	              dbUserName, dbPassword);  

	        Statement stmt = conn.createStatement();
	)  {

		String strSelect = "SELECT movies.title, movies.year, orderItems.quantity, orderItems.period, orderItems.videoID, orderItems.orderID, customerOrders.period, customerOrders.email FROM orderItems INNER JOIN movies ON orderItems.videoID = movies.videoID INNER JOIN customerOrders ON customerOrders.orderID = orderItems.orderID WHERE customerOrders.email = \"" + email + "\" AND customerOrders.status = \"UNPAID\";";
		ResultSet rset = stmt.executeQuery(strSelect);
		 int rowCount = 0;
	     while(rset.next()) {   // Move the cursor to the next row, return false if no more row
	        int orderID = rset.getInt("orderID");
	        int videoID = rset.getInt("videoID");
	        int year = rset.getInt("year");
	        String title = rset.getString("title");
	        int quantity = rset.getInt("quantity");
	        int period = rset.getInt("period");
	        
	        output.add(new OrderItem(title, year, quantity, orderID, videoID, period, email));
	      
	        ++rowCount;
	     }
	     
	       conn.close();
	   
	}

	return output;
}



//Scan through all current orders that are paid for. Retrieve the list of all orderItems from those orders, because they need to be shipped.
public ArrayList<OrderItem> getToBeOrderedList() throws SQLException {

ArrayList<OrderItem> output = new ArrayList<OrderItem>();

try (
        Connection conn = DriverManager.getConnection(
              databaseURL,
              dbUserName, dbPassword);  

        Statement stmt = conn.createStatement();
)  {

	String strSelect = "SELECT movies.title, movies.year, orderItems.quantity, orderItems.period, orderItems.videoID, orderItems.orderID, customerOrders.email FROM orderItems INNER JOIN movies ON orderItems.videoID = movies.videoID INNER JOIN customerOrders ON customerOrders.orderID = orderItems.orderID WHERE customerOrders.status = \"PAID\";";
	ResultSet rset = stmt.executeQuery(strSelect);
	 int rowCount = 0;
     while(rset.next()) {   // Move the cursor to the next row, return false if no more row
        String email = rset.getString("email");
        int orderID = rset.getInt("orderID");
        int videoID = rset.getInt("videoID");
        int year = rset.getInt("year");
        String title = rset.getString("title");
        int quantity = rset.getInt("quantity");
        int period = rset.getInt("period");
        output.add(new OrderItem(title, year, quantity, orderID, videoID, period, email));
      
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
	try (
	         Connection conn = DriverManager.getConnection(
	               databaseURL,
	               dbUserName, dbPassword);  
	 
	         Statement stmt = conn.createStatement();
	)  {
	
		String strSelect = "update customerOrders set status = \"PAID\" where orderID = " + orderID + ";";
		stmt.executeUpdate(strSelect);
	      
	        conn.close();
	    
}
	
	
	
}

public void updateOrderStatusShipped(int orderID) throws SQLException {
	//TODO
		try (
		         Connection conn = DriverManager.getConnection(
		               databaseURL,
		               dbUserName, dbPassword);  
		 
		         Statement stmt = conn.createStatement();
		)  {
		
			String strSelect = "update customerOrders set status = \"SHIPPED\" where orderID = " + orderID + ";";
			stmt.executeUpdate(strSelect);
		      
		        conn.close();
		    
	}
		
		
		
	}


/*---------------------------------------------------------------------------------------------*/	
	
	
/*Untested*/
public void addManager(String email, String password) throws SQLException {
	try (
	         Connection conn = DriverManager.getConnection(
	               databaseURL,
	               dbUserName, dbPassword);  
	 
	         Statement stmt = conn.createStatement();
	)  {
		String strSelect = "insert into managers values (" + "\'" + email + "\', " + "\"" + password + "\");";
		System.out.println(strSelect);

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
	
		String strSelect = "insert into customers (email, password, loyaltypoints) values (" + "\"" + email + "\", " + "\"" + password + "\", " + 0 + ");";
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

public boolean correctCustomerPassword(String email, String password) throws SQLException {
	try (
	         Connection conn = DriverManager.getConnection(
	               databaseURL,
	               dbUserName, dbPassword);  
	 
	         Statement stmt = conn.createStatement();
	)  {
	
		String strSelect = "select email from customers WHERE email = " + "\"" + email + "\" AND password = \"" + password + "\";";
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

public boolean correctManagerPassword(String email, String password) throws SQLException {
	try (
	         Connection conn = DriverManager.getConnection(
	               databaseURL,
	               dbUserName, dbPassword);  
	 
	         Statement stmt = conn.createStatement();
	)  {
	
		String strSelect = "select email from managers WHERE email = " + "\"" + email + "\" AND password = \"" + password + "\";";
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

public int getNumPeriod(int orderID, int period) throws SQLException {
	
	int rowCount = 0;
	try (
	         Connection conn = DriverManager.getConnection(
	               databaseURL,
	               dbUserName, dbPassword);  
	 
	         Statement stmt = conn.createStatement();
			
	)  {
	
		String strSelect = "select * from orderItems WHERE orderID = " + orderID + " AND period = " + period + ";";
		ResultSet rset = stmt.executeQuery(strSelect);
	       
	        while(rset.next()) {   // Move the cursor to the next row, return false if no more row
	      
	           ++rowCount;
	        }
	        conn.close();  
}
	return rowCount;
}

public boolean enoughStock(int videoID, int quantity) throws SQLException {
	int quant = 0;
	try (
	         Connection conn = DriverManager.getConnection(
	               databaseURL,
	               dbUserName, dbPassword);  
	 
	         Statement stmt = conn.createStatement();
			
	)  {
	
		String strSelect = "select stock from movies WHERE videoID = " + videoID + ";";
		ResultSet rset = stmt.executeQuery(strSelect);
	       
	        while(rset.next()) {   // Move the cursor to the next row, return false if no more row
	      
	        	quant = rset.getInt("stock");
	           
	        }
	        conn.close();  
}
	if(quantity > quant) {
		return false;
	} else {
		return true;
	}

}
public int getNumItems(int orderID) throws SQLException {
	
	int rowCount = 0;
	try (
	         Connection conn = DriverManager.getConnection(
	               databaseURL,
	               dbUserName, dbPassword);  
	 
	         Statement stmt = conn.createStatement();
			
	)  {
	
		String strSelect = "select * from orderItems WHERE orderID = " + orderID + ";";
		ResultSet rset = stmt.executeQuery(strSelect);
	       
	        while(rset.next()) {   // Move the cursor to the next row, return false if no more row
	      
	           ++rowCount;
	        }
	        conn.close();  
}
	return rowCount;
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


