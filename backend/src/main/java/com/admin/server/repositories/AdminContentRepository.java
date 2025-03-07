package com.admin.server.repositories;


import com.edumingle.backend.models.Post;
import com.edumingle.backend.repositories.PostRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface AdminContentRepository extends PostRepository {
    @Query("SELECT p FROM Post p WHERE p.reported = true ORDER BY p.reportedDate DESC")
    List<Post> findAllReportedContent();

    @Query("SELECT COUNT(p) FROM Post p WHERE p.reported = true")
    long countReportedContent();
}
