package org.example.greenspace;

import lombok.Data;
import lombok.Getter;

@Getter
@Data
public class Comment {
    private String username;
    private String comment;
    private int rating;

    public Comment(String username, String comment, int rating) {
        this.comment = comment;
        this.username = username;
        this.rating = rating;
    }
}
