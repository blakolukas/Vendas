package com.projarq.vendas.dominio.servicos.descontos;

import com.projarq.vendas.dominio.entidades.OrcamentoModel;

public interface DescontoStrategy {
    double calcularDesconto(OrcamentoModel orcamento);
    String getDescricao();
    boolean aplicavel(OrcamentoModel orcamento);
}

