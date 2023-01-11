package com.social.media.socialmediaspring2.controller;

import com.social.media.socialmediaspring2.model.Post;
import com.social.media.socialmediaspring2.service.WallService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/wall")
public class WallController {
    @PersistenceContext
    private EntityManager em;
    @Autowired
    private WallService wallService;
   @RequestMapping(method = RequestMethod.GET)
    public String  getWall(Model model){
        List<Post> wallPosts = this.wallService.getWallPosts(em,45,0,5);
        System.out.println(wallPosts.get(0).getComments().get(0) );
        System.out.println(wallPosts);
        model.addAttribute("wallPosts",wallPosts);
        return "wallPage";
    }
}
