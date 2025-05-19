package com.projarq.vendas.interfaceAdaptadora.repositorios.implemRepositorios;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.projarq.vendas.dominio.entidades.ProdutoModel;
import com.projarq.vendas.dominio.interfRepositorios.IEstoqueRepositorio;
import com.projarq.vendas.interfaceAdaptadora.repositorios.entidades.ItemDeEstoque;
import com.projarq.vendas.interfaceAdaptadora.repositorios.entidades.Produto;
import com.projarq.vendas.interfaceAdaptadora.repositorios.interfaceJPA.EstoqueJPA_ItfRep;

@Repository
@Primary
public class EstoqueRepJPA implements IEstoqueRepositorio {

    private final EstoqueJPA_ItfRep estoque;

    public EstoqueRepJPA(EstoqueJPA_ItfRep estoque) {
        this.estoque = estoque;
    }

    @Override
    public List<ProdutoModel> todos() {
        List<ItemDeEstoque> itens = StreamSupport
                .stream(estoque.findAll().spliterator(), false)
                .collect(Collectors.toList());

        return itens.stream()
                .map(it -> Produto.toProdutoModel(it.getProduto()))
                .toList();
    }

    @Override
    public List<ProdutoModel> todosComEstoque() {
        List<ItemDeEstoque> itens = StreamSupport
                .stream(estoque.findAll().spliterator(), false)
                .collect(Collectors.toList());

        return itens.stream()
                .filter(it -> it.getQuantidade() > 0)
                .map(it -> Produto.toProdutoModel(it.getProduto()))
                .toList();
    }

    @Override
    public int quantidadeEmEstoque(long codigo) {
        ItemDeEstoque item = estoque.findById(codigo).orElse(null);
        if (item == null) {
            return -1;
        } else {
            return item.getQuantidade();
        }
    }

    @Override
    public int baixaEstoque(long codProd, int qtdade) {
        ItemDeEstoque item = estoque.findById(codProd).orElse(null);
        if (item == null) {
            throw new IllegalArgumentException("Produto inexistente");
        }
        if (item.getQuantidade() < qtdade) {
            throw new IllegalArgumentException("Quantidade em estoque insuficiente");
        }
        int novaQuantidade = item.getQuantidade() - qtdade;
        item.setQuantidade(novaQuantidade);
        estoque.save(item);
        return novaQuantidade;
    }
}
