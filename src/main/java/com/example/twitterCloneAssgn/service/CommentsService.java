package com.example.twitterCloneAssgn.service;

import com.example.twitterCloneAssgn.data.*;
import com.example.twitterCloneAssgn.data.dto.CreateNewCommentDTO;
import com.example.twitterCloneAssgn.data.dto.UpdateCommentDTO;
import com.example.twitterCloneAssgn.exception.GlobalExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class CommentsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostsRepository postsRepository;

    @Autowired
    private CommentsRepository commentsRepository;

    public String createNewComment(CreateNewCommentDTO createNewCommentDTO) {
        SimpleDateFormat ft
                = new SimpleDateFormat("yyyy-MM-dd");

        String str = ft.format(new Date());
        if (userRepository.findById(createNewCommentDTO.getUserID()).isEmpty()){
            throw new GlobalExceptionHandler.ResourceNotFoundException("User");
        }
        else if(postsRepository.findById(createNewCommentDTO.getPostID()).isEmpty()){
            throw new GlobalExceptionHandler.ResourceNotFoundException("Post");
        }
        else {
            UserDetails user=userRepository.findById(createNewCommentDTO.getUserID()).get();
            Comments.CommentCreator commentCreator=new Comments.CommentCreator();
            commentCreator.setName(user.getName());
            commentCreator.setUserID(user.getUserID());
            Comments comments = Comments.builder()
                    .commentBody(createNewCommentDTO.getCommentBody())
                    .commentCreator(commentCreator)
                    .build();
            Posts posts = postsRepository.findById(createNewCommentDTO.getUserID()).get();
            posts.setComments(comments);
            postsRepository.save(posts);
            commentsRepository.save(comments);
            return "Comment created successfully";
        }

    }

    public Comments retrieveExistingComment(int commentID) {

        return commentsRepository.findById(commentID).orElseThrow(()->new GlobalExceptionHandler.ResourceNotFoundException("Post"));

    }

    public String editComment(UpdateCommentDTO updateCommentDTO) {
     if(commentsRepository.findById(updateCommentDTO.getCommentID()).isPresent())
     {
         Comments comments=Comments.builder()
                 .commentBody(updateCommentDTO.getCommentBody())
                 .commentID(updateCommentDTO.getCommentID()
                 ).build();
         return "Comment updated successfully";
     }
     else
         throw new GlobalExceptionHandler.ResourceNotFoundException("Comment");
    }

    public String deleteExistingComment(int commentID) {
        if(commentsRepository.findById(commentID).isPresent())
        {
            commentsRepository.deleteById(commentID);
            return "Comment deleted successfully";
        }
        else
            throw new GlobalExceptionHandler.ResourceNotFoundException("Comment");
    }
}
