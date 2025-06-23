package com.projarq.vendas.dominio.interfRepositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.projarq.vendas.dominio.entidades.OrcamentoModel;

@Repository
public interface IOrcamentoRepositorio extends JpaRepository<OrcamentoModel, Long> {
    
    @Query("SELECT o FROM OrcamentoModel o")
    List<OrcamentoModel> todos();
    
    @Modifying
    @Transactional
    @Query("UPDATE OrcamentoModel o SET o.efetivado = true WHERE o.id = :id")
    void marcaComoEfetivado(@Param("id") long id);
}
