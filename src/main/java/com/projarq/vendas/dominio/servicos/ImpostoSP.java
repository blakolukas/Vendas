package com.projarq.vendas.dominio.servicos;

import com.projarq.vendas.dominio.entidades.PedidoModel;

public class ImpostoSP implements ImpostoStrategy {
    @Override
    public double calcularImposto(double valorBase, PedidoModel pedido) {
        return valorBase * 0.12;
    }
}
