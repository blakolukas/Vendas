package com.projarq.vendas.dominio.interfRepositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projarq.vendas.dominio.entidades.ProdutoModel;

@Repository
public interface IRepositorioProdutos extends JpaRepository<ProdutoModel, Long> {
} 