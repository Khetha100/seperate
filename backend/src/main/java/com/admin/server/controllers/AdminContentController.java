//package com.admin.server.controllers;
//
//import com.admin.server.services.AdminContentService;
//import com.edumingle.server.models.Contributions;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/admin/content")
//@CrossOrigin(origins = "http://localhost:4200")
//public class AdminContentController {
//
//    @Autowired
//    private AdminContentService adminContentService;
//
//    @GetMapping("/reported")
//    public ResponseEntity<List<Contributions>> getReportedContent() {
//        return ResponseEntity.ok(adminContentService.getReportedContent());
//    }
//
//    @GetMapping("/reported/count")
//    public ResponseEntity<Long> getReportedContentCount() {
//        return ResponseEntity.ok(adminContentService.getReportedContentCount());
//    }
//
//    @DeleteMapping("/{postId}")
//    public ResponseEntity<Void> deleteContent(@PathVariable int postId) {
//        Contributions contributions = new contributions();
//        contributions.setId(contributionsId);
//        adminContentService.deleteContent(contributions);
//        return ResponseEntity.ok().build();
//    }
//}
