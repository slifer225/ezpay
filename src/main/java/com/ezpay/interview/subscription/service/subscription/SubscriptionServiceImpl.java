package com.ezpay.interview.subscription.service.subscription;

import com.ezpay.interview.subscription.model.InvoiceVO;
import com.ezpay.interview.subscription.model.SubscriptionForm;
import org.springframework.stereotype.Service;

@Service
public class SubscriptionServiceImpl implements  SubscriptionService{

    @Override
    public InvoiceVO generateInvoice(SubscriptionForm subscriptionForm) {
        return new InvoiceVO(subscriptionForm);
    }
}
