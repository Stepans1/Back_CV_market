package com.example.BootApp.util;

public class PostErrorResponse
{
    private String message;

    private Long timestap;

    public PostErrorResponse(String message, Long timestap) {
        this.message = message;
        this.timestap = timestap;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getTimestap() {
        return timestap;
    }

    public void setTimestap(Long timestap) {
        this.timestap = timestap;
    }
}
