package com.trumio.task.aitools.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.trumio.task.aitools.models.AITool;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AIToolRepository extends MongoRepository<AITool, String> {
    Optional<AITool> findByName(String name);
    List<AITool> findAll();
}
