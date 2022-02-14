package com.ezpay.interview.subscription.validator;

import com.ezpay.interview.subscription.constant.SubscriptionErrorConstant;
import com.ezpay.interview.subscription.model.SubscriptionForm;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public final class SubscriptionFormValidator {

    public static final List<String> validate(SubscriptionForm subscriptionForm){
        List<String> err = new LinkedList<>();

        if(subscriptionForm.getStartDate().after(subscriptionForm.getEndDate())){
            err.add(SubscriptionErrorConstant.START_DATE_ERROR);
        }

        if(TimeUnit.DAYS.convert(subscriptionForm.getEndDate().getTime() -subscriptionForm.getStartDate().getTime(), TimeUnit.MILLISECONDS ) > 90){
            err.add(SubscriptionErrorConstant.MAX_SUBS_DAYS_ERROR);
        }

        if(subscriptionForm.getAmount().compareTo(BigDecimal.ZERO) <=0){
            err.add(SubscriptionErrorConstant.GREATER_THAN_ZERO_ERROR);
        }
        return err;
    }
}
