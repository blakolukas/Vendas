package com.projarq.vendas.interfaceAdaptadora.repositorios.implemRepositorios;

import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.projarq.vendas.dominio.entidades.OrcamentoModel;
import com.projarq.vendas.dominio.interfRepositorios.IOrcamentoRepositorio;

@Repository
@Primary
public class OrcamentoRepJPA implements IOrcamentoRepositorio {

    @Override
    public List<OrcamentoModel> todos() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'todos'");
    }

    @Override
    public OrcamentoModel cadastra(OrcamentoModel orcamento) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'cadastra'");
    }

    @Override
    public OrcamentoModel recuperaPorId(long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'recuperaPorId'");
    }

    @Override
    public void marcaComoEfetivado(long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'marcaComoEfetivado'");
    }
    
}
