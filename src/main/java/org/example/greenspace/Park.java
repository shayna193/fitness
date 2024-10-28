package org.example.greenspace;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Data
public class Park {
    private String name;
    private String location;
    @Setter
    private int rating;

    public Park(String name, String location, int rating) {
        this.name = name;
        this.location = location;
        this.rating = rating;
    }

}
