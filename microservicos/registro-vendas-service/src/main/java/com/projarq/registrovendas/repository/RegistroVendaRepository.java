package com.projarq.registrovendas.repository;

import com.projarq.registrovendas.entity.RegistroVenda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegistroVendaRepository extends JpaRepository<RegistroVenda, Long> {
    
    @Query("SELECT r FROM RegistroVenda r WHERE YEAR(r.dataEfetivacao) = :ano AND MONTH(r.dataEfetivacao) = :mes")
    List<RegistroVenda> findByAnoAndMes(@Param("ano") int ano, @Param("mes") int mes);
    
    @Query("SELECT SUM(r.valorCobrado) FROM RegistroVenda r WHERE YEAR(r.dataEfetivacao) = :ano AND MONTH(r.dataEfetivacao) = :mes")
    Double sumValorCobradoByAnoAndMes(@Param("ano") int ano, @Param("mes") int mes);
    
    @Query("SELECT SUM(r.valorImpostos) FROM RegistroVenda r WHERE YEAR(r.dataEfetivacao) = :ano AND MONTH(r.dataEfetivacao) = :mes")
    Double sumValorImpostosByAnoAndMes(@Param("ano") int ano, @Param("mes") int mes);
}

