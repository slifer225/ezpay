package com.ezpay.interview.subscription.util;

import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public final class DateTimeUtil {

    public static List<Date> getDateInBetweenDate(Date startDate, Date endDate, Integer interval){
        Calendar startDateCalendar= getCalendarFromDate(startDate);
        Calendar endDateCalendar = getCalendarFromDate(endDate);

        if(startDateCalendar.getTimeInMillis() > endDateCalendar.getTimeInMillis()){
            return null;
        }

        List<Date> dates = new LinkedList<>();

        while(startDateCalendar.getTimeInMillis() < endDateCalendar.getTimeInMillis()){
            dates.add(startDateCalendar.getTime());
            startDateCalendar.add(Calendar.DATE, interval);
        }
        return dates;
    }

    private static Calendar getCalendarFromDate(Date date){
        Calendar calendar= Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }
}
