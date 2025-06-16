package com.projarq.vendas.infra.persistencia.jpa;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projarq.vendas.dominio.entidades.OrcamentoModel;

/**
 * Repositório Spring Data JPA que lida com a entidade OrcamentoModel.
 * O Spring gerará a implementação em tempo de execução; a classe
 * OrcamentoRepJPA delega as operações a esta interface.
 */
public interface OrcamentoJpaSpringRepository
                extends JpaRepository<OrcamentoModel, Long> {

        /** Consulta usada no relatório de vendas (somente orçamentos efetivados). */
        List<OrcamentoModel> findByEfetivadoTrueAndDataCriacaoBetween(
                        LocalDate inicio, LocalDate fim);
}
