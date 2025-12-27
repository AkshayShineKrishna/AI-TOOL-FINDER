package com.trumio.task.aitools.controller;

import com.trumio.task.aitools.models.AITool;
import com.trumio.task.aitools.models.PricingType;
import com.trumio.task.aitools.services.FilterCriteria;
import com.trumio.task.aitools.services.FilterService;
import com.trumio.task.aitools.services.UserServices;
import com.trumio.task.aitools.services.UserServicesImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class Controller {

    private final UserServicesImpl userServices;
    private final FilterService filterService;

    public Controller(UserServicesImpl userServices, FilterService filterService) {
        this.userServices = userServices;
        this.filterService = filterService;
    }

    @GetMapping("/tools")
    public List<AITool> getAllAiToolsData(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) PricingType pricingType,
            @RequestParam(required = false) Double minRating,
            @RequestParam(required = false) Double maxRating
    ) {

        FilterCriteria criteria = new FilterCriteria()
                .setCategory(category)
                .setPricingType(pricingType)
                .setMinRating(minRating)
                .setMaxRating(maxRating);

        // 🔹 If no filters provided → return all tools
        if (!criteria.hasAnyFilter()) {
            return userServices.retrieveAllTools();
        }

        // 🔹 Otherwise apply filters
        return filterService.filter(criteria);
    }
    @GetMapping("tools/{id}")
    public Optional<AITool> getToolsById(@PathVariable String id){
        return userServices.retrievebyid(id);
    }

}
