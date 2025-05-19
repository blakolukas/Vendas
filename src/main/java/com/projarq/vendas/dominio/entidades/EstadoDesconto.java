package com.projarq.vendas.dominio.entidades;

public enum EstadoDesconto {
    SP(new DescontoSP()),
    RJ(new DescontoRJ());
    // Adicione outros estados conforme necessário

    private final DescontoStrategy strategy;

    EstadoDesconto(DescontoStrategy strategy) {
        this.strategy = strategy;
    }

    public double calcularDesconto(double valorBase, PedidoModel pedido) {
        return strategy.calcularDesconto(valorBase, pedido);
    }
}
