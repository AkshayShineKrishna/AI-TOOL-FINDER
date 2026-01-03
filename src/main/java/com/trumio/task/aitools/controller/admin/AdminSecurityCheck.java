package com.trumio.task.aitools.controller.admin;

import com.trumio.task.aitools.services.adminAuth.AdminAuthServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/adminchecker")
@CrossOrigin(
        origins = "http://localhost:5173",
        allowedHeaders = { "X-ADMIN-KEY", "Content-Type" },
        methods = { RequestMethod.GET}
)
public class AdminSecurityCheck {

    private final AdminAuthServices adminAuthServices;

    public AdminSecurityCheck(AdminAuthServices adminAuthServices) {
        this.adminAuthServices = adminAuthServices;
    }

    @GetMapping
    public ResponseEntity<Void> adminValidator(
            @RequestHeader("X-ADMIN-KEY") String adminKey
    ) {
        adminAuthServices.checkAdmin(adminKey);
        return ResponseEntity.ok().build();
    }
}
