package com.furence.assignment.user.model.dto;

public class ResponseData {

    boolean isSuccess;
    String message;

    public ResponseData() {
    }

    public ResponseData(boolean isSuccess, String message) {
        this.isSuccess = isSuccess;
        this.message = message;
    }
}
