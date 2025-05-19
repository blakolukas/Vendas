package com.projarq.vendas.dominio.servicos;

import com.projarq.vendas.dominio.entidades.OrcamentoModel;
import com.projarq.vendas.dominio.entidades.PedidoModel;

public class DescontoPorTotalItensStrategy implements DescontoStrategy {
    @Override
    public double calcularDesconto(OrcamentoModel orcamento, PedidoModel pedido) {
        int totalItens = orcamento.getItens().stream().mapToInt(it -> it.getQuantidade()).sum();
        if (totalItens > 10) {
            double total = orcamento.getItens().stream()
                .mapToDouble(it -> it.getProduto().getPrecoUnitario() * it.getQuantidade())
                .sum();
            return total * 0.10;
        }
        return 0.0;
    }
}