package org.example.greenspace;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Getter
@Data
public class Park {
    private String name;
    private String location;
    @Setter
    private String openingTimes;
    private List<String> amenities = new ArrayList<>();
    @Setter
    private double rating;
    private List<Integer> ratings = new ArrayList<>();
    private List<String> images = new ArrayList<>();
    private String mapEmbed;
    private String description;
    private boolean allowsDogs;
    private boolean allowsChildren;
    private boolean hasParking;

    public Park(String name, String location,String description, String openingTimes, String[] amenities, String[] images, boolean allowsDogs, boolean allowsChildren, boolean hasParking, String mapEmbed) {
        this.name = name;
        this.location = location;
        ratings.add(5);
        setAverageRating();
        this.openingTimes = openingTimes;
        this.mapEmbed = mapEmbed;
        this.allowsDogs = allowsDogs;
        this.allowsChildren = allowsChildren;
        this.hasParking = hasParking;
        this.description = description;
        this.images = List.of(images);

        setAmenities(List.of(amenities));
    }

    public void setAverageRating() {
        if (ratings.isEmpty()) {
            rating =  0.0;
        }
        double sum = 0.0;
        for (Integer rating : ratings) {
            sum += rating;
        }

        double average = sum / ratings.size();

        rating = Math.round(average * 4) / 4.0;
    }

    public void addRating(int rating) {
        ratings.add(rating);
        setAverageRating();
    }

    public void setAmenities(List<String> amenities) {
        this.amenities.addAll(amenities);

        if (allowsDogs) {
            this.amenities.add("Allows Dogs");
        }
        if (allowsChildren) {
            this.amenities.add("Kid Friendly");
        }
        if (hasParking) {
            this.amenities.add("Has Parking");
        }
    }
    public String getFirstImage() {
        return images.get(0);
    }

    public String getRandomImage() {
        Random random = new Random();
        int index = random.nextInt(images.size());
        return images.get(index);
    }


}
