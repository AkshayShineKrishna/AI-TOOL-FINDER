package com.trumio.task.aitools.services.ratingServices;

public interface RatingServices {
    void calculateRatingsByToolId(String toolId);
    void calculateAllRatings();
}
