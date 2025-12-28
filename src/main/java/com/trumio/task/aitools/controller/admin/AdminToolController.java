package com.trumio.task.aitools.controller.admin;

import com.trumio.task.aitools.models.ReviewResponse;
import com.trumio.task.aitools.services.adminAuth.AdminAuthServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.trumio.task.aitools.models.AITool;
import com.trumio.task.aitools.services.adminManagement.AdminManagementServices;

import java.util.Map;

@RestController
@RequestMapping("/admin/tools")
public class AdminToolController {

    private final AdminManagementServices adminManagementServices;
    private final AdminAuthServices adminAuthServices;

    public AdminToolController(AdminManagementServices adminManagementServices, AdminAuthServices adminAuthServices) {
        this.adminManagementServices = adminManagementServices;
        this.adminAuthServices = adminAuthServices;
    }


    @PostMapping
    public ResponseEntity<AITool> addTool(
            @RequestHeader("X-ADMIN-KEY") String adminKey,
            @RequestBody AITool tool) {

        adminAuthServices.checkAdmin(adminKey);

        return ResponseEntity.ok(adminManagementServices.addTool(
                tool.getName(),
                tool.getUseCase(),
                tool.getCategory(),
                tool.getPricingType()
        ));
    }


    @PatchMapping("/{id}")
    public ResponseEntity<AITool> updateTool(
            @RequestHeader("X-ADMIN-KEY") String adminKey,
            @PathVariable String id,
            @RequestBody AITool tool) {

        adminAuthServices.checkAdmin(adminKey);

        return ResponseEntity.ok(adminManagementServices.updateTool(
                id,
                tool.getName(),
                tool.getUseCase(),
                tool.getCategory(),
                tool.getPricingType()
        ));
    }

    // DELETE TOOL
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String,String>> deleteTool(
            @RequestHeader("X-ADMIN-KEY") String adminKey,
            @PathVariable String id) {

        adminAuthServices.checkAdmin(adminKey);

        adminManagementServices.removeTool(id);
        String message = "Tool with id = " + id + " deleted successfully";
        return ResponseEntity.ok(Map.of("result",message));
    }
}
