package com.projarq.vendas.aplicacao.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.projarq.vendas.aplicacao.dtos.TaxCalculationRequestDTO;

@FeignClient(name = "TaxesService", url = "http://localhost:8002")
public interface TaxesServiceClient {
    
    @PostMapping("/api/taxes/calculate")
    Double calculateTax(@RequestBody TaxCalculationRequestDTO request);
} 