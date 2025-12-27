package com.trumio.task.aitools.services;

import com.trumio.task.aitools.models.AITool;
import com.trumio.task.aitools.repositories.AIToolRepository;
import com.trumio.task.aitools.repositories.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


}
