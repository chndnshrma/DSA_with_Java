package com.chndn.sharma.learningSpringBootApp;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "payment.provider", havingValue = "stripe")
public class StripPayment implements  paymentService{

    @Override
    public String pay() {
        String payment = "Stripe Pay";
        System.out.println("Payment from: " + payment);
        return payment;
    }
}
