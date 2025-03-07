package com.edumingle.backend.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Comments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer user_id;
    private String name;
    private String description;
    private Long numberOfLikes;
    private LocalDateTime timePosted;

    @ManyToOne
    @JoinColumn(name = "postId")
    private Post post;
}
