package com.trumio.task.aitools.services;

import com.trumio.task.aitools.models.AITool;

import java.util.List;
import java.util.Optional;

public interface UserServices {
public List<AITool> retrieveAllTools();
public Optional<AITool> retrievebyid(String id);

//get all aitools
//    getai tool by id
//    by name
//    add review
//    filter


}
