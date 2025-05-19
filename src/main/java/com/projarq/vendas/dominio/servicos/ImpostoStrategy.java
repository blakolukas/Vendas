package com.projarq.vendas.dominio.servicos;

import com.projarq.vendas.dominio.entidades.PedidoModel;

// Interface para estratégia de desconto
public interface ImpostoStrategy {
    double calcularImposto(double valorBase, PedidoModel pedido);
}
