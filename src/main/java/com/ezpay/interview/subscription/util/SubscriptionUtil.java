package com.ezpay.interview.subscription.util;

import com.ezpay.interview.subscription.constant.SubscriptionType;

import java.util.Date;
import java.util.List;

public final class SubscriptionUtil {

    // Assuming DAILY is 1 day, WEEKLY is 7 day and MONTHLY is 30day
    public static List<Date> getSubscriptionInBetweenDate(SubscriptionType subscriptionType,Date startDate, Date endDate){
        if(SubscriptionType.DAILY.equals(subscriptionType)){
            return getInBetweenDateDaily(startDate, endDate);
        }else if(SubscriptionType.WEEKLY.equals(subscriptionType)){
            return getInBetweenDateWeekly(startDate, endDate);
        }else if(SubscriptionType.MONTHLY.equals(subscriptionType)){
            return getInBetweenDateMonthly(startDate, endDate);
        }
        return null;
    }

    private static List<Date> getInBetweenDateDaily(Date startDate, Date endDate){
        return DateTimeUtil.getDateInBetweenDate(startDate, endDate, 1);
    }

    private static List<Date> getInBetweenDateWeekly(Date startDate, Date endDate){
        return DateTimeUtil.getDateInBetweenDate(startDate, endDate, 7);
    }

    private static List<Date> getInBetweenDateMonthly(Date startDate, Date endDate){
        return DateTimeUtil.getDateInBetweenDate(startDate, endDate, 30);
    }




}
