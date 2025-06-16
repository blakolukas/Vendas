package com.projarq.vendas.dominio.servicos.descontos;

import com.projarq.vendas.dominio.entidades.OrcamentoModel;

public class DescontoMaisTresItensStrategy implements DescontoStrategy {
    
    private static final double PERCENTUAL_DESCONTO = 0.05; // 5%
    private static final int MINIMO_ITENS = 3;
    
    @Override
    public double calcularDesconto(OrcamentoModel orcamento) {
        if (!aplicavel(orcamento)) {
            return 0.0;
        }
        
        return orcamento.getCustoItens() * PERCENTUAL_DESCONTO;
    }
    
    @Override
    public String getDescricao() {
        return "Desconto de 5% para pedidos com mais de 3 itens";
    }
    
    @Override
    public boolean aplicavel(OrcamentoModel orcamento) {
        int totalItens = orcamento.getItens().stream()
            .mapToInt(item -> item.getQuantidade())
            .sum();
        
        return totalItens > MINIMO_ITENS;
    }
}

