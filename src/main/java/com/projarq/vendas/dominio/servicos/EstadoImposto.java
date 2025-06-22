package com.projarq.vendas.dominio.servicos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.projarq.vendas.dominio.entidades.PedidoModel;

@Component
public class EstadoImposto {
    
    private static RemoteTaxStrategy remoteTaxStrategy;
    
    @Autowired
    public void setRemoteTaxStrategy(RemoteTaxStrategy remoteTaxStrategy) {
        EstadoImposto.remoteTaxStrategy = remoteTaxStrategy;
    }
    
    public static double calcularImposto(String estado, double valorBase, PedidoModel pedido) {
        if (remoteTaxStrategy == null) {
            // Fallback to default calculation if RemoteTaxStrategy is not available
            return valorBase * 0.1;
        }
        
        // Use the remote strategy for all states
        return remoteTaxStrategy.calcularImposto(valorBase, pedido);
    }
}
