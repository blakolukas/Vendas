package com.projarq.registrovendas.controller;

import com.projarq.registrovendas.dto.RelatorioMensalResponse;
import com.projarq.registrovendas.service.RegistroVendasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/registro-vendas")
public class RegistroVendasController {

    @Autowired
    private RegistroVendasService registroVendasService;

    @GetMapping("/relatorio/{ano}/{mes}")
    public ResponseEntity<RelatorioMensalResponse> obterRelatorioMensal(
            @PathVariable int ano, 
            @PathVariable int mes) {
        
        if (mes < 1 || mes > 12) {
            return ResponseEntity.badRequest().build();
        }
        
        RelatorioMensalResponse relatorio = registroVendasService.obterRelatorioMensal(mes, ano);
        return ResponseEntity.ok(relatorio);
    }

    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("Microserviço de Registro de Vendas está funcionando!");
    }
}

