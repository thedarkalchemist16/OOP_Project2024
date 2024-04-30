package com.example.twitterCloneAssgn.data;

import com.example.twitterCloneAssgn.data.dto.FindByIDDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
public class UserDetails extends FindByIDDTO {

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
  @Embedded
  private Posts posts;
}
