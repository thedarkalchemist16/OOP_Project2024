package com.example.twitterCloneAssgn.service;

import com.example.twitterCloneAssgn.data.UserDetails;
import com.example.twitterCloneAssgn.data.UserRepository;
import com.example.twitterCloneAssgn.data.dto.FindByIDDTO;
import com.example.twitterCloneAssgn.data.dto.LoginDTO;
import com.example.twitterCloneAssgn.data.dto.UserDetailsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class UserService {
    //Scanner scanner = new Scanner(System.in);
    @Autowired
    private UserRepository userRepository;


    //user feed, login and signup ka logic is left
    //user login method
    public String existingUserLogin(LoginDTO loginDTO){

        if(userRepository.findByEmail(loginDTO.getEmail()).isEmpty()){
            return "User not found";
        }
        else {
            UserDetails userDetails1=userRepository.findByEmail(loginDTO.getEmail()).getFirst();
            if(userDetails1.getPassword().equals(loginDTO.getPassword())){
                return "User logged in";
            }
            else
                return "Wrong password";
        }
    }

    //method to create a new user
    public String newUserSignUp(UserDetailsDTO userDetailsDTO) {

            if(userRepository.findByEmail(userDetailsDTO.getEmail()).isEmpty())                      /*email doesn't exist already then*/
        {
            UserDetails userDetails=new UserDetails();
            userDetails.setEmail(userDetailsDTO.getEmail());
            userDetails.setName(userDetailsDTO.getName());
            userDetails.setPassword(userDetailsDTO.getPassword());

            userRepository.save(userDetails);
            return "Account Creation Successful\n";
        }
            else
                return "Forbidden, Account already exists\n";
    }

    //method for finding user details
    public ResponseEntity<?> findUserByUserID(Integer userID) {
        FindByIDDTO existingUserDTO=userRepository.findById(userID).orElseThrow(() -> new RuntimeException("User does not exist"));
        if(userRepository.findById(userID).isPresent())
        {
            existingUserDTO.getName();
            existingUserDTO.getEmail();
            existingUserDTO.getUserID();

        }
        return ResponseEntity.ok(existingUserDTO);
    }

    //method to get all users
    public List<UserDetails> getAllUsers() {
        List<UserDetails> listOfUserDetails=new ArrayList<UserDetails>();
        userRepository.findAll().forEach(listOfUserDetails::add);
        return listOfUserDetails;
    }


}
