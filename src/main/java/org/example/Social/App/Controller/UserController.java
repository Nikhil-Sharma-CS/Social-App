package org.example.Social.App.Controller;

import org.example.Social.App.Model.*;
import org.example.Social.App.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    PostService postService;

    @Autowired
    FollowService followService;

    @Autowired
    CommentService commentService;


    //User related APIs------------------------------------------------------------------------------------------

    @PostMapping("/create")
    public String createUser(@RequestBody User user)
    {
        return userService.createUser(user);
    }

    @GetMapping("/users")
    public Collection<User> getUsers()
    {
        return userService.getUsers();
    }

    @DeleteMapping("/delete/{Id}")
    public String deleteUser(@PathVariable Integer Id)
    {
        return userService.deleteUser(Id);
    }


    //Post related APIs------------------------------------------------------------------------------------------

    @PostMapping("/create-post")
    public String createPost(@RequestBody Post post, @RequestParam String userEmail,@RequestParam String userPassword)
    {
        return postService.createPost(post, userEmail, userPassword);
    }

    @DeleteMapping("/delete-post/{Id}")
    public String deletePost(@PathVariable Integer Id)
    {
        return postService.deletePost(Id);
    }

    @GetMapping("/see-post/{UserId}")
    public ResponseEntity<List<Post>> seePosts(@PathVariable Integer UserId, @RequestParam String userEmail, @RequestParam String userPassword)
    {
        return postService.seePosts(UserId, userEmail, userPassword);
    }


    //Follow user------------------------------------------------------------------------------------------------

    @PostMapping("/follow-user")
    public String followUser(@RequestBody Follow follow)
    {
        return followService.followUser(follow);
    }

    @DeleteMapping("/unfollow-user")
    public String unfollowUser(@RequestParam Integer followerId, @RequestParam Integer followingId) {
        return followService.unfollowUser(followerId, followingId);
    }


    //Comments on posts------------------------------------------------------------------------------------------

    @PostMapping("/comment-on-post")
    public String commentOnPost(@RequestBody Comment comment)
    {
        return commentService.commentOnPost(comment);
    }

    @DeleteMapping("/delete-comment")
    public String deleteComment(@RequestParam Integer CommentId)
    {
        return commentService.deleteComment(CommentId);
    }
}
