package com.trumio.task.aitools.repositories;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.trumio.task.aitools.models.Review;

public interface ReviewRepository extends MongoRepository<Review, String> {

    List<Review> findByToolId(String toolId);
}
