package com.trumio.task.aitools.controller.userController;

import com.trumio.task.aitools.exceptions.InvalidEnumException;
import com.trumio.task.aitools.models.AITool;
import com.trumio.task.aitools.models.PricingType;
import com.trumio.task.aitools.models.Review;
import com.trumio.task.aitools.services.filterServices.FilterCriteria;
import com.trumio.task.aitools.services.filterServices.FilterService;
import com.trumio.task.aitools.services.userServices.UserServicesImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private final UserServicesImpl userServices;
    private final FilterService filterService;

    public UserController(UserServicesImpl userServices, FilterService filterService) {
        this.userServices = userServices;
        this.filterService = filterService;
    }

    @GetMapping("/tools")
    public ResponseEntity<List<AITool>> getAllAiToolsData(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String pricingType,
            @RequestParam(required = false) Double minRating,
            @RequestParam(required = false) Double maxRating
    ) {
        PricingType pricingEnum;
        if(pricingType.equalsIgnoreCase("FREE")){
            pricingEnum = PricingType.FREE;
        } else if (pricingType.equalsIgnoreCase("PAID")) {
            pricingEnum = PricingType.PAID;
        } else if (pricingType.equalsIgnoreCase("SUBSCRIPTION")) {
            pricingEnum = PricingType.SUBSCRIPTION;
        }else {
            throw new InvalidEnumException("Invalid ENUM value for pricingType < FREE,PAID,SUBSCRIPTION >");
        }

        FilterCriteria criteria = new FilterCriteria()
                .setCategory(category)
                .setPricingType(pricingEnum)
                .setMinRating(minRating)
                .setMaxRating(maxRating);

        // 🔹 If no filters provided → return all tools
        if (!criteria.hasAnyFilter()) {
            return ResponseEntity.status(HttpStatus.OK).body(userServices.retrieveAllTools());
        }

        // 🔹 Otherwise apply filters
        return ResponseEntity.status(HttpStatus.OK).body(filterService.filter(criteria));
    }
    @GetMapping("/tools/{id}")
    public ResponseEntity<AITool> getToolsById(@PathVariable String id){
        return ResponseEntity.status(HttpStatus.OK).body(userServices.retrievebyid(id));
    }

    @PostMapping("/tools/review")
    public ResponseEntity<Review> addReview(@RequestBody Review review) {
        userServices.addReview(review);
        return ResponseEntity.status(HttpStatus.CREATED).body(review);
    }

}
