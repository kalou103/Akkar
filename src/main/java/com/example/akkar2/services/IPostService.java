package com.example.akkar2.services;

import com.example.akkar2.entities.Post;

import java.util.List;

public interface IPostService {

    Post addPost(Post p);
    List<Post> retrieveAllPosts();
    void removePost(int id);
    Post updatePost(Post p);
}
