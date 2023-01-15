package com.social.media.socialmedia.controller;

import com.social.media.socialmedia.model.Image;
import com.social.media.socialmedia.model.Post;
import com.social.media.socialmedia.model.User;
import com.social.media.socialmedia.repository.ImageRepository;
import com.social.media.socialmedia.service.PostService;
import com.social.media.socialmedia.service.WallService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.serial.SerialBlob;

import com.social.media.socialmedia.util.ModifiedUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
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
    @Autowired
    private ImageRepository imageRepository;
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
    public ModelAndView addPost(@RequestParam("message") String postText, @RequestParam("photos") MultipartFile[] files, Model model) throws Exception {
        Post post = new Post();
        post.setUser(user);
        post.setPostDate(new Date());
        post.setPost(postText);
        postService.writePost(post);
        System.out.println(files.length);
        ////////////// for upload images ////////////////////
            for (int i = 0; i < files.length; i++) {
                Image img = new Image();
                InputStream inputStream = files[i].getInputStream();
                byte[] inputStreamByte = inputStream.readAllBytes();
                if (inputStreamByte.length > 0) {
                    Blob imgBlob = null;
                    try {
                        imgBlob = new SerialBlob(inputStream.readAllBytes());
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    img.setPost(post);
                    img.setImg(imgBlob);
                    imageRepository.save(img);
                }
            }
        //////////////######################////////////////////
        return new ModelAndView("redirect:/wall");
    }




    @GetMapping("/image")
    public void getImageAsByteArray(@RequestParam("id") String id, HttpServletResponse resp) throws IOException, SQLException {
        Image image = imageRepository.findImageById(Integer.parseInt(id));
        resp.setContentType("image/jpeg");
        resp.getOutputStream().write(image.getImg().getBinaryStream().readAllBytes());
        resp.getOutputStream().flush();
    }

}
