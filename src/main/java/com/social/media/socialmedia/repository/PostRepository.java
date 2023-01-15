package com.social.media.socialmedia.repository;


import com.social.media.socialmedia.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Integer> {
    Post findPostById(int posId);

}
