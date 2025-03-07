package com.edumingle.backend.controllers;

import com.edumingle.backend.models.UserInfo;
import com.edumingle.backend.services.impl.UserInfoServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;

@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE}, allowCredentials = "true")
@RequestMapping("api/v1/profiles")
public class ProfileController {
    private final UserInfoServiceImpl userInfoService;

    public ProfileController(UserInfoServiceImpl userInfoService) {
        this.userInfoService = userInfoService;
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserInfo> updateProfile(
            @PathVariable int id,
            @RequestBody UserInfo userInfo,
            HttpServletRequest request,
            HttpServletResponse response
    ) throws NoSuchAlgorithmException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            UserInfo userId = (UserInfo) session.getAttribute("user");
            System.out.println("Session ID: " + session.getId());
            System.out.println("User ID: " + userId);
        }

        UserInfo updatedProfile = userInfoService.updateUserProfile(id, userInfo);
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:4200");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
        return updatedProfile != null ? ResponseEntity.ok(updatedProfile) : ResponseEntity.notFound().build();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserInfo> getUserProfileById(
            @PathVariable int userId,
            HttpServletResponse response
    ) {
        UserInfo userInfo = userInfoService.getUserProfileById(userId);
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:4200");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
        return userInfo != null ? ResponseEntity.ok(userInfo): ResponseEntity.notFound().build();

    }
}
