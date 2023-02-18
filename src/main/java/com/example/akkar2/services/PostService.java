package com.example.akkar2.services;

import com.example.akkar2.entities.Post;
import com.example.akkar2.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PostService implements IPostService {
    @Autowired
    PostRepository PostRepo;
    @Override
    public Post addPost(Post p) {


        return PostRepo.save(p) ;
    }

    @Override
    public List<Post> retrieveAllPosts() {
        return PostRepo.findAll();
    }



    @Override
    public void removePost(int id) {
        PostRepo.deleteById(id);
    }



      @Override
        public Post updatePost(Post p) {
          return PostRepo.save(p);
      }

}
