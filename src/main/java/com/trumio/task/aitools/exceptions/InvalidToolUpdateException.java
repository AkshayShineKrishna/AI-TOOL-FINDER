package com.trumio.task.aitools.exceptions;

import java.util.List;

public class InvalidToolUpdateException extends RuntimeException {
    private final List<String> errors;
    public InvalidToolUpdateException(String message,List<String> errors) {
        super(message);
        this.errors = errors;
    }
    public List<String> getErrors() {
        return errors;
    }
}
