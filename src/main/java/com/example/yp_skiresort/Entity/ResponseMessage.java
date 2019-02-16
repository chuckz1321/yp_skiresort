package com.example.yp_skiresort.Entity;

import com.google.gson.Gson;

public class ResponseMessage<T> {

    private String httpCode;

    private T responseBody;

    public String getHttpCode() {
        return httpCode;
    }

    public void setHttpCode(String httpCode) {
        this.httpCode = httpCode;
    }

    public T getResponseBody() {
        return responseBody;
    }

    public void setResponseBody(T responseBody) {
        this.responseBody = responseBody;
    }

    public String convert2Json(){
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
