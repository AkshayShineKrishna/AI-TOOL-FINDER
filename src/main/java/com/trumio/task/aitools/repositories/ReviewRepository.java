package com.trumio.task.aitools.repositories;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.trumio.task.aitools.models.Review;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends MongoRepository<Review, String> {
    boolean existsByToolId(String toolId);
    List<Review> findByToolId(String toolId);
}
