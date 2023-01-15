package com.social.media.socialmedia.model;


import javax.persistence.*;

import java.util.Date;


//TODO many to many.
@Entity
@Table(name = "friendship_request")
public class Friendship {
    @Id
    int id;
    @ManyToOne
    @JoinColumn(name ="user1")
    User user1;
    @ManyToOne
    @JoinColumn(name ="user2")
    User user2;

    @Column(name = "create_time")
    Date CreateDate;

    public Friendship() {
    }

}
