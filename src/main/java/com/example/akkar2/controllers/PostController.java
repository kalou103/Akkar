package com.example.akkar2.controllers;

import com.example.akkar2.entities.Post;
import com.example.akkar2.entities.PostTopic;
import com.example.akkar2.entities.User;
import com.example.akkar2.repository.UserRepository;
import com.example.akkar2.services.PostService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("Post")
public class PostController {
      PostService postService;
      UserRepository userDao;

    @PostMapping("/add/{userid}")
    public Post addPost(@RequestBody Post post,@PathVariable("userid")  Long userid){

       return postService.createPost(post, userid);
    }



    @GetMapping("/findBy/user/{userid}")
    public List<Post> findByPostsByUserid(@PathVariable("userid")Long userid){

        User user = userDao.findUserById(userid);

        return postService.findPostByUser(user);
    }

    @GetMapping("/find/{name}")
    public Iterable<Post> findByName(@PathVariable("name") String name)
    {
        return postService.SearchPostsByName(name);
    }

    @GetMapping("/trending")
    public Iterable<Post> findTrendingPosts(){

        return postService.findMostLikedPosts();
    }


    @GetMapping("/user/{username}")
    public List<Post> searchPostByUser(@PathVariable("username")Long username){
        return postService.getPostsByUser(userDao.findUserById(username));
    }


    @GetMapping("/all")
    public Iterable<Post> showAllPostsDesc(){

        return postService.showAllPostsDesc();
    }

    @GetMapping("/get/category/{category}")
    public Iterable<Post> showPostsByCategories(@PathVariable("category") PostTopic category){

        return postService.showPostsByCategory(category);
    }

    @GetMapping("/get/post/{id}")
    public Optional<Post> getPostById(@PathVariable("id") Long id){


        return postService.selectPost(id);
    }


    @DeleteMapping("/delete/{postId}/{userid}")
    public void deletePostById(@PathVariable("postId")Long postId,@PathVariable("userid")Long userid){



        postService.deletePost(postId, userid);
    }

    @PutMapping ("/like/{id}/{userId}")
    public Post likePostById(@PathVariable("id") Long id, @PathVariable("userId") Long userid)
        {


        return postService.giveALike(id,userid);
    }
    @PutMapping("/dislike/{id}/{userId}")
    public Post dislikePostById(@PathVariable("id") Long id, @PathVariable("userId") Long userid){


        return postService.giveADisLike(id,userid);
    }
    @GetMapping("/get/likedBy/{id}")
    public List<String> getUsersLikedPost(@PathVariable("id") Long id){


        return postService.getUsersLikedPost(id);
    }
    @GetMapping("/get/dislikedBy/{id}")
    public List<String> getUsersDislikedPost(@PathVariable("id") Long id){


        return postService.getUsersDislikedPost(id);
    }
    }





