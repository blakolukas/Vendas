package com.projarq.vendas.dominio.servicos;

import com.projarq.vendas.dominio.entidades.PedidoModel;

public enum EstadoImposto {
    SP(new ImpostoSP()),
    RS(new ImpostoRS());
    // Adicione outros estados conforme necessário

    private final ImpostoStrategy strategy;

    EstadoImposto(ImpostoStrategy strategy) {
        this.strategy = strategy;
    }

    public double calcularImposto(double valorBase, PedidoModel pedido) {
        return strategy.calcularImposto(valorBase, pedido);
    }
}
