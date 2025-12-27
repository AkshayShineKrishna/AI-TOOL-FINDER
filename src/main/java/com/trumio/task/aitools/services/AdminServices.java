package com.trumio.task.aitools.services;

import com.trumio.task.aitools.models.AITool;
import com.trumio.task.aitools.models.PricingType;

public interface AdminServices {
//    add new tools
//    view all reviews
//update review sts
AITool addTool(String name, String useCase,
                   String category, PricingType pricingType);

    AITool updateTool(String id, String name,
                      String useCase, String category,
                      PricingType pricingType);
 void removeTool(String id);
//
}
