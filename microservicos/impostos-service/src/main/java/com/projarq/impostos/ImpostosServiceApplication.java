package com.projarq.impostos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ImpostosServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ImpostosServiceApplication.class, args);
    }
}

