package com.example.twitterCloneAssgn.data;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
public class UserDetails {

  @Id
  @Column
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int userID;
  @Column
  private String name;
  @Column
  private String email;
  @Column
  private String password;
}
