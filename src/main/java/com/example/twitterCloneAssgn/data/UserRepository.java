package com.example.twitterCloneAssgn.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<UserDetails, Integer> {

    public List<UserDetails> findByEmail(String email);
}
