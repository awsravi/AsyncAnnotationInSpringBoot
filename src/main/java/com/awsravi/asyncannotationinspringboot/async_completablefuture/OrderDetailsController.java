package com.awsravi.asyncannotationinspringboot.async_completablefuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/completablefuture")
@EnableAsync
public class OrderDetailsController {
    @Autowired
    private OrderDetailsService orderDetailsService;

    @PostMapping("/order")
    public ResponseEntity<Order> orderProcess(@RequestBody Order order) throws InterruptedException, ExecutionException {
        order = orderDetailsService.processOrder(order);

        CompletableFuture<Void> allFutures = CompletableFuture.allOf(
                orderDetailsService.notifyOrder(order),
                orderDetailsService.assignVendor(order),
                orderDetailsService.packingOrder(order),
                orderDetailsService.assignDeliveryPartner(order),
                orderDetailsService.assignTrailerAndDispatch(order)
        );

        allFutures.join(); // Wait for all asynchronous tasks to complete

        return ResponseEntity.ok(order);
    }
}
