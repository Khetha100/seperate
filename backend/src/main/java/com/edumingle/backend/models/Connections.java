package com.edumingle.backend.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Connections {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "followerId", insertable=false, updatable=false)
    private UserInfo follower;

    @ManyToOne
    @JoinColumn(name = "followedId", insertable=false, updatable=false)
    private UserInfo followed;

    @Enumerated(EnumType.STRING)
    private ConnectionStatus status;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
