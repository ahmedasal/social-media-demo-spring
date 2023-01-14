package com.social.media.socialmediaspring2.controller;

import com.social.media.socialmediaspring2.model.Post;
import com.social.media.socialmediaspring2.model.User;
import com.social.media.socialmediaspring2.service.PostService;
import com.social.media.socialmediaspring2.service.WallService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.social.media.socialmediaspring2.util.ModifiedUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;

@Controller

public class WallController {
    @PersistenceContext
    private EntityManager em;
    @Autowired
    private WallService wallService;
    @Autowired
    private PostService postService;
    private Object principal = null;
    private User user;




    @GetMapping("/wall")
    public String getWall(Model model) {
        principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        user = ((ModifiedUserDetails) principal).getUser();

        System.out.println(user);
        System.out.println(user.getId());
        List<Post> wallPosts = this.wallService.getWallPosts(em, user.getId(), 0, 5);

        model.addAttribute("username", user.getUsername());
        model.addAttribute("wallPosts", wallPosts);
        return "wallPage";
    }

    @PostMapping("/post")
    public ModelAndView addPost(@RequestParam("message")String postText, Model model){
        Post post = new Post();
        post.setUser(user);
        post.setPostDate(new Date());
        post.setPost(postText);
        postService.writePost(post);
        return new ModelAndView("redirect:/wall");
    }
}
