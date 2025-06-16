package com.projarq.impostos.service;

import com.projarq.impostos.dto.CalculoImpostoRequest;
import com.projarq.impostos.dto.CalculoImpostoResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ImpostosService {

    @Value("${server.port:8080}")
    private String serverPort;

    @Value("${spring.application.instance_id:unknown}")
    private String instanceId;

    public CalculoImpostoResponse calcularImpostos(CalculoImpostoRequest request) {
        double impostoEstadual = calcularImpostoEstadual(request.getValorBase(), request.getEstado());
        double impostoFederal = calcularImpostoFederal(request.getValorBase());
        
        String instancia = String.format("Instância: %s - Porta: %s", instanceId, serverPort);
        
        return new CalculoImpostoResponse(
            impostoEstadual, 
            impostoFederal, 
            "Imposto Estadual (ICMS)", 
            "Impostos Federais (PIS/COFINS)",
            instancia
        );
    }

    private double calcularImpostoEstadual(double valorBase, String estado) {
        switch (estado.toUpperCase()) {
            case "SP":
                return valorBase * 0.18; // 18% ICMS SP
            case "RJ":
                return valorBase * 0.20; // 20% ICMS RJ
            case "RS":
                return valorBase * 0.17; // 17% ICMS RS
            case "MG":
                return valorBase * 0.18; // 18% ICMS MG
            case "PR":
                return valorBase * 0.18; // 18% ICMS PR
            case "SC":
                return valorBase * 0.17; // 17% ICMS SC
            case "BA":
                return valorBase * 0.18; // 18% ICMS BA
            case "GO":
                return valorBase * 0.17; // 17% ICMS GO
            case "DF":
                return valorBase * 0.18; // 18% ICMS DF
            default:
                return valorBase * 0.17; // 17% ICMS padrão para outros estados
        }
    }

    private double calcularImpostoFederal(double valorBase) {
        return valorBase * 0.0925; // 9,25% (PIS + COFINS)
    }
}

