package com.trumio.task.aitools.exceptions;

import java.util.List;

public class InvalidReviewException extends RuntimeException {

    private final List<String> errors;

    public InvalidReviewException(List<String> errors) {
        super("Invalid review data");
        this.errors = errors;
    }

    public List<String> getErrors() {
        return errors;
    }
}