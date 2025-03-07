package com.edumingle.backend.controllers;

import com.edumingle.backend.models.Reports;
import com.edumingle.backend.models.UserInfo;
import com.edumingle.backend.services.impl.ReportServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE}, allowCredentials = "true")
@RequestMapping("/api/v1/reports")
public class ReportController {

    private final ReportServiceImpl reportService;

    @Autowired
    public ReportController(ReportServiceImpl reportService) {
        this.reportService = reportService;
    }

    @PostMapping
    public ResponseEntity<Reports> createReport(
        @RequestBody Reports report,
        HttpServletRequest request
    ) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            UserInfo userId = (UserInfo) session.getAttribute("user");
            System.out.println("Session ID: " + session.getId());
            System.out.println("User ID: " + userId);
        }

        Reports savedReport = reportService.createReport(report);
        return new ResponseEntity<>(savedReport, HttpStatus.CREATED);
    }
}
