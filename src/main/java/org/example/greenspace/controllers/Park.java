package org.example.greenspace.controllers;

import lombok.Getter;
import lombok.Setter;

public class Park {
    // Getter and Setters
    @Setter
    @Getter
    private String name;
    private String location;
    @Setter
    @Getter
    private int rating;
    private boolean isApproved; // Approval status field

    // Constructors
    public Park(String name, String location, int rating, boolean isApproved) {
        this.name = name;
        this.location = location;
        this.rating = rating;
        this.isApproved = isApproved;
    }

    //Approval Status
    public boolean isApproved() {
        return isApproved;
    }
    public void setApproved(boolean isApproved) {
        this.isApproved = isApproved;
    }
}
