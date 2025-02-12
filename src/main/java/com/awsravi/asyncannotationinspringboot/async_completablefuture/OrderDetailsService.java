package com.awsravi.asyncannotationinspringboot.async_completablefuture;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

@Service
@Slf4j
public class OrderDetailsService {
    @Autowired
    private OrderInventoryService inventoryService;

    @Autowired
    private OrderPaymentService paymentService;

    @Autowired
    Executor asyncExecutor;

    public Order processOrder(Order order) throws InterruptedException {
        order.setTrackingId(UUID.randomUUID().toString());
        if(inventoryService.checkProductAvailability(order.getProductId())){
            paymentService.processPayment(order);
        }else {
            throw new RuntimeException("Product is not available");
        }
        return order;
    }
    @Async("asyncExecutor")
    public CompletableFuture<Void> notifyOrder(Order order) {
        return CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(4000);
                log.info("Order notifyOrder : " + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                log.error("Order notifyOrder interrupted", e);
            }
        },asyncExecutor);
    }

    @Async("asyncExecutor")
    public CompletableFuture<Void> assignVendor(Order order)  {
        return CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(4000);
                log.info("Order assignVendor : " + Thread.currentThread().getName());
            }catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                log.error("Order assigningVendor interrupted", e);
            }
        },asyncExecutor);
    }

    @Async("asyncExecutor")
    public CompletableFuture<Void> packingOrder(Order order) {
        return CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(4000);
                log.info("Order packing : " + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                log.error("Order packing interrupted", e);
            }
        }, asyncExecutor);

    }

    @Async("asyncExecutor")
    public CompletableFuture<Void> assignDeliveryPartner(Order order) {
        return CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(4000);
                log.info("Order assignDeliveryPartner : " + Thread.currentThread().getName());

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                log.error("Order assignDeliveryPartner interrupted ", e);
            }
        }, asyncExecutor);
    }

    @Async("asyncExecutor")
    public CompletableFuture<Void> assignTrailerAndDispatch(Order order){
        return CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(4000);
                log.info("Order assignTrailerAndDispatch : " + Thread.currentThread().getName());
            }catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                log.error("Order assignTrailerAndDispatch interrupted ", e);
            }
        }, asyncExecutor);
    }
}
