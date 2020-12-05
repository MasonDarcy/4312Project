package data_objects;

public class OrderItem {

public OrderItem(String title, int year, int quantity, int orderID, int videoID, int period, String email) {
	this.orderID = orderID;
	this.videoID = videoID;
	this.quantity = quantity;
	this.year = year;
	this.email = email;
	this.title = title;
	this.period = period;
}
	
private int orderID;
private int videoID;
private int quantity;
private int year;
private int period;
private String title;
private String email;

public int getOrderID() {
	return orderID;
}

public int getVideoID() {
	return videoID;
}

public int getQuantity() {
	return quantity;
}

public int getYear() {
	return year;
}

public String getTitle() {
	return title;
}

public String getEmail() {
	return email;
}

public int getPeriod() {
	return period;
}
@Override
public String toString() {
	return this.title + " " + this.year + " " + this.quantity + " "  + this.orderID + " " + this.videoID + " " + this.email;
}
}
