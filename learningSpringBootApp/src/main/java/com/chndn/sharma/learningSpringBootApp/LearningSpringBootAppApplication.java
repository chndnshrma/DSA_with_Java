package com.chndn.sharma.learningSpringBootApp;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LearningSpringBootAppApplication implements CommandLineRunner {

	public static void main(String[] args) {
        SpringApplication.run(LearningSpringBootAppApplication.class, args);
	}
    private paymentService paymentService;
    public LearningSpringBootAppApplication(paymentService paymentService) {
        this.paymentService = paymentService;
    }
    @Override
    public void run(String... args) throws Exception {
        String payment = paymentService.pay();
        System.out.println("Payment Done!");
    }
}
