package driver;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import data_objects.Movie;
import data_objects.OrderItem;
import database_access.VideoRentalDA;

public class main {

	 public static void main( String[] args ) throws SQLException
	    {
	    VideoRentalDA dba = new VideoRentalDA();
	    
//	   ArrayList<OrderItem> testList = dba.getToBeOrderedList();
//	   
//	   for(int i = 0; i < testList.size(); i++) {
//		   System.out.println(testList.get(i).toString());
//	   }
	   
//	   ArrayList<Movie> testMovie = dba.retrieveVideoInformationByName("The Thing");
//	   
//	   for(int i = 0; i < testMovie.size(); i++) {
//		   System.out.println(testMovie.get(i).toString());
//	   }
	 //  dba.addCustomer("masonsemail", "masonsPassword"); 
	    
	    dba.removeOrder(1);
	    
	 
	    }
	
}
