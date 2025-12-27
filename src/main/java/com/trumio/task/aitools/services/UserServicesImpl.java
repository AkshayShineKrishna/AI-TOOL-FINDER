package com.trumio.task.aitools.services;

import com.trumio.task.aitools.exceptions.InvalidReviewException;
import com.trumio.task.aitools.models.AITool;
import com.trumio.task.aitools.models.Review;
import com.trumio.task.aitools.repositories.AIToolRepository;
import com.trumio.task.aitools.repositories.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServicesImpl implements UserServices {

    private final AIToolRepository aiToolRepository;
    private final ReviewRepository reviewRepository;

    public UserServicesImpl(AIToolRepository aiToolRepository,ReviewRepository reviewRepository) {
        this.aiToolRepository = aiToolRepository;
        this.reviewRepository = reviewRepository;
    }

    @Override
    public List<AITool> retrieveAllTools() {
        return aiToolRepository.findAll();
    }

    @Override
    public Optional<AITool> retrievebyid(String id) {
        return aiToolRepository.findById(id);
    }

    @Override
    public void addReview(Review review) {

        List<String> errors = new ArrayList<>();

        if (review == null) {
            errors.add("Review object must not be null");
            throw new InvalidReviewException(errors);
        }

        if (review.getToolId() == null || review.getToolId().isBlank()) {
            errors.add("Tool ID must not be empty");
        } else if (!aiToolRepository.existsById(review.getToolId())) {
            errors.add("Tool with given ID does not exist");
        }

        if (review.getRating() < 1 || review.getRating() > 5) {
            errors.add("Rating must be between 1 and 5");
        }

        if (!errors.isEmpty()) {
            throw new InvalidReviewException(errors);
        }

        reviewRepository.save(review);
    }
}
