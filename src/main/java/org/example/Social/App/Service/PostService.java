package org.example.Social.App.Service;

import org.example.Social.App.Model.Post;
import org.example.Social.App.Model.User;
import org.example.Social.App.Repository.IFollowRepo;
import org.example.Social.App.Repository.IPostRepo;
import org.example.Social.App.Repository.IUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {
    
    @Autowired
    IPostRepo postRepo;

    @Autowired
    IUserRepo userRepo;

    @Autowired
    IFollowRepo followRepo;

    public String createPost(Post post, String email, String password) {
        //Firstly check if the user is valid or not
        User user = userRepo.findByuserEmail(email);

        if(user == null)
            return "User not found!!";

        if(!password.equals(user.getPassword()))
            return "Password invalid!!";


        post.setUser(user);
        postRepo.save(post);

        return "Post Created";
    }

    public String deletePost(Integer id) {
        postRepo.deleteById(id);
        return "Post Deleted";
    }

    public ResponseEntity<List<Post>> seePosts(Integer userId, String userEmail, String userPassword) {
        //Now lets check if our user exists or not
        User user = userRepo.findById(userId).orElse(null);
        if(user == null)
            return null;

        List<Post> list;

        //Grab our user who is trying to view posts
        User viewer = userRepo.findByuserEmail(userEmail);

        if(viewer == null)
            return ResponseEntity.notFound().build();

        if(!userPassword.equals(viewer.getPassword()))
                return ResponseEntity.unprocessableEntity().build();

        //Now lets check if our viewer follows our user or not
        if(followRepo.findByCurrentUserAndCurrentUserFollower(user, viewer) == null)
            return ResponseEntity.notFound().build();

        if(followRepo.findByCurrentUserAndCurrentUserFollower(viewer, user) != null)
        {
            return new ResponseEntity<>(postRepo.getAllByuser(user), HttpStatusCode.valueOf(207));
        }
        else
        {
            return new ResponseEntity<>(postRepo.getByUserAndIsPrivate(user, false), HttpStatusCode.valueOf(200));
        }
    }
}
