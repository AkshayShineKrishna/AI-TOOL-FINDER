package com.trumio.task.aitools.controller.admin;

import com.trumio.task.aitools.services.adminAuth.AdminAuthServices;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/reviews")
public class AdminReviewController {

    private final AdminAuthServices adminAuthServices;

    AdminReviewController(AdminAuthServices adminAuthServices){
        this.adminAuthServices = adminAuthServices;
    }

    @PutMapping("/{id}/approve")
    public String approve(
            @RequestHeader("X-ADMIN-KEY") String adminKey,
            @PathVariable String id) {

        adminAuthServices.checkAdmin(adminKey);
        // reviewService.approveReview(id);

        return "Review approved";
    }
}
