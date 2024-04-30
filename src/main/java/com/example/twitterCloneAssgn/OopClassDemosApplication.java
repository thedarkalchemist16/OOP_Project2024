package com.example.twitterCloneAssgn;

import com.example.twitterCloneAssgn.data.Posts;
import com.example.twitterCloneAssgn.data.UserDetails;
import com.example.twitterCloneAssgn.data.UserRepository;
import com.example.twitterCloneAssgn.data.dto.*;
import com.example.twitterCloneAssgn.exception.ErrorResponse;
import com.example.twitterCloneAssgn.exception.GlobalExceptionHandler;
import com.example.twitterCloneAssgn.service.CommentsService;
import com.example.twitterCloneAssgn.service.PostsService;
import com.example.twitterCloneAssgn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SpringBootApplication
@RestController
public class OopClassDemosApplication {

    @Autowired
    UserService userService;
    @Autowired
    PostsService postsService;
    @Autowired
    CommentsService commentsService;
    @Autowired
    private UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(OopClassDemosApplication.class, args);
    }


    @PostMapping("/login")
    public ResponseEntity userLogin(@RequestBody LoginDTO loginDTO) {

        try {
            String result = userService.existingUserLogin(loginDTO);
            return ResponseEntity.ok(result);
        }  catch (GlobalExceptionHandler.ResourceNotFoundException ex) {
            ErrorResponse errorResponse = new ErrorResponse("User does not exist");
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        } catch (GlobalExceptionHandler.BadRequestException ex) {
            ErrorResponse errorResponse = new ErrorResponse("Username/Password Incorrect");
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/signup")
    public ResponseEntity userSignup(@RequestBody UserDetailsDTO userDetailsDTO) {

        try {
            String result = userService.newUserSignUp(userDetailsDTO);
            return ResponseEntity.ok(result);
        } catch (GlobalExceptionHandler.BadRequestException ex) {
            ErrorResponse errorResponse = new ErrorResponse("Forbidden, Account already exists ");
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/user")
    public ResponseEntity getUser(@RequestParam Integer userID) {

        try {
            UserDetails userDetails = userService.findUserByUserID(userID);
            UserDetailsResponse response = UserDetailsResponse.builder()
                    .email(userDetails.getEmail())
                    .name(userDetails.getName())
                    .userID(userDetails.getUserID())
                    .build();
            return ResponseEntity.ok(response);
        } catch (GlobalExceptionHandler.ResourceNotFoundException ex) {
            ErrorResponse errorResponse = new ErrorResponse("User does not exist");
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/")
    public List<Posts> getUserFeed() {

        return postsService.getUserFeed();
    }

    @PostMapping("/post")
    public ResponseEntity createPost(@RequestBody CreatePostDTO createPost) {

        try {
            String result = postsService.createPost(createPost);
            return ResponseEntity.ok(result);

        } catch (GlobalExceptionHandler.ResourceNotFoundException ex) {
            ErrorResponse errorResponse = new ErrorResponse("User does not exist");
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/post")
    public ResponseEntity getPosts(@RequestParam int postID) throws Exception {

        try {
            Posts posts = postsService.findPostById(postID);
            return ResponseEntity.ok(posts);

        } catch (GlobalExceptionHandler.ResourceNotFoundException ex) {
            ErrorResponse errorResponse = new ErrorResponse("Post does not exist");
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        }

    }


    @PatchMapping("/post")
    public ResponseEntity updatePost(@RequestBody UpdatePostDTO postsDTO) {

        try{
            String result = postsService.updateExistingPost(postsDTO);
            return ResponseEntity.ok(result);
        } catch (GlobalExceptionHandler.ResourceNotFoundException ex) {
            ErrorResponse errorResponse = new ErrorResponse("Post does not exist");
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping("/post")
    public ResponseEntity deletePost(@RequestParam int postID) {
        try{
            String result = postsService.deletePost(postID);
            return ResponseEntity.ok(result);
        } catch (GlobalExceptionHandler.ResourceNotFoundException ex) {
            ErrorResponse errorResponse = new ErrorResponse("Post does not exist");
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/comment")
    public ResponseEntity createComment(@RequestBody CreateNewCommentDTO createNewCommentDTO) {
        try {
            String result= commentsService.createNewComment(createNewCommentDTO);
            return ResponseEntity.ok(result);
        } catch (GlobalExceptionHandler.ResourceNotFoundException ex) {
            ErrorResponse errorResponse = new ErrorResponse("Comment does not exist");
            return new ResponseEntity<>(errorResponse,HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/comment")
    public ResponseEntity getComment(@RequestParam int commentID) {
        try {
            String result= String.valueOf(commentsService.retrieveExistingComment(commentID));
            return ResponseEntity.ok(result);
        } catch (GlobalExceptionHandler.ResourceNotFoundException ex) {
            ErrorResponse errorResponse = new ErrorResponse("Comment does not exist");
            return new ResponseEntity<>(errorResponse,HttpStatus.NOT_FOUND);
        }
    }


    @PatchMapping("/comment")
    public ResponseEntity updateComment(@RequestBody UpdateCommentDTO updateCommentDTO) {
        try {
            String result= commentsService.editComment(updateCommentDTO);
            return ResponseEntity.ok(result);
        }

        catch (GlobalExceptionHandler.ResourceNotFoundException ex) {
            ErrorResponse errorResponse = new ErrorResponse("Comment does not exist");
            return new ResponseEntity<>(errorResponse,HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping("/comment")
    public ResponseEntity deleteComment(@RequestParam int commentID) {
        try {
            String result= commentsService.deleteExistingComment(commentID);
            return ResponseEntity.ok(result);
        }
        catch (GlobalExceptionHandler.ResourceNotFoundException ex) {
            ErrorResponse errorResponse = new ErrorResponse("Comment does not exist");
            return new ResponseEntity<>(errorResponse,HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/users")
    public List<UserDetails> getAllUsers(){
        List<UserDetails> userDetailsList= userService.getAllUsers();

        return userDetailsList;
    }


}