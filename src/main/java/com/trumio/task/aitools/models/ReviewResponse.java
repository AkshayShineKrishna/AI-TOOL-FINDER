package com.trumio.task.aitools.models;

import java.util.List;

public class ReviewResponse {
    private String message;
    private List<Review> reviews;
    private String result;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public ReviewResponse(String message, List<Review> reviews) {
        this.message = message;
        this.reviews = reviews;
    }

    public ReviewResponse(String message,String result){
        this.message = message;
        this.result = result;
    }
}


