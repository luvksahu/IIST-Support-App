package com.brocoders.iistsupport.notices.notice;

public class NoticeModel {

    String message;

    public NoticeModel(){

    }

    public NoticeModel(String message){
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
