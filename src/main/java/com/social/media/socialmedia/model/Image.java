package com.social.media.socialmedia.model;


import javax.persistence.*;
import java.sql.Blob;
import java.sql.SQLException;

@Entity
@Table(name = "post_images")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @ManyToOne
    @JoinColumn(name = "post_id")
    Post post;
    // TODO is this best practice
    @Lob
    @Column(name = "image")
    Blob img;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public Blob getImg() throws SQLException {
        return img;
    }

    public void setImg(Blob img) {
        this.img = img;
    }
}
