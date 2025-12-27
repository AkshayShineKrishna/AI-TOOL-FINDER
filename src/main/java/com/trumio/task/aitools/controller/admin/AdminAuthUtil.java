package com.trumio.task.aitools.controller.admin;

// package com.trumio.task.aitools.util;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class AdminAuthUtil {

    private static final String ADMIN_KEY = "admin123";

    public static void checkAdmin(String key) {
        if (!ADMIN_KEY.equals(key)) {
            throw new ResponseStatusException(
                HttpStatus.FORBIDDEN,
                "Admin access only"
            );
        }
    }
}

