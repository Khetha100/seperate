package com.edumingle.backend.dtos;

import com.edumingle.backend.models.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostDTO extends Post {
    private Integer userInfoId;


}
