package com.trumio.task.aitools.services;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.trumio.task.aitools.models.AITool;
import com.trumio.task.aitools.models.PricingType;
import com.trumio.task.aitools.repositories.AIToolRepository;

@Service
public class AdminServicesImpl implements AdminServices {

    private final AIToolRepository toolRepository;

    public AdminServicesImpl(AIToolRepository toolRepository) {
        this.toolRepository = toolRepository;
    }

    @Override
    public AITool addTool(String name, String useCase,
                          String category, PricingType pricingType) {

        AITool tool = new AITool();
        tool.setName(name);
        tool.setUseCase(useCase);
        tool.setCategory(category);
        tool.setPricingType(pricingType);
        tool.setAverageRating(0.0);
        tool.setCreatedAt(LocalDateTime.now());
        tool.setCreatedAt(LocalDateTime.now());

        return toolRepository.save(tool);
    }

    @Override
    public AITool updateTool(String id, String name,
                             String useCase, String category,
                             PricingType pricingType) {

        AITool tool = toolRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tool not found"));

        if (name != null) tool.setName(name);
        if (useCase != null) tool.setUseCase(useCase);
        if (category != null) tool.setCategory(category);
        if (pricingType != null) tool.setPricingType(pricingType);

        tool.setCreatedAt(LocalDateTime.now());

        return toolRepository.save(tool);
    }
    @Override
    public void removeTool(String id) {

        if (!toolRepository.existsById(id)) {
            throw new RuntimeException("Tool not found");
        }

        toolRepository.deleteById(id);
    }
}
