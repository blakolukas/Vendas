package com.projarq.vendas.dominio.entidades;

public class DescontoSP implements DescontoStrategy {
    @Override
    public double calcularDesconto(double valorBase, PedidoModel pedido) {
        return valorBase * 0.07;
    }
}
