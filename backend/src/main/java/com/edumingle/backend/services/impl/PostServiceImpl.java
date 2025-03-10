package com.edumingle.backend.services.impl;

import com.edumingle.backend.dtos.PostDTO;
import com.edumingle.backend.models.Post;
import com.edumingle.backend.models.UserInfo;
import com.edumingle.backend.repositories.PostRepository;
import com.edumingle.backend.repositories.ReportRepository;
import com.edumingle.backend.repositories.UserInfoRepository;
import com.edumingle.backend.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final UserInfoRepository userInfoRepository;
    private final ReportRepository reportRepository;

    @Autowired
    public PostServiceImpl(PostRepository postRepository, UserInfoRepository userInfoRepository, ReportRepository reportRepository) {
        this.postRepository = postRepository;
        this.userInfoRepository = userInfoRepository;
        this.reportRepository = reportRepository;
    }

    @Override
    public List<Post> getPostService() {
        List<Post> myPosts = postRepository.findAll();
//        System.out.println(myPosts);
        return myPosts;
    }



    @Override
    public Post getSinglePostService(int postId) {
        Post myPost = postRepository.findById(postId).orElse(null);
        System.out.println(myPost);
        return myPost;
    }


    @Override
    public Post addPostService(PostDTO post) {
        System.out.println(post);
        UserInfo userInfo = userInfoRepository.findById( post.getUserInfoId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        Post newPost = new Post();
//        newPost.setPostLikes(new ArrayList<>());
        newPost.setDate(post.getDate());
        newPost.setComments(new ArrayList<>());
//        newPost.setReports(new ArrayList<>());
        newPost.setImageUrl(post.getImageUrl());
        newPost.setUserInfo(userInfo);
        newPost.setDescription(post.getDescription());
        newPost.setName(post.getName());

        return postRepository.save(newPost);
    }

    @Override
    public void deletePostService(Long postId) {
        reportRepository.deleteByPostId(postId);
        postRepository.deleteById(postId);
    }

    @Override
    public List<Post> searchPosts(String keyword) {
        return postRepository.searchPosts(keyword);
    }
}
