package data_objects;

import java.time.Duration;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

import com.google.protobuf.TextFormat.ParseException;

public class CustomerOrder {

	
public CustomerOrder(int orderID, String dateOrdered, String shippingDestination, int period, String status,
			String returnTrackingInformation, String email) {
		this.orderID = orderID;
		this.dateOrdered = dateOrdered;
		this.shippingDestination = shippingDestination;
		this.period = period;
		this.status = status;
		this.returnTrackingInformation = returnTrackingInformation;
		this.email = email;
	}
private int orderID;
private String dateOrdered;
private String shippingDestination;
private int period;
private String status;
private String returnTrackingInformation;
private String email;

public int getOrderID() {
	return orderID;
}
public String getDateOrdered() {
	return dateOrdered;
}
public String getShippingDestination() {
	return shippingDestination;
}
public int getPeriod() {
	return period;
}
public String getStatus() {
	return status;
}
public String getReturnTrackingInformation() {
	return returnTrackingInformation;
}
public String getEmail() {
	return email;
}

public int calculateLateFees() throws ParseException {
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	LocalDate date1 = LocalDate.now();
	LocalDate date2 = LocalDate.parse(dateOrdered).plusDays(this.period);


    int diff = (int) Duration.between(date2.atStartOfDay(), date1.atStartOfDay()).toDays();
    System.out.println("diff = " + diff);
	//long daysBetween = DAYS.between(date1, date2);
//	
//
	return diff > 0 ? (int) (diff * 3) : 0;
//	return diff;

}

}
