package com.awsravi.asyncannotationinspringboot.asyncannotation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OrderPaymentService {

    public void processPayment(Order order) throws InterruptedException {
        log.info("Processing payment for order... "+ order.getProductId());
        Thread.sleep(4000);
        log.info("completed payment for order... "+ order.getProductId());
    }
}
