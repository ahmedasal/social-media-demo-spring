package com.social.media.socialmediaspring2.repository;


import com.social.media.socialmediaspring2.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Integer> {
    Post findPostById(int posId);

}
