package com.projarq.vendas.dominio.servicos.impostos;

import com.projarq.vendas.dominio.entidades.OrcamentoModel;

public interface ImpostoStrategy {
    double calcularImposto(double valorBase, OrcamentoModel orcamento);
    String getDescricao();
}

