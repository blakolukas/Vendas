package com.projarq.registrovendas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class RegistroVendasServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(RegistroVendasServiceApplication.class, args);
    }
}

