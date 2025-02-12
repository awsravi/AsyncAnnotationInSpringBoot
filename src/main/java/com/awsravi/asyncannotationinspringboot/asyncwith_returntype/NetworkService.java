package com.awsravi.asyncannotationinspringboot.asyncwith_returntype;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class NetworkService {
    private static final Logger log = LoggerFactory.getLogger(NetworkService.class);

    @Async("taskExecutor")
    public CompletableFuture<String> display() throws InterruptedException {
        try {
            Thread.sleep(2000);
           return  CompletableFuture.completedFuture("NetworkService By : " + Thread.currentThread().getName());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return CompletableFuture.failedFuture(e);


        }
    }
    @Async
    public void testAsyncExecution() throws InterruptedException {
        log.info(Thread.currentThread().getName());
    }
}
