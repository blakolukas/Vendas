package com.projarq.vendas.dominio.entidades;

// Interface para estratégia de desconto
public interface DescontoStrategy {
    double calcularDesconto(double valorBase, PedidoModel pedido);
}
