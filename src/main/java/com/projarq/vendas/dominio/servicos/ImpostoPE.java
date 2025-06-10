package com.projarq.vendas.dominio.servicos;

import com.projarq.vendas.dominio.entidades.ItemPedidoModel;
import com.projarq.vendas.dominio.entidades.PedidoModel;

public class ImpostoPE implements ImpostoStrategy {
    @Override
    public double calcularImposto(double valorBase, PedidoModel pedido) {
        double impostoEstadual = 0.0;
        for (ItemPedidoModel item : pedido.getItens()) {
            if (item.getProduto().getDescricao().endsWith("*")) { // Produto essencial
                impostoEstadual += item.getProduto().getPrecoUnitario() * item.getQuantidade() * 0.05; // 5% para
                                                                                                       // essenciais
            } else {
                impostoEstadual += item.getProduto().getPrecoUnitario() * item.getQuantidade() * 0.15; // 15% para os
                                                                                                       // demais
            }
        }
        return impostoEstadual;
    }
}