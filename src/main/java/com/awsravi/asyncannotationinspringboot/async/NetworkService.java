package com.awsravi.asyncannotationinspringboot.async;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class NetworkService {
    @Async
    public void display() throws InterruptedException {
        try {
            Thread.sleep(2000);
        }catch (InterruptedException e) {
            throw new InterruptedException();
        }
        System.out.println("NetworkService By : "+Thread.currentThread().getName());

    }
}
