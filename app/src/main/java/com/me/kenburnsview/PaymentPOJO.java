package com.me.kenburnsview;

/**
 * Created by HULK on 11/26/2017.
 */

public class PaymentPOJO {

    String paymentDate;
    String paymentId;
    String from;
    String to;
    double amount;
    int paymentStatus;

    PaymentPOJO(){}

    PaymentPOJO(String paymentId,String from,String to,double amount,int paymentStatus)
    {
        this.paymentId= paymentId;
        this.from=from;
        this.to=to;
        this.amount=amount;
        this.paymentStatus=paymentStatus;
    }

    public double getAmount() {
        return amount;
    }

    public int getPaymentStatus() {
        return paymentStatus;
    }

    public String getFrom() {
        return from;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public String getTo() {
        return to;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public void setPaymentStatus(int paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public void setTo(String to) {
        this.to = to;
    }
}

