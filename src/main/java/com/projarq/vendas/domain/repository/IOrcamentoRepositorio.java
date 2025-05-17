package com.projarq.vendas.domain.repository;

import java.util.List;

import com.projarq.vendas.domain.model.Orcamento;

public interface IOrcamentoRepositorio {
    List<Orcamento> todos();
    Orcamento cadastra(Orcamento orcamento);
    Orcamento recuperaPorId(long id);
    void marcaComoEfetivado(long id); 
}
