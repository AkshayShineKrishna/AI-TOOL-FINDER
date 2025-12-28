package com.trumio.task.aitools.services.filterServices;

import com.trumio.task.aitools.models.PricingType;
import org.springframework.stereotype.Service;

@Service
public class FilterCriteria {

    private String category;
    private PricingType pricingType;
    private Double minRating;
    private Double maxRating;

    // ---------- Getters & Setters ----------

    public String getCategory() {
        return category;
    }

    public FilterCriteria setCategory(String category) {
        this.category = category;
        return this;
    }

    public PricingType getPricingType() {
        return pricingType;
    }

    public FilterCriteria setPricingType(PricingType pricingType) {
        this.pricingType = pricingType;
        return this;
    }

    public Double getMinRating() {
        return minRating;
    }

    public FilterCriteria setMinRating(Double minRating) {
        this.minRating = minRating;
        return this;
    }

    public Double getMaxRating() {
        return maxRating;
    }

    public FilterCriteria setMaxRating(Double maxRating) {
        this.maxRating = maxRating;
        return this;
    }

    public boolean hasCategory() {
        return category != null && !category.isBlank();
    }

    public boolean hasPricingType() {

        return pricingType != null;
    }

    public boolean hasMinRating() {

        return minRating != null;
    }

    public boolean hasMaxRating() {

        return maxRating != null;
    }

    public boolean hasAnyFilter() {
        return hasCategory()
                || hasPricingType()
                || hasMinRating()
                || hasMaxRating();
    }

}
