package com.projarq.vendas.dominio.interfRepositorios;

import com.projarq.vendas.dominio.entidades.ItemDeEstoqueModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional; // Importação adicionada

@Repository
public interface IEstoqueJpaRepositorio extends JpaRepository<ItemDeEstoqueModel, Long> {
    // Método para encontrar um item de estoque pelo ID do produto
    Optional<ItemDeEstoqueModel> findByProduto_Id(Long produtoId);
}