package com.admin.server.controllers;

import com.edumingle.backend.models.Donation;
import com.edumingle.backend.services.DonationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/adminAuth")
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE}, allowCredentials = "true")
public class AdminDonationController {

    private final DonationService donationService;

    @Autowired
    public AdminDonationController(DonationService donationService) {
        this.donationService = donationService;
    }

    @GetMapping("/donations")
    public ResponseEntity<List<Donation>> getAllDonations(HttpServletRequest request) {
        // Check if user is authenticated as admin
        HttpSession session = request.getSession(false);
//        if (session == null || session.getAttribute("adminUser") == null) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//        }

        List<Donation> donations = donationService.getAllDonations();
        return ResponseEntity.ok(donations);
    }

    @GetMapping("/donations/stats")
    public ResponseEntity<Map<String, Object>> getDonationStats(HttpServletRequest request) {
        // Check if user is authenticated as admin
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("adminUser") == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        return ResponseEntity.ok(donationService.getDonationStats());
    }
}
