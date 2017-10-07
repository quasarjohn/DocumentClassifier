package com.berstek.models;

import org.springframework.http.HttpStatus;

public class ReturnObject {

    public HttpStatus status;
    public Object content;

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }

}
