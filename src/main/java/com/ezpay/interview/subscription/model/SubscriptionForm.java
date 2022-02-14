package com.ezpay.interview.subscription.model;

import com.ezpay.interview.subscription.constant.SubscriptionType;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class SubscriptionForm implements Serializable {

    private BigDecimal amount;

    private SubscriptionType subscriptionType;

    private Date startDate;

    private Date endDate;

    // Not sure what is the purpose of this
    private String dayOfWeek;
    private String dateOfMonth;
    public String getDayOfWeekOrMonth(){
        if(SubscriptionType.WEEKLY.equals(this.getSubscriptionType())){
            return this.dayOfWeek;
        }else if(SubscriptionType.MONTHLY.equals(this.getSubscriptionType())){
            return this.dateOfMonth;
        }
        return null;
    }

    public SubscriptionForm(){}

    public SubscriptionForm(BigDecimal amount, SubscriptionType subscriptionType, Date startDate, Date endDate){
        this.amount = amount;
        this.subscriptionType = subscriptionType;
        this.startDate = startDate;
        this.endDate = endDate;
    }



    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public SubscriptionType getSubscriptionType() {
        return subscriptionType;
    }

    public void setSubscriptionType(SubscriptionType subscriptionType) {
        this.subscriptionType = subscriptionType;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

//    public Boolean getSubscriptionDateValidity(){
//
//        int daysInBetween = this.getStartDate().compareTo(this.getEndDate());
//
//        if(SubscriptionType.DAILY.equals(this.subscriptionType)){
//            return true;
//        }else if(SubscriptionType.WEEKLY.equals(this.subscriptionType)){
//            return daysInBetween/7 == 0;
//        }else if(SubscriptionType.MONTHLY.equals(this.subscriptionType)){
//            return daysInBetween/30 == 0;
//        }else{
//            return false;
//        }
//    }

}
