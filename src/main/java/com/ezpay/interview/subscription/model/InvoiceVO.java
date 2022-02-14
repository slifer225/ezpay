package com.ezpay.interview.subscription.model;

import com.ezpay.interview.subscription.constant.DateTimeConstant;
import com.ezpay.interview.subscription.constant.SubscriptionType;
import com.ezpay.interview.subscription.util.SubscriptionUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class InvoiceVO  implements Serializable {

    private BigDecimal amount;

    private SubscriptionType subscriptionType;

    @JsonFormat(pattern = DateTimeConstant.INVOICE_DATE_FORMAT)
    private List<Date> invoiceDates;

    @JsonIgnore
    private Date startDate;

    @JsonIgnore
    private Date endDate;

    public InvoiceVO(){}

    public InvoiceVO(SubscriptionForm subscriptionForm){
        this.amount = subscriptionForm.getAmount();
        this.subscriptionType = subscriptionForm.getSubscriptionType();
        this.startDate = subscriptionForm.getStartDate();
        this.endDate = subscriptionForm.getEndDate();
        this.setInvoiceDates();
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

    public List<Date> getInvoiceDates() {
        return invoiceDates;
    }

    private void setInvoiceDates() {
        this.invoiceDates = SubscriptionUtil.getSubscriptionInBetweenDate(this.subscriptionType, this.startDate, this.endDate);
    }
}
