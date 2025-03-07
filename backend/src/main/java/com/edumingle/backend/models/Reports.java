package com.edumingle.backend.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

import org.apache.logging.log4j.message.Message;

@Setter
@Getter
@Entity
public class Reports {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ReportReason reason;

    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime reviewedAt;

    @ManyToOne
    @JoinColumn(name = "userId")
    private UserInfo user;  // User who created the report

    @ManyToOne
    @JoinColumn(name = "postId")
    private Post post;
    
    @ManyToOne
    @JoinColumn(name = "commentId")
    private Comments comment;

    @ManyToOne
    @JoinColumn(name="message_id")
    private CommunityMessage communityMessage;

    @ManyToOne
    @JoinColumn(name="community_id")
    private Community community;

    @Enumerated(EnumType.STRING)
    private ReportStatus status;



}
