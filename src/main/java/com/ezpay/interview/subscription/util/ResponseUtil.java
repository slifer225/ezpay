package com.ezpay.interview.subscription.util;

import com.ezpay.interview.subscription.model.ResponseDTO;

import java.util.LinkedList;
import java.util.List;

public final class ResponseUtil {

    public static ResponseDTO onSuccess(Object result){
        return onSuccess("", result);
    }

    public static ResponseDTO onSuccess(String message){
        return onSuccess(message, null);
    }

    public static ResponseDTO onSuccess(List<String> messages){
        return onSuccess(messages, null);
    }

    public static ResponseDTO onSuccess(String message, Object result){
        List<String> messages = new LinkedList<>();
        messages.add(message);
        return onSuccess(messages, result);
    }

    public static ResponseDTO onSuccess(List<String> message, Object result){
        return new ResponseDTO<Object>(true, message, result);
    }

    public static ResponseDTO onFail(Object result){
        return onFail("", result);
    }

    public static ResponseDTO onFail(String message){
        return onFail(message, null);
    }

    public static ResponseDTO onFail(List<String> messages){
        return onFail(messages, null);
    }

    public static ResponseDTO onFail(String message, Object result){
        List<String> messages = new LinkedList<>();
        messages.add(message);
        return onFail(messages, result);
    }

    public static ResponseDTO onFail(List<String> message, Object result){
        return new ResponseDTO<Object>(false, message, result);
    }
}
