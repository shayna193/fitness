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
    private List<Comment> comments = new ArrayList<>();
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

        Comment comment1 = new Comment("Zeus", "An alright place to twirl my moustache", 3);
        Comment comment2 = new Comment("Shayna", "A welcome relief from the mean streets of Bangalore", 5);
        Comment comment3 = new Comment("Karla", "Not much to steal here, only foliage", 1);
        Comment comment4 = new Comment("Steve", "Fantastic spot for some summer pics", 5);

        comments.add(comment3);
        comments.add(comment2);
        comments.add(comment4);
        comments.add(comment1);
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

    public void addComment(Comment comment) {
        comments.add(comment);
    }

    public Comment getRecentComment() {
        return comments.get(comments.size() - 1);
    }

    public Comment getSecondRecentComment() {
        return comments.get(comments.size() - 2);
    }

    public List<Comment> getAllComments() {return comments;}

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

    public String getAmenitiesAsString() {
        return String.join(", ", amenities);
    }


}
