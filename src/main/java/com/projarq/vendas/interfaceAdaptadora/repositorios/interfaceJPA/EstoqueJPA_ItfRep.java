package com.projarq.vendas.interfaceAdaptadora.repositorios.interfaceJPA;

import org.springframework.data.repository.CrudRepository;

import com.projarq.vendas.interfaceAdaptadora.repositorios.entidades.ItemDeEstoque;

public interface EstoqueJPA_ItfRep extends CrudRepository<ItemDeEstoque, Long> {
    // Não precisa sobrescrever findAll nem findById — herdados corretamente do CrudRepository
}
