package com.example.twitterCloneAssgn.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostsRepository extends CrudRepository<Posts,Integer> {
    List<Posts> findAllByOrderByDateDesc();
}
