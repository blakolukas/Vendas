package com.projarq.vendas.dominio.servicos;

import com.projarq.vendas.dominio.entidades.OrcamentoModel;
import com.projarq.vendas.dominio.entidades.PedidoModel;

public interface DescontoStrategy {
    double calcularDesconto(OrcamentoModel orcamento, PedidoModel pedido);
}