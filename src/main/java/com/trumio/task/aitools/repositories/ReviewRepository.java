package com.trumio.task.aitools.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.trumio.task.aitools.models.Review;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends MongoRepository<Review, String> {
    @Aggregation(pipeline = { "{ $match: { toolId: ?0, status: 'APPROVED' } }", "{ $group: { _id: null, avgRating: { $avg: '$rating' } } }" })
    Double findAverageRatingByToolId(String toolId);
    boolean existsByToolId(String toolId);
    List<Review> findByToolId(String toolId);
    Optional<Review> findById(String id);
}
