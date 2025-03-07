package com.edumingle.backend.dtos;

import com.edumingle.backend.models.Discussion;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CommunityDiscussionDTO extends Discussion {
    private int communityId;

    private int userInfoId;
}
