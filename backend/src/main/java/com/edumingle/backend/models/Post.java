package com.edumingle.backend.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String imageUrl;
    private String name;
    private String description;
    private LocalDateTime date;

//    @OneToMany(mappedBy = "post")
//    private List<Reports> reports;

    @OneToMany(mappedBy = "post")
    private List<Comments> comments;

//    @OneToMany(mappedBy = "post")
//    private List<PostLikes> postLikes;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserInfo userInfo;


    //Required by admin
    private boolean reported;

    private Date reportedDate;
}
