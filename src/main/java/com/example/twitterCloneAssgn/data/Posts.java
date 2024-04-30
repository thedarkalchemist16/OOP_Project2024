package com.example.twitterCloneAssgn.data;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@Entity
@Table
@Embeddable
public class Posts{

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int postId;

    @Column
    private String postBody;
    @Column
    private Date date;
    @Embedded
    private Comments comments;

}
