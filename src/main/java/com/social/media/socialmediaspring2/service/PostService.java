package com.social.media.socialmediaspring2.service;


import com.social.media.socialmediaspring2.model.Image;
import com.social.media.socialmediaspring2.model.Post;
import com.social.media.socialmediaspring2.repository.ImageRepository;
import com.social.media.socialmediaspring2.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.persistence.EntityManager;
import java.util.List;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private ImageRepository imageRepository;

    public Boolean likedByMe(EntityManager em, int userId, int postId) {
        List resultSet = em.createQuery("select id from Like l where l.user.id= :userId and l.post.id = :postId").setParameter("userId", userId).setParameter("postId", postId).getResultList();
        return resultSet.size() > 0;
    }


    public Post writePost(Post post) {
        postRepository.save(post);
        return post;
    }

    public Post getPost(Integer id) {
        Post post = postRepository.findPostById(id);
        return post;
    }

    //TODO getPost (postCrud.getPost commentCrud.getPstComments
    public List getPostComments(int postId) {
        Post post = postRepository.findPostById(postId);
        return post.getComments().size() > 0 ? post.getComments() : null;
    }

    public Integer getLikesCount(EntityManager em, int postId) {
        List likes = em.createQuery("select post.likesCount from Post post where post.id = :post_id").setParameter("post_id", postId).getResultList();
        return likes.size() > 0 ? (Integer) likes.get(0) : null;
    }


    public String getUsername(EntityManager em, int id) {
        List users = em.createQuery("select user.username from User user where user.id = :id").setParameter("id", id).getResultList();
        return users.size() > 0 ? (String) users.get(0) : null;
    }

    public Image saveImage(Image img) {
        imageRepository.save(img);
        return img;
    }

    public Image getImage(EntityManager em, int imageId) {
        return imageRepository.findImageById(imageId);
    }

    public List<Image> getPostImagesIds(EntityManager em, int postId) {
        List<Image> images = em.createQuery("select image.id from Image image where image.post.id = :postId").setParameter("id", postId).getResultList();
        return images;
    }
}
