package com.example.twitterCloneAssgn.service;

import com.example.twitterCloneAssgn.data.UserDetails;
import com.example.twitterCloneAssgn.data.UserRepository;
import com.example.twitterCloneAssgn.data.dto.LoginDTO;
import com.example.twitterCloneAssgn.data.dto.UserDetailsDTO;
import com.example.twitterCloneAssgn.exception.GlobalExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    //user login method
    public String existingUserLogin(LoginDTO loginDTO){

        if(userRepository.findByEmail(loginDTO.getEmail()).isEmpty()){
            throw new GlobalExceptionHandler.ResourceNotFoundException("User");
        }
        else {
            UserDetails userDetails1=userRepository.findByEmail(loginDTO.getEmail()).get(0);
            if(userDetails1.getPassword().equals(loginDTO.getPassword())){
                return "User logged in";
            }
            else
                throw new GlobalExceptionHandler.BadRequestException("Username/Password Incorrect");
        }
    }

    //method to create a new user
    public String newUserSignUp(UserDetailsDTO userDetailsDTO) {

        /*email doesn't exist already then*/
        if(userRepository.findByEmail(userDetailsDTO.getEmail()).isEmpty()){
            UserDetails userDetails=new UserDetails();
            userDetails.setEmail(userDetailsDTO.getEmail());
            userDetails.setName(userDetailsDTO.getName());
            userDetails.setPassword(userDetailsDTO.getPassword());

            userRepository.save(userDetails);
            return "Account Creation Successful\n";
        }
        else
            throw new GlobalExceptionHandler.BadRequestException("Forbidden, Account already exists ");
    }

    //method to get all users
    public List<UserDetails> getAllUsers() {
        List<UserDetails> listOfUserDetails=new ArrayList<UserDetails>();
        userRepository.findAll().forEach(userDetails -> listOfUserDetails.add(userDetails));
        return listOfUserDetails;
    }

    //method for finding user details
    public UserDetails findUserByUserID(Integer userID) {

        return userRepository.findById(userID).orElseThrow(() -> new GlobalExceptionHandler.ResourceNotFoundException("User"));
    }

}
