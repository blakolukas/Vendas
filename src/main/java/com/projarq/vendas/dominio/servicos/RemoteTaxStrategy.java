package com.projarq.vendas.dominio.servicos;

import org.springframework.stereotype.Component;

import com.projarq.vendas.aplicacao.client.TaxesServiceClient;
import com.projarq.vendas.aplicacao.dtos.TaxCalculationRequestDTO;
import com.projarq.vendas.dominio.entidades.PedidoModel;

@Component
public class RemoteTaxStrategy implements ImpostoStrategy {
    
    private final TaxesServiceClient taxesServiceClient;
    
    public RemoteTaxStrategy(TaxesServiceClient taxesServiceClient) {
        this.taxesServiceClient = taxesServiceClient;
    }
    
    @Override
    public double calcularImposto(double valorBase, PedidoModel pedido) {
        try {
            TaxCalculationRequestDTO request = new TaxCalculationRequestDTO(
                pedido.getEstado(), 
                valorBase
            );
            
            Double result = taxesServiceClient.calculateTax(request);
            
            if (result == null) {
                // Fallback to default calculation if service returns null
                return valorBase * 0.1;
            }
            
            // The TaxesService now returns just the tax amount
            return result;
            
        } catch (Exception e) {
            // Log the error and fallback to default calculation
            System.err.println("Erro ao comunicar com TaxesService: " + e.getMessage());
            return valorBase * 0.1;
        }
    }
} 