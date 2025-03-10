package com.edumingle.backend.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.edumingle.backend.models.UserInfo;
import com.edumingle.backend.services.impl.ConnectionServiceImpl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.edumingle.backend.services.impl.ConnectionServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/api/v1/connections")
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE}, allowCredentials = "true")

public class ConnectionController {
    private final ConnectionServiceImpl connectionService;

    public ConnectionController(ConnectionServiceImpl connectionService) {
        this.connectionService = connectionService;
    }

    @PostMapping("/connect")
    public ResponseEntity<String> sendConnectionRequest(
            @RequestParam
            Integer senderId,
            @RequestParam
            Integer receiverId,
            HttpServletRequest request
    ) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            UserInfo userId = (UserInfo) session.getAttribute("user");
            System.out.println("Session ID: " + session.getId());
            System.out.println("User ID: " + userId);
        }

        boolean success = connectionService.sendConnectionRequest(senderId, receiverId);
        return success ? ResponseEntity.ok("Connection request sent")
                : ResponseEntity.badRequest().body("Failed to send request");
    }

    @PostMapping("/accept")
    public ResponseEntity<String> acceptConnectionRequest(
            @RequestParam Integer connectionId,
            HttpServletRequest request
    ) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            UserInfo userId = (UserInfo) session.getAttribute("user");
            System.out.println("Session ID: " + session.getId());
            System.out.println("User ID: " + userId);
        }

        boolean success = connectionService.acceptConnectionRequest(connectionId);
        return success ? ResponseEntity.ok("Connection accepted")
                : ResponseEntity.badRequest().body("Failed to accept connection");
    }

    @PostMapping("/reject")
    public ResponseEntity<String> rejectConnectionRequest(
            @RequestParam Integer connectionId,
            HttpServletRequest request
    ) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            UserInfo userId = (UserInfo) session.getAttribute("user");
            System.out.println("Session ID: " + session.getId());
            System.out.println("User ID: " + userId);
        }

        boolean success = connectionService.rejectConnectionRequest(connectionId);
        return success ? ResponseEntity.ok("Connection rejected")
                : ResponseEntity.badRequest().body("Failed to reject connection");
    }

    @GetMapping("{userId}/connections")
    public ResponseEntity<List<UserInfo>> getConnections(
            @PathVariable Integer userId,
            HttpServletRequest request
    ){
        HttpSession session = request.getSession(false);
        if (session != null) {
            UserInfo user = (UserInfo) session.getAttribute("user");
            System.out.println("Session ID: " + session.getId());
            System.out.println("User ID: " + user);
        }

        List<UserInfo> connections = connectionService.getUserConnections(userId);
        return ResponseEntity.ok(connections);
    }
}
