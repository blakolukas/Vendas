package com.projarq.vendas.dominio.servicos.descontos;

import com.projarq.vendas.dominio.entidades.OrcamentoModel;

public class DescontoMaisDezItensStrategy implements DescontoStrategy {
    
    private static final double PERCENTUAL_DESCONTO = 0.10; // 10%
    private static final int MINIMO_ITENS = 10;
    
    @Override
    public double calcularDesconto(OrcamentoModel orcamento) {
        if (!aplicavel(orcamento)) {
            return 0.0;
        }
        
        return orcamento.getCustoItens() * PERCENTUAL_DESCONTO;
    }
    
    @Override
    public String getDescricao() {
        return "Desconto de 10% para pedidos com mais de 10 itens";
    }
    
    @Override
    public boolean aplicavel(OrcamentoModel orcamento) {
        int totalItens = orcamento.getItens().stream()
            .mapToInt(item -> item.getQuantidade())
            .sum();
        
        return totalItens > MINIMO_ITENS;
    }
}

