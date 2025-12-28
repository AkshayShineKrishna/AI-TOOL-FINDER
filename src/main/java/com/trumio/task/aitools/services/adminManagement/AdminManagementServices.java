package com.trumio.task.aitools.services.adminManagement;

import com.trumio.task.aitools.models.AITool;
import com.trumio.task.aitools.models.PricingType;
import com.trumio.task.aitools.models.Review;
import com.trumio.task.aitools.models.ReviewStatus;

import java.util.List;
import java.util.Optional;

public interface AdminManagementServices {
    AITool addTool(String name, String useCase,
                   String category, PricingType pricingType);

    AITool updateTool(String id, String name,
                      String useCase, String category,
                      PricingType pricingType);
    void removeTool(String id);
    List<Review> retrieveAllReviews(ReviewStatus status);
    List<Review> retrieveAllReviews();
    Review retrieveReviewsById(String id);
    List<Review> retrieveReviewByToolId(String id);
    Review changeStatus(String id,String status);
}
