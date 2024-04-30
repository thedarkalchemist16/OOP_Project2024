package com.example.twitterCloneAssgn.data.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateNewCommentDTO {
    private String commentBody;
    private int postID;
    private int userID;
}
