package com.example.twitterCloneAssgn.data;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class Comments {

    @Column
    private int commentID;

    @Column
    private String commentBody;
    @Embedded
    private CommentCreator  commentCreator;
    @Embeddable
    public static class CommentCreator{
        private int userID;
        private String name;
    }
}
