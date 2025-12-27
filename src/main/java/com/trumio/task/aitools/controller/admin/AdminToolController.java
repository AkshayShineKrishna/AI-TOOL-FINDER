package com.trumio.task.aitools.controller.admin;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trumio.task.aitools.models.AITool;
import com.trumio.task.aitools.services.AdminServices;
// import com.trumio.task.aitools.util.AdminAuthUtil;

@RestController
@RequestMapping("/admin/tools")
public class AdminToolController {

    private final AdminServices adminServices;

    public AdminToolController(AdminServices adminServices) {
        this.adminServices = adminServices;
    }

    // ADD TOOL (JSON)
    @PostMapping
    public AITool addTool(
            @RequestHeader("X-ADMIN-KEY") String adminKey,
            @RequestBody AITool tool) {

        // X-ADMIN-KEY : admin123
        AdminAuthUtil.checkAdmin(adminKey);

        return adminServices.addTool(
                tool.getName(),
                tool.getUseCase(),
                tool.getCategory(),
                tool.getPricingType()
        );
    }

    // UPDATE TOOL (JSON, partial allowed)
    @PutMapping("/{id}")
    public AITool updateTool(
            @RequestHeader("X-ADMIN-KEY") String adminKey,
            @PathVariable String id,
            @RequestBody AITool tool) {

        AdminAuthUtil.checkAdmin(adminKey);

        return adminServices.updateTool(
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

        AdminAuthUtil.checkAdmin(adminKey);

        adminServices.removeTool(id);
        return "Tool removed successfully";
    }
}
