package com.trumio.task.aitools.controller.admin;

import com.trumio.task.aitools.services.adminAuth.AdminAuthServices;
import org.springframework.web.bind.annotation.*;

import com.trumio.task.aitools.models.AITool;
import com.trumio.task.aitools.services.adminManagement.AdminManagementServices;

@RestController
@RequestMapping("/admin/tools")
public class AdminToolController {

    private final AdminManagementServices adminManagementServices;
    private final AdminAuthServices adminAuthServices;

    public AdminToolController(AdminManagementServices adminManagementServices, AdminAuthServices adminAuthServices) {
        this.adminManagementServices = adminManagementServices;
        this.adminAuthServices = adminAuthServices;
    }

    // ADD TOOL (JSON)
    @PostMapping
    public AITool addTool(
            @RequestHeader("X-ADMIN-KEY") String adminKey,
            @RequestBody AITool tool) {

        adminAuthServices.checkAdmin(adminKey);

        return adminManagementServices.addTool(
                tool.getName(),
                tool.getUseCase(),
                tool.getCategory(),
                tool.getPricingType()
        );
    }


    @PatchMapping("/{id}")
    public AITool updateTool(
            @RequestHeader("X-ADMIN-KEY") String adminKey,
            @PathVariable String id,
            @RequestBody AITool tool) {

        adminAuthServices.checkAdmin(adminKey);

        return adminManagementServices.updateTool(
                id,
                tool.getName(),
                tool.getUseCase(),
                tool.getCategory(),
                tool.getPricingType()
        );
    }

    // DELETE TOOL
    @DeleteMapping("/{id}")
    public String deleteTool(
            @RequestHeader("X-ADMIN-KEY") String adminKey,
            @PathVariable String id) {

        adminAuthServices.checkAdmin(adminKey);

        adminManagementServices.removeTool(id);
        return "Tool removed successfully";
    }
}
