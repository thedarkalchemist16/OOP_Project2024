package com.example.twitterCloneAssgn.data;

import jakarta.persistence.*;
import lombok.*;

@Table
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Comments {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int commentID;

    @Column
    private String commentBody;
    @Embedded
    private CommentCreator commentCreator;


    @Embeddable
    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CommentCreator{
        private int userID;
        private String name;
    }
}
