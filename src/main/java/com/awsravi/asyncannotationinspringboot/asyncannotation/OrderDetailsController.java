package com.awsravi.asyncannotationinspringboot.asyncannotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@EnableAsync
public class OrderDetailsController {
    @Autowired
    private OrderDetailsService orderDetailsService;

    @PostMapping("/order")
    public ResponseEntity<Order> orderProcess(@RequestBody Order order ) throws InterruptedException {
    orderDetailsService.processOrder(order);
    orderDetailsService.notifyOrder(order);
    orderDetailsService.assignVendor(order);
    orderDetailsService.packingOrder(order);
    orderDetailsService.assignDeliveryPartner(order);
    orderDetailsService.assignTrailerAndDispatch(order);

    return ResponseEntity.ok(order);

    }
}
