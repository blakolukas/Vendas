package com.projarq.vendas.dominio.servicos;

import com.projarq.vendas.dominio.entidades.PedidoModel;

public enum EstadoImposto {
    SP(new ImpostoSP()),
    RS(new ImpostoRS()),
    PE(new ImpostoPE()); // <--- Adicionado Pernambuco

    private final ImpostoStrategy strategy;

    EstadoImposto(ImpostoStrategy strategy) {
        this.strategy = strategy;
    }

    public double calcularImposto(double valorBase, PedidoModel pedido) {
        return strategy.calcularImposto(valorBase, pedido);
    }
}