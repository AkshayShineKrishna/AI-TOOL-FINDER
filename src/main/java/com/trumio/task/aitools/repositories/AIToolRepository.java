package com.trumio.task.aitools.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.trumio.task.aitools.models.AITool;

import java.util.Optional;

public interface AIToolRepository extends MongoRepository<AITool, String> {
    Optional<AITool> findByName(String name);
}
