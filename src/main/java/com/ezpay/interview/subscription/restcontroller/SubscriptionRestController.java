package com.ezpay.interview.subscription.restcontroller;

import com.ezpay.interview.subscription.model.InvoiceVO;
import com.ezpay.interview.subscription.model.ResponseDTO;
import com.ezpay.interview.subscription.model.SubscriptionForm;
import com.ezpay.interview.subscription.service.subscription.SubscriptionService;
import com.ezpay.interview.subscription.util.ResponseUtil;
import com.ezpay.interview.subscription.validator.SubscriptionFormValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/subscription")
public class SubscriptionRestController {

    @Autowired
    private SubscriptionService subscriptionService;

    private Logger logger = LogManager.getLogger(SubscriptionRestController.class);

    @PostMapping
    public ResponseDTO<InvoiceVO> createSubscription(@RequestBody SubscriptionForm subscriptionForm){
        try {
            logger.info("createSubscription start : " + new Date());
            List<String> errList = SubscriptionFormValidator.validate(subscriptionForm);
            if(!CollectionUtils.isEmpty(errList)){
                logger.info("Validation failed");
                return ResponseUtil.onFail(errList);
            }
            InvoiceVO invoiceVO = subscriptionService.generateInvoice(subscriptionForm);
            logger.info("createSubscription end : " + new Date());
            return ResponseUtil.onSuccess(invoiceVO);
        }catch (Exception ex){
            logger.error("Exception createSubscription : " , ex);
            return ResponseUtil.onFail("Fail to create");
        }
    }
}
