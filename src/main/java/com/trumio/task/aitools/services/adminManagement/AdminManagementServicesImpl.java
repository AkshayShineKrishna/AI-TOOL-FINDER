package com.trumio.task.aitools.services.adminManagement;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.trumio.task.aitools.exceptions.InvalidEnumException;
import com.trumio.task.aitools.exceptions.InvalidIDException;
import com.trumio.task.aitools.exceptions.InvalidToolUpdateException;
import com.trumio.task.aitools.models.Review;
import com.trumio.task.aitools.models.ReviewStatus;
import com.trumio.task.aitools.repositories.ReviewRepository;
import com.trumio.task.aitools.services.ratingServices.RatingServices;
import org.springframework.stereotype.Service;

import com.trumio.task.aitools.models.AITool;
import com.trumio.task.aitools.models.PricingType;
import com.trumio.task.aitools.repositories.AIToolRepository;

@Service
public class AdminManagementServicesImpl implements AdminManagementServices {

    private final AIToolRepository toolRepository;
    private final ReviewRepository reviewRepository;
    private final RatingServices ratingServices;

    public AdminManagementServicesImpl(AIToolRepository toolRepository, ReviewRepository reviewRepository, RatingServices ratingServices) {
        this.toolRepository = toolRepository;
        this.reviewRepository = reviewRepository;
        this.ratingServices = ratingServices;
    }

    @Override
    public AITool addTool(String name, String useCase,
                          String category, PricingType pricingType) {

        AITool tool = new AITool();
        tool.setName(name);
        tool.setUseCase(useCase);
        tool.setCategory(category);
        tool.setPricingType(pricingType);
        tool.setAverageRating(0.0);
        tool.setCreatedAt(LocalDateTime.now());
        return toolRepository.save(tool);
    }

    @Override
    public AITool updateTool(String id, String name,
                             String useCase, String category,
                             PricingType pricingType) {

        AITool tool = toolRepository.findById(id)
                .orElseThrow(() -> new InvalidIDException("Review with id = " + id + " doesn't exist"));

        List<String> errors = new ArrayList<>();
        if (name == null) errors.add("name");
        if (useCase == null) errors.add("useCase not provided");
        if (category == null) errors.add("category not provided");
        if (pricingType == null) errors.add("pricingType not provided");
        if (errors.size() == 4) {
            throw new InvalidToolUpdateException("No fields provided for update.",errors);
        }

        if (name != null) tool.setName(name);
        if (useCase != null) tool.setUseCase(useCase);
        if (category != null) tool.setCategory(category);
        if (pricingType != null) tool.setPricingType(pricingType);

        tool.setUpdatedAt(LocalDateTime.now());

        return toolRepository.save(tool);
    }
    @Override
    public void removeTool(String id) {

        if (!toolRepository.existsById(id)) {
            throw new InvalidIDException("Review with id = " + id + " doesn't exist");
        }
        toolRepository.deleteById(id);
    }

    @Override
    public List<Review> retrieveAllReviews() {
        return reviewRepository.findAll();
    }

    @Override
    public Review retrieveReviewsById(String id) {
        Optional<Review> reviewOpt = reviewRepository.findById(id);
        if(reviewOpt.isPresent()){
            return reviewOpt.get();
        }else{
            throw new InvalidIDException("Review with id = " + id + " doesn't exist");
        }
    }

    @Override
    public List<Review> retrieveAllReviews(ReviewStatus status) {
        List<Review> reviews = reviewRepository.findAll();

        if (status == null) {
            return reviews;
        }

        return reviews.stream()
                .filter(r -> r.getStatus() == status)
                .collect(Collectors.toList());
    }


    @Override
    public List<Review> retrieveReviewByToolId(String toolId) {
        if (toolRepository.existsById(toolId)){
            return reviewRepository.findByToolId(toolId);
        }else {
            throw new InvalidIDException("Tool with id = " + toolId + " doesn't exist");
        }

    }

    @Override
    public Review changeStatus(String id, String status) {

        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new InvalidIDException("Review with id = " + id + " doesn't exist"));

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
        ratingServices.calculateRatingsByToolId(review.getToolId());
        review.setStatus(reviewStatus);
        reviewRepository.save(review);

        return review;
    }

}
