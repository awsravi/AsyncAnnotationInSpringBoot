package com.awsravi.asyncannotationinspringboot.asyncwith_returntype;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/v1")
@EnableAsync
public class AsyncWith_ReturnTypeController {

    @Autowired
    NetworkService networkService;
    @Autowired
    DatabaseService databaseService;
    @Autowired
    BatchService batchService;

    @GetMapping("/taskwithtype")
    public CompletableFuture<String> display() throws InterruptedException {

        CompletableFuture<String> networkResult = networkService.display();

        CompletableFuture<String> databaseResult = databaseService.display();

        CompletableFuture<String> batchResult = batchService.display();


        return CompletableFuture.allOf(networkResult, databaseResult, batchResult)
                .thenApply(v -> {
                    try {
                        String network = networkResult.get();
                        String database = databaseResult.get();
                        String batch = batchResult.get();
                        return "Async Example with  Return Type CompletableFuture : \n" + network + "\n" + database + "\n" + batch;
                    }catch (Exception e) {
                            return "Error: " + e.getMessage(); // Handle exceptions
                        }
                });
    }
}
