package com.trumio.task.aitools.controller.admin;

import com.trumio.task.aitools.exceptions.InvalidEnumException;
import com.trumio.task.aitools.exceptions.InvalidIDException;
import com.trumio.task.aitools.models.Review;
import com.trumio.task.aitools.models.ReviewStatus;
import com.trumio.task.aitools.services.adminAuth.AdminAuthServices;
import com.trumio.task.aitools.services.adminManagement.AdminManagementServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/reviews")
public class AdminReviewController {

    private final AdminAuthServices adminAuthServices;
    private final AdminManagementServices adminManagementServices;

    AdminReviewController(AdminAuthServices adminAuthServices, AdminManagementServices adminManagementServices) {
        this.adminAuthServices = adminAuthServices;
        this.adminManagementServices = adminManagementServices;
    }

    @PatchMapping("/{id}/{status}")
    public ResponseEntity<Review> changeReviewStatus(
            @RequestHeader("X-ADMIN-KEY") String adminKey,
            @PathVariable String id,
            @PathVariable String status) {

        adminAuthServices.checkAdmin(adminKey);
        Review review = adminManagementServices.changeStatus(id,status);
        return ResponseEntity.status(HttpStatus.CREATED).body(review);
    }

    @GetMapping()
    public ResponseEntity<List<Review>> retrieveAllReviews(
            @RequestHeader("X-ADMIN-KEY") String adminKey,
            @RequestParam(required = false) String status
    ) {
        adminAuthServices.checkAdmin(adminKey);

        List<Review> reviews;

        if (status == null || status.isBlank()) {
            // No status provided → fetch all reviews
            reviews = adminManagementServices.retrieveAllReviews();
        } else {
            ReviewStatus reviewStatus;
            if (status.equalsIgnoreCase("PENDING")) {
                reviewStatus = ReviewStatus.PENDING;
            } else if (status.equalsIgnoreCase("APPROVED")) {
                reviewStatus = ReviewStatus.APPROVED;
            } else if (status.equalsIgnoreCase("REJECTED")) {
                reviewStatus = ReviewStatus.REJECTED;
            } else {
                throw new InvalidEnumException("Invalid ENUM value for status <PENDING,APPROVED,REJECTED>");
            }
            reviews = adminManagementServices.retrieveAllReviews(reviewStatus);
        }
        return ResponseEntity.ok(reviews);
    }

    @GetMapping("/tool/{toolId}")
    public ResponseEntity<List<Review>> retrieveAllReviewsByTool(
            @RequestHeader("X-ADMIN-KEY") String adminKey,
            @PathVariable(required = false) String toolId
    ){
        adminAuthServices.checkAdmin(adminKey);
        List<Review> reviews = adminManagementServices.retrieveReviewByToolId(toolId);
        return ResponseEntity.ok(reviews);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Review> retrieveAReview(
            @RequestHeader("X-ADMIN-KEY") String adminKey,
            @PathVariable String id
    ){
        adminAuthServices.checkAdmin(adminKey);
        return ResponseEntity.ok(adminManagementServices.retrieveReviewsById(id));
    }

}
