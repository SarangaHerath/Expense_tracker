package com.saara.expenseservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.saara.categoryservice.entity")
public class ExpenseServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExpenseServiceApplication.class, args);
    }

}
