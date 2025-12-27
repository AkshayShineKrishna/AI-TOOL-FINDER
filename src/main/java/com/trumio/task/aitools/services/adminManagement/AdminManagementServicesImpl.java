package com.trumio.task.aitools.services.adminManagement;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.trumio.task.aitools.exceptions.InvalidToolUpdateException;
import org.springframework.stereotype.Service;

import com.trumio.task.aitools.models.AITool;
import com.trumio.task.aitools.models.PricingType;
import com.trumio.task.aitools.repositories.AIToolRepository;

@Service
public class AdminManagementServicesImpl implements AdminManagementServices {

    private final AIToolRepository toolRepository;

    public AdminManagementServicesImpl(AIToolRepository toolRepository) {
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
        return toolRepository.save(tool);
    }

    @Override
    public AITool updateTool(String id, String name,
                             String useCase, String category,
                             PricingType pricingType) {

        AITool tool = toolRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tool not found"));

        List<String> errors = new ArrayList<>();
        if (name == null) errors.add("name");
        if (useCase == null) errors.add("useCase not provided");
        if (category == null) errors.add("category not provided");
        if (pricingType == null) errors.add("pricingType not provided");
        if (errors.size() == 4) {
            throw new InvalidToolUpdateException("No fields provided for update.",errors);
        }

        if (name != null) tool.setName(name);
        if (useCase != null) tool.setUseCase(useCase);
        if (category != null) tool.setCategory(category);
        if (pricingType != null) tool.setPricingType(pricingType);

        tool.setUpdatedAt(LocalDateTime.now());

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
