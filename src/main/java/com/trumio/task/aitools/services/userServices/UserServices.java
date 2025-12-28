package com.trumio.task.aitools.services.userServices;

import com.trumio.task.aitools.models.AITool;
import com.trumio.task.aitools.models.Review;

import java.util.List;
import java.util.Optional;

public interface UserServices {
    public List<AITool> retrieveAllTools();
    public AITool retrievebyid(String id);
    public void addReview(Review review);
}
