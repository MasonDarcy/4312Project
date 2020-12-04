package data_objects;

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


}
