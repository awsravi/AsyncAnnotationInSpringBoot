package com.awsravi.asyncannotationinspringboot.asyncannotation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class OrderDetailsService {
    @Autowired
    private OrderInventoryService inventoryService;

    @Autowired
    private OrderPaymentService paymentService;

    public  Order processOrder(Order order) throws InterruptedException {
        order.setTrackingId(UUID.randomUUID().toString());
        if(inventoryService.checkProductAvailability(order.getProductId())){
            paymentService.processPayment(order);
        }else {
            throw new RuntimeException("Product is not available");
        }
        return order;
    }
    @Async("asyncExecutor")
    public void notifyOrder(Order order) throws InterruptedException {
        Thread.sleep(4000);
        log.info("Notified to the user "+ Thread.currentThread().getName());
    }

    @Async("asyncExecutor")
    public void assignVendor(Order order) throws InterruptedException {
        Thread.sleep(4000);
        log.info("Assigning vendor "+ Thread.currentThread().getName());
    }

    @Async("asyncExecutor")
    public void packingOrder(Order order) throws InterruptedException {
        Thread.sleep(4000);
        log.info("Packing order "+ Thread.currentThread().getName());
    }

    @Async("asyncExecutor")
    public void assignDeliveryPartner(Order order) throws InterruptedException {
        Thread.sleep(4000);
        log.info("Assigning delivery partner"+ Thread.currentThread().getName());
    }

    @Async("asyncExecutor")
    public void assignTrailerAndDispatch(Order order) throws InterruptedException {
        Thread.sleep(4000);
        log.info("Assigning trailer and dispatch"+ Thread.currentThread().getName());
    }

}
