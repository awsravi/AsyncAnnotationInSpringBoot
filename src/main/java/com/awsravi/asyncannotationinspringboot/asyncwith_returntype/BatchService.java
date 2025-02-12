package com.awsravi.asyncannotationinspringboot.asyncwith_returntype;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class BatchService {
     @Async("taskExecutor")
    public CompletableFuture<String> display() throws InterruptedException {
       try{
           Thread.sleep(1000);
           return CompletableFuture.completedFuture("BatchService By : " + Thread.currentThread().getName());
       }catch (InterruptedException e){
           Thread.currentThread().interrupt();
           return CompletableFuture.failedFuture(e);

       }

    }
}
