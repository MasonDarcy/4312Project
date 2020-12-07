package data_objects;

public class Rating {

	
public Rating(String criticName, String review, int videoID) {
	this.criticName = criticName;
	this.review = review;
	this.videoID = videoID;
}
	
private String criticName;
private String review;
private int videoID;

public String getCriticName() {
	return criticName;
}
public String getReview() {
	return review;
}
public int getVideoID() {
	return videoID;
}

	
}
