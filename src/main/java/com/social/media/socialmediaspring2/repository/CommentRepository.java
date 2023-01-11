package com.social.media.socialmediaspring2.repository;


import com.social.media.socialmediaspring2.model.Comment;
import com.social.media.socialmediaspring2.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
    List<Comment> findCommentsByPost(Post post);
}
