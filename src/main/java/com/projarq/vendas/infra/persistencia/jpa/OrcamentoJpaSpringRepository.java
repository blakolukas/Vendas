package com.projarq.vendas.infra.persistencia.jpa;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projarq.vendas.dominio.entidades.OrcamentoModel;

public interface OrcamentoJpaSpringRepository
        extends JpaRepository<OrcamentoModel, Long> {

    List<OrcamentoModel> findByEfetivadoTrueAndDataCriacaoBetween(
            LocalDate inicio, LocalDate fim);
}
