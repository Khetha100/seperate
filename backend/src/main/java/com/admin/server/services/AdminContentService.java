package com.admin.server.services;

import com.admin.server.repositories.AdminContentRepository;
import com.edumingle.backend.models.Post;
import com.edumingle.backend.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminContentService {

    @Autowired
    private AdminContentRepository adminContentRepository;

    @Autowired
    private PostService postService;

    public List<Post> getReportedContent() {
        return adminContentRepository.findAllReportedContent();
    }

    public long getReportedContentCount() {
        return adminContentRepository.countReportedContent();
    }

    public void deleteContent(Long post) {
        postService.deletePostService(post);
    }
}
