package com.projarq.vendas.dominio.interfRepositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projarq.vendas.dominio.entidades.ItemDeEstoqueModel;

@Repository
public interface IEstoqueRepositorio extends JpaRepository<ItemDeEstoqueModel, Long> {
    List<ItemDeEstoqueModel> findByQuantidadeGreaterThan(int quantidade);
    ItemDeEstoqueModel findByProdutoId(Long produtoId);
}
