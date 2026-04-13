package com.chndn.sharma.learningSpringBootApp;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "payment.provider", havingValue = "razorpay")
public class RazorPayPayment implements paymentService{
    @Override
    public String pay() {
        String payment = "Razor Pay";
        System.out.println("Payment from: " + payment);
        return payment;
    }
}
