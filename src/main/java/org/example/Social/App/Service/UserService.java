package org.example.Social.App.Service;

import org.example.Social.App.Model.User;
import org.example.Social.App.Repository.IUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    IUserRepo userRepo;

    @Autowired
    PasswordEncoder encode;

    //Create user
    public String createUser(User user) {
        //Check if user with same email and userName exists or not
        Optional<User> check = userRepo.getByuserEmail(user.getUserEmail());

        if(check.isPresent())
            return "User email already exists";
        check = Optional.ofNullable(userRepo.getByuserName(user.getUsername()));

        if(check.isPresent())
                return "User with user name already exists";
        user.setUserPassword(encode.encode(user.getUserPassword()));
        userRepo.save(user);

        return "User created";
    }


    //Delete user
    public String deleteUser(Integer id) {
        if(userRepo.findById(id).isEmpty())
            return "User with Id not found";
        userRepo.deleteById(id);

        return "User deleted";
    }

//    public String addUser(User user) {
//
//        userRepo.save(user);
//
//        return "User saved";
//    }


    //Get the list of users
    public List<User> getUsers() {
        return userRepo.findAll();
    }
}
