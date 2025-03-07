package com.edumingle.backend.repositories;

import com.edumingle.backend.models.Connections;
import com.edumingle.backend.models.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConnectionRepository extends JpaRepository<Connections, Integer> {

    @Query("SELECT u FROM UserInfo u JOIN Connections c ON (c.follower = u OR c.followed = u) WHERE (c.follower.id = :userId OR c.followed.id = :userId) AND c.status = 'ACCEPTED'")
    List<UserInfo> findAcceptedConnectionsByUserId(Integer userId);

    boolean existsByFollowerIdAndFollowedId(Integer senderId, Integer receiverId);
}
