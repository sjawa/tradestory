package de.app.tradestory.controller;

import de.app.tradestory.elasticsearch.Content;

import java.util.List;

public class AjaxResponseBody {

    String msg;
    List<Content> result;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<Content> getResult() {
        return result;
    }

    public void setResult(List<Content> result) {
        this.result = result;
    }
}