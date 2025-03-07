package com.edumingle.backend.services.impl;

import com.edumingle.backend.models.ConnectionStatus;
import com.edumingle.backend.models.Connections;
import com.edumingle.backend.models.UserInfo;
import com.edumingle.backend.repositories.ConnectionRepository;
import com.edumingle.backend.repositories.UserInfoRepository;
import com.edumingle.backend.services.ConnectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConnectionServiceImpl implements ConnectionService {
    private final ConnectionRepository connectionRepository;
    private final UserInfoRepository userInfoRepository;

    @Autowired
    public ConnectionServiceImpl(ConnectionRepository connectionRepository, UserInfoRepository userInfoRepository) {
        this.connectionRepository = connectionRepository;
        this.userInfoRepository = userInfoRepository;
    }

    @Override
    public boolean sendConnectionRequest(Integer senderId, Integer receiverId) {
        if (connectionRepository.existsByFollowerIdAndFollowedId(senderId, receiverId)) {
            throw new Error("Connection already exists");
        }

//        UserInfo sender = userInfoRepository.findById(senderId)
//                .orElseThrow(() -> new IllegalArgumentException("Sender not found"));
//
//        UserInfo receiver = userInfoRepository.findById(receiverId)
//                .orElseThrow(() -> new IllegalArgumentException("Receiver not found"));



        Connections connection = new Connections();
        connection.setFollower(new UserInfo(senderId));
        connection.setFollowed(new UserInfo(receiverId));
        connection.setStatus(ConnectionStatus.PENDING);

        connectionRepository.save(connection);

        return true;
    }

    @Override
    public boolean acceptConnectionRequest(Integer connectionId) {
        Optional<Connections> connection = connectionRepository.findById(connectionId);
        if (connection.get().getStatus() == ConnectionStatus.PENDING) {
            connection.get().setStatus(ConnectionStatus.ACCEPTED);
            return false;
        }
        return true;
    }

    @Override
    public boolean rejectConnectionRequest(Integer connectionId) {
        Optional<Connections> connection = connectionRepository.findById(connectionId);
        if (connection.get().getStatus() == ConnectionStatus.PENDING) {
            connection.get().setStatus(ConnectionStatus.REJECTED);
            return false;
        }
        return true;
    }

    @Override
    public List<UserInfo> getUserConnections(Integer userId) {
        return connectionRepository.findAcceptedConnectionsByUserId(userId);
    }
}
