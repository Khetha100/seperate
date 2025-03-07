package com.edumingle.backend.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class CommunityMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String content;
    private LocalDateTime date;
    private Long senderId;
    private Long communityId;
    private String SubscriptionChannel;

//    @Column(insertable=false, updatable=false)
//    private Integer discussionId;

    @ManyToOne
    @JoinColumn(name = "discussionId")
    private Discussion discussion;

    public CommunityMessage(String s) {
    }

    public CommunityMessage() {

    }
}
