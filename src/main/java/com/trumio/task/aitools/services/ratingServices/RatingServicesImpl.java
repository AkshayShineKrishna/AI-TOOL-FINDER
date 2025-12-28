package com.trumio.task.aitools.services.ratingServices;

import com.trumio.task.aitools.exceptions.InvalidIDException;
import com.trumio.task.aitools.models.AITool;
import com.trumio.task.aitools.models.Review;
import com.trumio.task.aitools.models.ReviewStatus;
import com.trumio.task.aitools.repositories.AIToolRepository;
import com.trumio.task.aitools.repositories.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RatingServicesImpl implements RatingServices {

    private final AIToolRepository aiToolRepository;
    private final ReviewRepository reviewRepository;

    public RatingServicesImpl(AIToolRepository aiToolRepository, ReviewRepository reviewRepository) {
        this.aiToolRepository = aiToolRepository;
        this.reviewRepository = reviewRepository;
    }


    @Override
    public void calculateRatingsByToolId(String toolId) {
        List<Review> reviews = reviewRepository.findByToolId(toolId);
        Double averageRating = reviews.stream()
                .filter(review -> review.getStatus() == ReviewStatus.APPROVED)
                .mapToInt(Review::getRating)
                .average().orElse(0.0);
        Optional<AITool> aiToolOpt = aiToolRepository.findById(toolId);
        if (aiToolOpt.isPresent()) {
            AITool aiTool = aiToolOpt.get();
            aiTool.setAverageRating(averageRating);
            aiToolRepository.save(aiTool);
        } else {
            throw new InvalidIDException("Tool with id = " + toolId + " doesn't exist");
        }

    }

    @Override
    public void calculateAllRatings() {
        List<AITool> aiTools = aiToolRepository.findAll();
        for (AITool aiTool : aiTools) {
            Double averageRating = reviewRepository.findAverageRatingByToolId(aiTool.getId());
            aiTool.setAverageRating(averageRating != null ? averageRating : 0.0);
        }
        aiToolRepository.saveAll(aiTools);
    }
}
