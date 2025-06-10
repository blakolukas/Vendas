package com.projarq.vendas.dominio.interfRepositorios;

import com.projarq.vendas.dominio.entidades.OrcamentoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOrcamentoJpaRepositorio extends JpaRepository<OrcamentoModel, Long> {
}