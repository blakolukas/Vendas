package com.projarq.vendas.dominio.interfRepositorios;

import java.util.List;

import com.projarq.vendas.dominio.entidades.OrcamentoModel;

public interface IOrcamentoRepositorio {
    List<OrcamentoModel> todos();
    OrcamentoModel cadastra(OrcamentoModel orcamento);
    OrcamentoModel recuperaPorId(long id);
    void marcaComoEfetivado(long id); 
}
