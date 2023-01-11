package com.social.media.socialmediaspring2.service;


import com.social.media.socialmediaspring2.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.*;
import java.util.List;

@Service
public class WallService {
    @Autowired
    PostService postService;

    public List<Post>getWallPosts(EntityManager em, int userId, int offset, int noOfRows) {
        System.out.println("before query"); //for debugging
        List<Post> posts = em.createQuery("select post from Post post join Friendship f ON (f.user1.id = :user and post.user.id = f.user2.id) or (f.user2.id = :user and post.user.id = f.user1.id) ")
                .setParameter("user", userId)
                .setFirstResult(offset)
                .setMaxResults(noOfRows)
                .getResultList();
//        PostService postService = new PostService();
//        for (Post post : posts) {
//            post.setLikedByMe(postService.likedByMe(em, userId, post.getId()));
//        }
        System.out.println("after query"); //for debugging
        return posts;
    }
}
