package data_objects;

public class OrderItem {

public OrderItem(String title, int year, int quantity, int orderID, int videoID, String email) {
	this.orderID = orderID;
	this.videoID = videoID;
	this.quantity = quantity;
	this.year = year;
	this.email = email;
	this.title = title;
}
	
int orderID;
int videoID;
int quantity;
int year;
String title;
String email;

@Override
public String toString() {
	return this.title + " " + this.year + " " + this.quantity + " "  + this.orderID + " " + this.videoID + " " + this.email;
}
}
