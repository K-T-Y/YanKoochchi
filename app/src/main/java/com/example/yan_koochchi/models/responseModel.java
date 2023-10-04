package com.example.yan_koochchi.models;

public class responseModel {
   String message ;
   int code;

   userModel data;

    public userModel getData() {
        return data;
    }

    public void setData(userModel data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
