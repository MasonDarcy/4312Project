package data_objects;

public class Movie {
	
public Movie(int videoID, int year, String title, String category, String introduction, String directors, String producers, int stock) {
	this.videoID = videoID;
	this.year = year;
	this.title = title;
	this.category = category;
	this.introduction = introduction;
	this.directors = directors;
	this.producers = producers;
	this.stock = stock;
}



public int getVideoID() {
	return videoID;
}
public int getYear() {
	return year;
}
public String getTitle() {
	return title;
}
public String getCategory() {
	return category;
}
public String getIntroduction() {
	return introduction;
}
public String getDirectors() {
	return directors;
}
public String getProducers() {
	return producers;
}
public int getStock() {
	return stock;
}



private int videoID;
private int year;
private String title;
private String category;
private String introduction;
private String directors;
private String producers;
private int stock;

@Override
public String toString() {
	return "Movie [videoID=" + videoID + ", year=" + year + ", title=" + title + ", category=" + category
			+ ", introduction=" + introduction + ", directors=" + directors + ", producers=" + producers + ", stock="
			+ stock + "]";
}




}
