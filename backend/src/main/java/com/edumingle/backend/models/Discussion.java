package com.edumingle.backend.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@Entity
public class Discussion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;
    private String description;
    private String subscriptionChannel;
    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "communityId")
    private Community community;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserInfo userInfo;

    @OneToMany(mappedBy = "discussion")
    private List<CommunityMessage> communityMessages;

}
