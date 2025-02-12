package com.awsravi.asyncannotationinspringboot.async_completablefuture;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OrderInventoryService {
    public boolean checkProductAvailability(int productId) {
        return true;

    }

}
