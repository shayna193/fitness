package java;

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
    //Other getters, setters and constructors
    @Setter
    private boolean approved;

    public Park(String name, String location, int rating, boolean approved) {
        this.name = name;
        this.location = location;
        this.rating = rating;
        this.approved = approved;
    }
    public boolean isApproved() {
        return approved;
    }

}
