package org.example.Social.App.Service;

import org.example.Social.App.Model.User;
import org.example.Social.App.Repository.IUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class UserService {

    @Autowired
    IUserRepo userRepo;

    public String createUser(User user) {
        //Check if user with same email and userName exists or not
        User check = userRepo.getByuserEmail(user.getUserEmail());

        if(check != null)
            return "User email already exists";
        check = userRepo.getByuserName(user.getUserName());

        if(check != null)
                return "User with user name already exists";

        userRepo.save(user);

        return "User created";
    }

    public Collection<User> getUsers() {
        return userRepo.findAll();
    }

    public String deleteUser(Integer id) {
        if(userRepo.findById(id).isEmpty())
            return "User with Id not found";
        userRepo.deleteById(id);

        return "User deleted";
    }
}
