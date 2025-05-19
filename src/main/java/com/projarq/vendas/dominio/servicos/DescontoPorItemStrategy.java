package com.projarq.vendas.dominio.servicos;

import com.projarq.vendas.dominio.entidades.OrcamentoModel;
import com.projarq.vendas.dominio.entidades.PedidoModel;
import com.projarq.vendas.dominio.entidades.ItemPedidoModel;

public class DescontoPorItemStrategy implements DescontoStrategy {
    @Override
    public double calcularDesconto(OrcamentoModel orcamento, PedidoModel pedido) {
        double desconto = 0.0;
        for (ItemPedidoModel item : orcamento.getItens()) {
            if (item.getQuantidade() > 3) {
                desconto += item.getProduto().getPrecoUnitario() * item.getQuantidade() * 0.05;
            }
        }
        return desconto;
    }
}