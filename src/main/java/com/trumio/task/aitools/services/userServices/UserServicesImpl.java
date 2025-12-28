package com.trumio.task.aitools.services.userServices;

import com.trumio.task.aitools.exceptions.InvalidIDException;
import com.trumio.task.aitools.exceptions.InvalidReviewException;
import com.trumio.task.aitools.models.AITool;
import com.trumio.task.aitools.models.Review;
import com.trumio.task.aitools.models.ReviewStatus;
import com.trumio.task.aitools.repositories.AIToolRepository;
import com.trumio.task.aitools.repositories.ReviewRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
    public AITool retrievebyid(String id) {
         Optional<AITool> aiToolOpt = aiToolRepository.findById(id);
         if(aiToolOpt.isPresent()){
             AITool aiTool = aiToolOpt.get();
             return aiTool;
         }else {
             throw new InvalidIDException("Review with id = " + id + " doesn't exist");
         }
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

        if(review.getRating() == null){
            errors.add("Rating cannot be empty");
            throw new InvalidReviewException(errors);
        }

        if (review.getRating() < 1 || review.getRating() > 5) {
            errors.add("Rating must be between 1 and 5");
        }

        if (!errors.isEmpty()) {
            throw new InvalidReviewException(errors);
        }

        review.setCreatedAt(LocalDateTime.now());
        review.setStatus(ReviewStatus.PENDING);
        reviewRepository.save(review);
    }
}
