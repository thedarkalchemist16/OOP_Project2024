package com.example.twitterCloneAssgn.data.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserDetailsResponse {
    private int userID;
    private String name;
    private String email;
}