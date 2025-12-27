package com.trumio.task.aitools.controller.admin;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/reviews")
public class AdminReviewController {

    // private final ReviewService reviewService;

    // public AdminReviewController(ReviewService reviewService) {
    //     this.reviewService = reviewService;
    // }

    @PutMapping("/{id}/approve")
    public String approve(
            @RequestHeader("X-ADMIN-KEY") String adminKey,
            @PathVariable String id) {

        AdminAuthUtil.checkAdmin(adminKey);
        // reviewService.approveReview(id);

        return "Review approved";
    }
}
