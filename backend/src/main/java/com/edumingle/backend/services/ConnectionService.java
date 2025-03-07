package com.edumingle.backend.services;

import com.edumingle.backend.models.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ConnectionService {

    boolean sendConnectionRequest(Integer senderId, Integer receiverId);

    boolean acceptConnectionRequest(Integer connectionId);

    boolean rejectConnectionRequest(Integer connectionId);

    List<UserInfo> getUserConnections(Integer userId);
}
