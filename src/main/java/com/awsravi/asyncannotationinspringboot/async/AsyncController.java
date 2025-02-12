package com.awsravi.asyncannotationinspringboot.async;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@EnableAsync
public class AsyncController {

    @Autowired
    NetworkService networkService;
    @Autowired
    DatabaseService databaseService;
    @Autowired
    BatchService batchService;

    @GetMapping("/task")
    public String display() throws InterruptedException {

        networkService.display();
        databaseService.display();
        batchService.display();

        return "Async Example for awsravi!";
    }
}
