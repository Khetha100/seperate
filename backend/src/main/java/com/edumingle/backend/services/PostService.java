package com.edumingle.backend.services;

import com.edumingle.backend.dtos.PostDTO;
import com.edumingle.backend.models.Comments;
import com.edumingle.backend.models.Post;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PostService {
    List<Post> getPostService();

    Post addPostService(PostDTO post);

    void deletePostService(Long post);

    List<Post> searchPosts(String keyword);
}
