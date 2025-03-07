package com.edumingle.backend.repositories;

import com.edumingle.backend.models.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfileRepository extends JpaRepository<UserInfo, Integer> {
    Optional<UserInfo> findById(int userId);
}
