package com.example.second_retro_java;

public class ResponseModel {
    private boolean success;
    private String message;
    private Users users;

    public ResponseModel(boolean success,String message,Users users){
        this.success=success;
        this.message=message;
        this.users=users;
    }

    public boolean isSuccess() {
        return success;
    }
}
