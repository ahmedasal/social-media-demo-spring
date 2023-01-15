package com.social.media.socialmedia.repository;


import com.social.media.socialmedia.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Integer> {
    Image findImageById(int id);
}
