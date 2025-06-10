package com.projarq.vendas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan; // <--- Importação adicionada
import org.springframework.data.jpa.repository.config.EnableJpaRepositories; // <--- Importação adicionada

@SpringBootApplication
@EnableJpaRepositories("com.projarq.vendas.dominio.interfRepositorios") // <--- Adicionado
@EntityScan("com.projarq.vendas.dominio.entidades") // <--- Adicionado
public class VendasApplication {

	public static void main(String[] args) {
		SpringApplication.run(VendasApplication.class, args);
	}

}