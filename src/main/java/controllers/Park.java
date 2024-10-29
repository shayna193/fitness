package controllers;

public class Park {
    private String name;
    private String location;
    private int rating;
    private boolean isApproved; // Approval status field

    // Constructors
    public Park(String name, String location, int rating, boolean isApproved) {
        this.name = name;
        this.location = location;
        this.rating = rating;
        this.isApproved = isApproved;
    }

    // Getter and Setters
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public int getRating() {
        return rating;
    }
    public void setRating(int rating) {
        this.rating = rating;
    }
    //Approval Status
    public boolean isApproved() {
        return isApproved;
    }
    public void setApproved(boolean isApproved) {
        this.isApproved = isApproved;
    }
}
