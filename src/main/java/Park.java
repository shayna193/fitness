package org.example.greenspace;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Data
public class Park {
    private String name;
    private String location;
    @Setter
    private double rating;
    private List<Integer> ratings = new ArrayList<>();
    private String mapEmbed;

    public Park(String name, String location, String mapEmbed) {
        this.name = name;
        this.location = location;
        ratings.add(5);
        setAverageRating();
        this.mapEmbed = mapEmbed;
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

}
