package com.example.twitterCloneAssgn;

import com.example.twitterCloneAssgn.data.Comments;
import com.example.twitterCloneAssgn.data.Posts;
import com.example.twitterCloneAssgn.data.UserDetails;
import com.example.twitterCloneAssgn.data.dto.LoginDTO;
import com.example.twitterCloneAssgn.data.dto.UserDetailsDTO;
import com.example.twitterCloneAssgn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@RestController
public class OopClassDemosApplication {

    @Autowired
    UserService userService;

    public static void main(String[] args) {
        SpringApplication.run(OopClassDemosApplication.class, args);
    }


    @PostMapping("/login")
    public String userLogin(@RequestBody LoginDTO loginDTO) {

        return userService.existingUserLogin(loginDTO);
    }

    @PostMapping("/signup")
    public String userSignup(@RequestBody UserDetailsDTO userDetailsDTO) {

        return userService.newUserSignUp(userDetailsDTO);
    }

    @GetMapping("/user")
    public ResponseEntity<?> getUser(@RequestParam Integer userID) {

      return userService.findUserByUserID(userID);

    }

    @GetMapping("/")
    public List<Posts> getUserFeed() {

        List<Posts> userFeed = new ArrayList<Posts>();
        return userFeed;
    }

    @PostMapping("/post")
    public String createPost(@RequestBody String postBody, @RequestBody int userID) {

        return "Post Created";
    }

    @GetMapping("/post")
    public Posts getPosts(@RequestParam int postID) {

        Posts posts = new Posts();
        return posts;
    }


    @PatchMapping("/post")
    public String updatePost(@RequestBody String postBody, @RequestBody int postID) {

        return "userDetails";
    }


    @DeleteMapping("/post")
    public String deletePost(@RequestBody int postID) {

        return "Deleted Post";
    }

    @PostMapping("/comment")
    public String createComment(@RequestBody String commentBody, @RequestBody int postID, @RequestBody int userID) {

        return "Post Created";
    }

    @GetMapping("/comment")
    public Comments getComment(@RequestParam int commentID) {

        Comments comments = new Comments();
        return comments;
    }


    @PatchMapping("/comment")
    public String updateComment(@RequestBody String commentBody, @RequestBody int commentID) {

        return "userDetails";
    }


    @DeleteMapping("/comment")
    public String deleteComment(@RequestBody int commentID) {

        return "Deleted Comment";
    }

    @GetMapping("/users")
    public List<UserDetails> getAllUsers(){
        List<UserDetails> userDetailsList= new ArrayList<UserDetails>();

        return userDetailsList;
    }


}