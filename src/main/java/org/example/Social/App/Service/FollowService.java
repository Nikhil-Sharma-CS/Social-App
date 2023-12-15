package org.example.Social.App.Service;

import org.example.Social.App.Model.Follow;
import org.example.Social.App.Model.User;
import org.example.Social.App.Repository.IFollowRepo;
import org.example.Social.App.Repository.IUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FollowService {

    @Autowired
    IFollowRepo followRepo;

    @Autowired
    IUserRepo userRepo;

    //Follow the user
    public String followUser(Follow follow) {
        //Firstly check if both users are valid or not
        User currentUserFollower = getUserById(follow.getCurrentUserFollower().getUserId());
        User currentUser = getUserById(follow.getCurrentUser().getUserId());

        if(currentUserFollower == null || currentUser == null)
        {
            return "Invalid user ids";
        }

        Follow alreadyFollow = followRepo.findByCurrentUserAndCurrentUserFollower(currentUser, currentUserFollower);

        if(alreadyFollow != null)
            return "You are already following this user";

        follow.setCurrentUser(currentUser);
        follow.setCurrentUserFollower(currentUserFollower);

        followRepo.save(follow);

        return "User Followed";
    }


    //Unfollow a user
    public String unfollowUser(Integer followerId, Integer followingId) {
        User currentUserFollower = getUserById(followerId);
        User currentUser = getUserById(followingId);

        if(currentUser == null || currentUserFollower == null)
            return "User Id is invalid";

        Follow alreadyFollow = followRepo.findByCurrentUserAndCurrentUserFollower(currentUser,currentUserFollower);

        if(alreadyFollow == null)
            return "You do not follow this user";

        followRepo.delete(alreadyFollow);

        return "User unfollowed";
    }


    //Retrieve user from ID
    public User getUserById(Integer id)
    {
        return  userRepo.findById(id).orElse(null);
    }
}
