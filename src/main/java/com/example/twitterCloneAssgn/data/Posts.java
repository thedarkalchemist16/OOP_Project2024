package com.example.twitterCloneAssgn.data;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Setter
@Getter
@Entity
@Table
@Embeddable
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Posts{

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int postId;

    @Column
    private String postBody;
    @Column
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date date;

    @OneToOne
    @JoinColumn(name ="commentID")
    private Comments comments;

}
