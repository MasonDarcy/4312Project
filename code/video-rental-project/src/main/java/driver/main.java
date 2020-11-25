package driver;

import java.sql.SQLException;

import database_access.VideoRentalDA;

public class main {

	 public static void main( String[] args ) throws SQLException
	    {
	    VideoRentalDA dba = new VideoRentalDA();
	    
	  //  dba.retrieveVideoInformationByName("Alien", 1979);
	   System.out.println(dba.customerExists("whoa"));
	 //  dba.addCustomer("masonsemail", "masonsPassword"); 
	    
	    }
	
}
