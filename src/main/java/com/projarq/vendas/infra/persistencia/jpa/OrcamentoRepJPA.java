package com.projarq.vendas.infra.persistencia.jpa;

import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.projarq.vendas.dominio.entidades.OrcamentoModel;
import com.projarq.vendas.dominio.portas.IOrcamentoRepositorio;

@Repository
@Primary
@Transactional
public class OrcamentoRepJPA implements IOrcamentoRepositorio {

    private final OrcamentoJpaSpringRepository repo;

    public OrcamentoRepJPA(OrcamentoJpaSpringRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<OrcamentoModel> todos() {
        return repo.findAll();
    }

    @Override
    public OrcamentoModel cadastra(OrcamentoModel orcamento) {
        return repo.save(orcamento);
    }

    @Override
    public OrcamentoModel recuperaPorId(long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public void marcaComoEfetivado(long id) {
        repo.findById(id).ifPresent(o -> {
            o.setEfetivado(true);
            repo.save(o);
        });
    }
}
