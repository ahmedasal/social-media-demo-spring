package com.social.media.socialmediaspring2.repository;


import com.social.media.socialmediaspring2.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Integer> {
    Image findImageById(int id);
}
