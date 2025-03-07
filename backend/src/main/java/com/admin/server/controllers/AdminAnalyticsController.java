package com.admin.server.controllers;

import com.admin.server.services.AdminAnalyticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/api/admin/analytics")
@CrossOrigin
public class AdminAnalyticsController {

    @Autowired
    private AdminAnalyticsService adminAnalyticsService;

    @GetMapping("/newUsers")
    public ResponseEntity<Long> getNewUsersCount(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate) {
        return ResponseEntity.ok(adminAnalyticsService.getNewUsersCount(startDate));
    }

    @GetMapping("/userRoles")
    public ResponseEntity<Map<String, Long>> getUserCountByRole() {
        return ResponseEntity.ok(adminAnalyticsService.getUserCountByRole());
    }
}
