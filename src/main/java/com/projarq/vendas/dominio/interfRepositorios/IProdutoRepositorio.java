package com.projarq.vendas.dominio.interfRepositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.projarq.vendas.dominio.entidades.ProdutoModel;

@Repository
public interface IProdutoRepositorio extends JpaRepository<ProdutoModel, Long> {
    
    @Query("SELECT p FROM ProdutoModel p")
    List<ProdutoModel> todos();
    
    @Query("SELECT p FROM ProdutoModel p WHERE p.id = :codigo")
    ProdutoModel consultaPorId(long codigo);
}
