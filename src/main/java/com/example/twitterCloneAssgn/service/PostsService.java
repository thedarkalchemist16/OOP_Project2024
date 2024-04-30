package com.example.twitterCloneAssgn.service;

import com.example.twitterCloneAssgn.data.Comments;
import com.example.twitterCloneAssgn.data.Posts;
import com.example.twitterCloneAssgn.data.PostsRepository;
import com.example.twitterCloneAssgn.data.UserRepository;
import com.example.twitterCloneAssgn.data.dto.CreatePostDTO;
import com.example.twitterCloneAssgn.data.dto.UpdatePostDTO;
import com.example.twitterCloneAssgn.exception.GlobalExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PostsService {

    /*
     * implement logic and methods
     *
     *
     * */

    @Autowired
    private PostsRepository postsRepository;

    @Autowired
    private UserRepository userRepository;
    //getting all posts
    public List<Posts> getAllPosts() {
        List<Posts> listOfPosts= new ArrayList<Posts>();
        postsRepository.findAll().forEach(posts -> listOfPosts.add(posts));
        return listOfPosts;
    }

    public String createPost (CreatePostDTO createPost) {
        SimpleDateFormat ft
                = new SimpleDateFormat("yyyy-MM-dd");

        String str = ft.format(new Date());
        if (userRepository.findById(createPost.getUserID()).isEmpty()){
            throw new GlobalExceptionHandler.ResourceNotFoundException("User");
        }
        else{
            Posts posts = Posts.builder()
                    .postBody(createPost.getPostBody())
                    .date(new Date()) // tbd
                    .comments(new Comments())
                    .build();
            postsRepository.save(posts);
            return "Post created successfully";
        }

    }

    //retrieve an existing post
    public Posts findPostById(Integer postId) {

        return postsRepository.findById(postId).orElseThrow(() -> new GlobalExceptionHandler.ResourceNotFoundException("Post"));
    }

    //update an existing post
    public String updateExistingPost(UpdatePostDTO postsDTO) {

        if(postsRepository.findById(postsDTO.getPostId()).isEmpty()){
            throw new GlobalExceptionHandler.ResourceNotFoundException("Post");
        }
        else {
            Posts post = Posts.builder()
                    .postBody(postsDTO.getPostBody())
                    .postId(postsDTO.getPostId())
                    .build();
            postsRepository.save(post);
            return "Post edited successfully";
        }
    }

    //delete a post
    public String deletePost(Integer postId) {
        if(postsRepository.findById(postId).isEmpty()){
            throw new GlobalExceptionHandler.ResourceNotFoundException("Post");
        }
        else {
            postsRepository.deleteById(postId);
            return "Post deleted";
        }
    }

    public List<Posts> getUserFeed (){
        List<Posts> postsList = new ArrayList<Posts>();
        postsRepository.findAllByOrderByDateDesc().forEach(postsList::add);
        return postsList;
    }

}
