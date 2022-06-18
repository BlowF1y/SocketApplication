package com.daelim.socketapplication.data;

public class socketVO {
    private String id;
    private String message;

    public socketVO(String id, String message) {
        this.id = id;
        this.message = message;
    }

    public socketVO(){

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
         this.message = message;
    }
}
