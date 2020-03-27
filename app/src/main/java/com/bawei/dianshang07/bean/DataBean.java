package com.bawei.dianshang07.bean;

import java.io.Serializable;
import java.util.List;

public class DataBean<T> implements Serializable {
    private List<T> result;
    private String status;
    private String message;
    public List<T> getResult() {
        return result;
    }
    public void setResult(List<T> result) {
        this.result = result;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
}
