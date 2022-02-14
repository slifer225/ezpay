package com.ezpay.interview.subscription.model;

import java.io.Serializable;
import java.util.List;

public class ResponseDTO<T> implements Serializable {

    private Boolean statusFlag;

    private List<String> message;

    private T result;

    public ResponseDTO(){}

    public Boolean getStatusFlag() {
        return statusFlag;
    }

    public List<String> getMessage() {
        return message;
    }

    public T getResult() {
        return result;
    }

    public ResponseDTO(Boolean statusFlag, List<String> message, T result) {
        this.statusFlag = statusFlag;
        this.message = message;
        this.result = result;
    }
}
