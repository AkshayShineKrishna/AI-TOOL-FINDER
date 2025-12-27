package com.trumio.task.aitools.controller.admin;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TestController {

    @GetMapping("/public")
    public String publicEndpoint() {
        return "Public access ✅";
    }

    @GetMapping("/protected/hello")
    public String protectedEndpoint() {
        return "Protected access 🔐";
    }
}
