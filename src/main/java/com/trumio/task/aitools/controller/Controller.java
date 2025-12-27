package com.trumio.task.aitools.controller;

import com.trumio.task.aitools.models.AITool;
// import com.trumio.task.aitools.services.UserServices;
import com.trumio.task.aitools.services.UserServicesImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class Controller {

      private final UserServicesImpl userServices;

    public Controller(UserServicesImpl userServices) {
        this.userServices = userServices;
    }

    @GetMapping("/Aitools")
    public List<AITool> getall(){
        return userServices.retrieveAllTools();
    }
    @GetMapping("/find/{id}")
    public Optional<AITool> getid(@PathVariable String id){
        return userServices.retrievebyid(id);
    }
}
