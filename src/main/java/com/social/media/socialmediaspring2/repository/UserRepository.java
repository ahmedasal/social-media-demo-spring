package com.social.media.socialmediaspring2.repository;


import com.social.media.socialmediaspring2.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    List<User> findByUsernameStartingWithIgnoreCase(String username);
    User findByEmailIgnoreCase(String email);
}
