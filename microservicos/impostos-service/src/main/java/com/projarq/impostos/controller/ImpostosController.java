package com.projarq.impostos.controller;

import com.projarq.impostos.dto.CalculoImpostoRequest;
import com.projarq.impostos.dto.CalculoImpostoResponse;
import com.projarq.impostos.service.ImpostosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/impostos")
public class ImpostosController {

    @Autowired
    private ImpostosService impostosService;

    @PostMapping("/calcular")
    public ResponseEntity<CalculoImpostoResponse> calcularImpostos(@RequestBody CalculoImpostoRequest request) {
        CalculoImpostoResponse response = impostosService.calcularImpostos(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("Microserviço de Impostos está funcionando!");
    }
}

