package com.social.media.socialmedia.service;



import com.social.media.socialmedia.model.User;
import com.social.media.socialmedia.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class UserService {
@Autowired
private UserRepository userRepository;


    public User register(User user) throws SQLException {
        // TODO add user validation (add to user crud getUserByEmail return null if not exist)
       ;
        if (userRepository.findByEmailIgnoreCase(user.getEmail()) != null) {
            throw new SQLException("there is a user with the same email exists");
        } else {
            userRepository.save(user);
        }
        return user;
    }

}
