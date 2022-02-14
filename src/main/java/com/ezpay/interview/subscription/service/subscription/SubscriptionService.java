package com.ezpay.interview.subscription.service.subscription;

import com.ezpay.interview.subscription.model.InvoiceVO;
import com.ezpay.interview.subscription.model.SubscriptionForm;

public interface SubscriptionService {
    InvoiceVO generateInvoice(SubscriptionForm subscriptionForm);
}
