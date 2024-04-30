package com.example.twitterCloneAssgn.service;

import com.example.twitterCloneAssgn.data.Posts;
import com.example.twitterCloneAssgn.data.PostsRepository;
import com.example.twitterCloneAssgn.data.dto.CreatePostDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    //getting all posts
    public List<Posts> getAllPosts() {
        List<Posts> listOfPosts= new ArrayList<Posts>();
        postsRepository.findAll().forEach(posts -> listOfPosts.add(posts));
        return listOfPosts;
    }
    private String createPost(CreatePostDTO createPostDTO) {
        if(postsRepository.findById(createPostDTO.getUserID()).isPresent()) {
            Posts posts=new Posts();

            posts.setPostBody(createPostDTO.getPostBody());
            posts.setPostId(createPostDTO.getUserID());
            return "Post created successfully";
        }

        else
            return "user does not exist";

    }
    //retrieve an existing post
    private Posts findPostById(Integer postId) {
        return postsRepository.findById(postId).get();
    }

    //create/update an existing post
    public void updatePost(Posts post) {
        postsRepository.save(post);
    }

    //delete a post
    public void deletePost(Integer postId) {
        postsRepository.deleteById(postId);
    }

}
