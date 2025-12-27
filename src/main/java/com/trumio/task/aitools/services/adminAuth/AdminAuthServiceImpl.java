package com.trumio.task.aitools.services.adminAuth;

import com.trumio.task.aitools.exceptions.UnauthorizedAccessException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AdminAuthServiceImpl implements AdminAuthServices {

    private final String adminKey;

    public AdminAuthServiceImpl(@Value("${admin.key}") String adminKey) {
        this.adminKey = adminKey;
    }

    @Override
    public void checkAdmin(String key) {
        if (!adminKey.equals(key)) {
            throw new UnauthorizedAccessException("Access denied due to invalid authentication");
        }
    }
}

