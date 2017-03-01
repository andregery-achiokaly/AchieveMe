package com.example.alexander_topilskii.achieveme.model.rest;


public class PostModel {
    private String success;
    private String message;
    private String token;

    public PostModel(String success, String message, String token) {
        this.success = success;
        this.message = message;
        this.token = token;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
