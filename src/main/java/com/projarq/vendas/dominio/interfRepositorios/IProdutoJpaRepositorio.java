package com.projarq.vendas.dominio.interfRepositorios;

import com.projarq.vendas.dominio.entidades.ProdutoModel;
import org.springframework.data.jpa.repository.JpaRepository; // <--- Importação adicionada
import org.springframework.stereotype.Repository; // <--- Importação adicionada

@Repository // <--- Anotação adicionada
public interface IProdutoJpaRepositorio extends JpaRepository<ProdutoModel, Long> {
    // JpaRepository já fornece métodos como save, findById, findAll, delete, etc.
    // Você pode adicionar métodos personalizados aqui se precisar de queries
    // específicas
}