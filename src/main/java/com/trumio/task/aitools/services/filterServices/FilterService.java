package com.trumio.task.aitools.services.filterServices;

import com.trumio.task.aitools.models.AITool;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FilterService {

    private final MongoTemplate mongoTemplate;

    public FilterService(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public List<AITool> filter(FilterCriteria c) {

        Query query = new Query();
        List<Criteria> criteriaList = new ArrayList<>();

        if (c.getCategory() != null && !c.getCategory().isBlank()) {
            criteriaList.add(Criteria.where("category")
                    .regex("^" + c.getCategory() + "$", "i"));
        }

        if (c.getPricingType() != null) {
            criteriaList.add(Criteria.where("pricingType")
                    .is(c.getPricingType()));
        }

        if (c.getMinRating() != null) {
            criteriaList.add(Criteria.where("averageRating")
                    .gte(c.getMinRating()));
        }

        if (c.getMaxRating() != null) {
            criteriaList.add(Criteria.where("averageRating").lte(c.getMaxRating()));
        }

        if (!criteriaList.isEmpty()) {
            query.addCriteria(new Criteria().andOperator(
                    criteriaList.toArray(new Criteria[0])
            ));
        }

        return mongoTemplate.find(query, AITool.class);
    }
}
