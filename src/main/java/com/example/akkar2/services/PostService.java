package com.example.akkar2.services;

import com.example.akkar2.entities.Post;
import com.example.akkar2.entities.User;
import com.example.akkar2.repository.PostRepository;
import com.example.akkar2.repository.UserRepository;
import com.example.akkar2.services.Exceptions.NotUserPostFoundException;
import com.example.akkar2.services.Exceptions.PostNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class PostService  {

    UserRepository userDao;
    PostRepository postRepository;

    public Optional<Post> selectPost(Long id){
        Boolean exists = postRepository.selectExistsId(id);        if(!exists){
            throw new PostNotFoundException(
                    "Post not found!"
            );
        }
        return postRepository.findById(id);
    }
    public Iterable<Post> showAllPostsDesc(){
        return postRepository.findByIdDesc();
    }
    public Iterable<Post> findMostLikedPosts(){

        return postRepository.findPostByVoteCountDesc();
    }
    public List<Post> getPostsByUser(User uid){
        List<Post> posts = postRepository.findPostByUser(uid);
        return posts;
    }

    public Iterable<Post> SearchPostsByName(String name){
        return postRepository.searchByPostTitle(name);

    }


    public List<Post> findPostByUser(User user){

        return  postRepository.findPostByUser(user);
    }


    public void updatePost(Long postId, Long uid){

        Post post = postRepository.findPostByPostId(postId);
        User user = userDao.findUserById(uid);

        if(post.getUser() == user) {
            post.setPostTitle(post.getPostTitle());
            post.setPostDescription(post.getPostDescription());
            postRepository.save(post);
        } else {
            throw new NotUserPostFoundException(
                    "You can't edit this post!"
            );
        }

    }

    public void deletePost(Long postId, Long uid){
        User user = userDao.findUserById(uid);
        Post post = postRepository.findPostByPostId(postId);

        if(post.getUser() == user){
            postRepository.deleteById(postId);

        } else {
            throw new NotUserPostFoundException(
                    "Post does not belongs to user!"
            );
        }
    }


    public Post createPost(Post post, Long uid){
        User user = userDao.findUserById(uid);
        String url = "localhost:8080/post/"+ post.getPostUrl();
        post.setPostUrl( url);
        post.setUser(user);
        return postRepository.save(post);
    }



    public Post giveALike(Long postId, Long userId) {
        Post post = postRepository.findPostByPostId(postId);
        User user = userDao.findUserById(userId);
        if (post.getLikedBy().contains(user)) {
            // User has already liked the post, so remove the like
            post.getLikedBy().remove(user);
            post.setVoteCount(post.getVoteCount() - 1);
        } else {
            // User has not yet liked the post, so add the like
            post.getLikedBy().add(user);
            post.setVoteCount(post.getVoteCount() + 1);
        }

        postRepository.save(post);
        return post;
    }
    public Post giveADisLike(Long postId, Long userId){
        Post post = postRepository.findPostByPostId(postId);
        User user = userDao.findUserById(userId);

        if (post.getDislikedBy().contains(user)) {
            // User has already disliked the post, so remove the dislike
            post.getDislikedBy().remove(user);
            post.setVoteCount(post.getVoteCount() + 1);
        } else if (post.getLikedBy().contains(user)){
            // User has not yet liked the post, so add the like
            post.getLikedBy().remove(user);
            post.getDislikedBy().add(user);
            post.setVoteCount(post.getVoteCount() - 1);
        } else {post.getDislikedBy().add(user);
            post.setVoteCount(post.getVoteCount() - 1);}

        postRepository.save(post);
        return post;
    }
    public List<String> getUsersLikedPost(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new NotFoundException("Post not found"));
        List<User> likedBy = post.getLikedBy();
        List<String> userNames = new ArrayList<>();
        for (User user : likedBy) {
            userNames.add(user.getFirstname()+user.getLastname());
        }
        return userNames;
    }

    public List<String> getUsersDislikedPost(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new NotFoundException("Post not found"));
        List<User> likedBy = post.getDislikedBy();
        List<String> userNames = new ArrayList<>();
        for (User user : likedBy) {
            userNames.add(user.getFirstname()+user.getLastname());
        }
        return userNames;
    }

    public  Iterable<Post> showPostsByCategory(String category){
        return postRepository.findPostByCategory(category);

    }


}
