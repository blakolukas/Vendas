package com.projarq.vendas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableFeignClients
@EnableJpaRepositories(basePackages = "com.projarq.vendas.dominio.interfRepositorios")
@EntityScan(basePackages = "com.projarq.vendas.dominio.entidades")
@ComponentScan(basePackages = "com.projarq.vendas")
@EnableTransactionManagement
public class VendasApplication {

	public static void main(String[] args) {
		SpringApplication.run(VendasApplication.class, args);
	}

}
