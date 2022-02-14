package com.ezpay.interview.subscription;

import com.ezpay.interview.subscription.constant.SubscriptionErrorConstant;
import com.ezpay.interview.subscription.constant.SubscriptionType;
import com.ezpay.interview.subscription.model.InvoiceVO;
import com.ezpay.interview.subscription.model.ResponseDTO;
import com.ezpay.interview.subscription.model.SubscriptionForm;
import com.ezpay.interview.subscription.restcontroller.SubscriptionRestController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

@SpringBootTest
class SubscriptionApplicationTests {

    private static final String CREATE_SUBSCRIPTION_ENDPOINT = "http://localhost:8080/subscription";

    private Logger logger = LogManager.getLogger(SubscriptionApplicationTests.class);

    @Autowired
    private SubscriptionRestController subscriptionRestController;

    private Date getCorrectStartDate(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(2022 ,Calendar.JANUARY ,1);
        return calendar.getTime();
    }

    private Date getCorrectEndDate(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(2022,Calendar.MARCH,30);
        return calendar.getTime();
    }

    private Date getWrongStartDate(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(2022,Calendar.MARCH,30);
        return calendar.getTime();
    }

    private Date getWrongEndDate(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(2022,Calendar.JANUARY,1);
        return calendar.getTime();
    }

    private Date getWrongStartDateMax(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(2021,Calendar.JANUARY,1);
        return calendar.getTime();
    }

    private Date getWrongEndDateMax(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(2022,Calendar.JANUARY,1);
        return calendar.getTime();
    }

    private SubscriptionForm getSuccessSubscriptionForm(SubscriptionType subscriptionType){
        SubscriptionForm subscriptionForm = new SubscriptionForm(new BigDecimal(120), subscriptionType, getCorrectStartDate(), getCorrectEndDate());
        return subscriptionForm;
    }

    private SubscriptionForm getFailWrongDateSubscriptionForm(){
        SubscriptionForm subscriptionForm = new SubscriptionForm(new BigDecimal(120), SubscriptionType.WEEKLY, getWrongStartDate(), getWrongEndDate());
        return subscriptionForm;
    }

    private SubscriptionForm getFailZeroAmountSubscriptionForm(){
        SubscriptionForm subscriptionForm = new SubscriptionForm(new BigDecimal(0), SubscriptionType.WEEKLY, getCorrectStartDate(), getCorrectEndDate());
        return subscriptionForm;
    }

    private SubscriptionForm getFailMaxSubscriptionDaySubscriptionForm(){
        SubscriptionForm subscriptionForm = new SubscriptionForm(new BigDecimal(120), SubscriptionType.WEEKLY, getWrongStartDateMax(), getWrongEndDateMax());
        return subscriptionForm;
    }

    private  ResponseEntity<ResponseDTO<InvoiceVO>> submitCreateSubscriptionRequest(HttpEntity httpEntity){
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.exchange(CREATE_SUBSCRIPTION_ENDPOINT, HttpMethod.POST, httpEntity, new ParameterizedTypeReference<ResponseDTO<InvoiceVO>>() {});
    }

    @Test
    public void testSubscriptionSuccess(){
        ResponseDTO<InvoiceVO> response = subscriptionRestController.createSubscription(getSuccessSubscriptionForm(SubscriptionType.DAILY));
        Assertions.assertEquals(true, response.getStatusFlag());
    }

    @Test
    public void testSubscriptionFailDateError(){
        ResponseDTO<InvoiceVO> response = subscriptionRestController.createSubscription(getFailWrongDateSubscriptionForm());
        Assertions.assertEquals(false, response.getStatusFlag());
        Assertions.assertEquals(SubscriptionErrorConstant.START_DATE_ERROR, response.getMessage().get(0));
    }

    @Test
    public void testSubscriptionFailNoAmountError(){
        ResponseDTO<InvoiceVO> response = subscriptionRestController.createSubscription(getFailZeroAmountSubscriptionForm());
        Assertions.assertEquals(false, response.getStatusFlag());
        Assertions.assertEquals(SubscriptionErrorConstant.GREATER_THAN_ZERO_ERROR, response.getMessage().get(0));
    }

    @Test
    public void testSubscriptionMaxSubsDaysError(){
        ResponseDTO<InvoiceVO> response = subscriptionRestController.createSubscription(getFailMaxSubscriptionDaySubscriptionForm());
        logger.info(response.getMessage().get(0));
        Assertions.assertEquals(false, response.getStatusFlag());
        Assertions.assertEquals(SubscriptionErrorConstant.MAX_SUBS_DAYS_ERROR, response.getMessage().get(0));
    }

    @Test
    public void testDailySubscriptionSuccess(){
        ResponseDTO<InvoiceVO> response = subscriptionRestController.createSubscription(getSuccessSubscriptionForm(SubscriptionType.DAILY));
        Assertions.assertEquals(true, response.getStatusFlag());
        Assertions.assertEquals(88,response.getResult().getInvoiceDates().size());
    }

    @Test
    public void testWeeklySubscriptionSuccess(){
        ResponseDTO<InvoiceVO> response = subscriptionRestController.createSubscription(getSuccessSubscriptionForm(SubscriptionType.WEEKLY));
        Assertions.assertEquals(true, response.getStatusFlag());
        Assertions.assertEquals(13,response.getResult().getInvoiceDates().size());
    }

    @Test
    public void testMonthlySubscriptionSuccess(){
        ResponseDTO<InvoiceVO> response = subscriptionRestController.createSubscription(getSuccessSubscriptionForm(SubscriptionType.MONTHLY));
        Assertions.assertEquals(true, response.getStatusFlag());
        Assertions.assertEquals(3,response.getResult().getInvoiceDates().size());
    }
}
