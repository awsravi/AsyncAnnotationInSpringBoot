package com.awsravi.asyncannotationinspringboot.async;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class BatchService {
    @Async
    public void display() throws InterruptedException {
        try {
            Thread.sleep(2000);
        }catch (InterruptedException e) {
            throw new InterruptedException();
        }
        System.out.println("BatchService By : "+Thread.currentThread().getName());

    }
}
