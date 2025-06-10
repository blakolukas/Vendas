package com.projarq.vendas.persistencia;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.projarq.vendas.dominio.entidades.ItemDeEstoqueModel;
import com.projarq.vendas.dominio.entidades.ProdutoModel;
import com.projarq.vendas.dominio.interfRepositorios.IEstoqueRepositorio;
import com.projarq.vendas.dominio.interfRepositorios.IProdutoRepositorio;

import jakarta.annotation.PostConstruct;

@Repository
public class EstoqueRepMem implements IEstoqueRepositorio {
    private List<ItemDeEstoqueModel> itens;
    private IProdutoRepositorio produtos;

    @Autowired
    public EstoqueRepMem(IProdutoRepositorio produtos) {
        System.out.println("EstoqueRepMem construído (sem itens ainda)");
        this.produtos = produtos;
        this.itens = new LinkedList<>();
    }

    @PostConstruct
    public void init() {
        System.out.println("Inicializando estoque com produtos...");

        ProdutoModel p = produtos.consultaPorId(10);
        System.out.println("Produto 10: " + p);
        itens.add(new ItemDeEstoqueModel(100, p, 20, 5, 50));

        p = produtos.consultaPorId(20);
        itens.add(new ItemDeEstoqueModel(200, p, 15, 5, 50));

        p = produtos.consultaPorId(40);
        itens.add(new ItemDeEstoqueModel(400, p, 25, 10, 100));

        p = produtos.consultaPorId(50);
        itens.add(new ItemDeEstoqueModel(500, p, 23, 5, 40));
    }

    @Override
    public List<ProdutoModel> todos() {
        return produtos.todos();
    }

    @Override
    public List<ProdutoModel> todosComEstoque() {
        return itens.stream()
                .filter(it -> it.getQuantidade() > 0)
                .map(ItemDeEstoqueModel::getProduto)
                .toList();
    }

    @Override
    public int quantidadeEmEstoque(long id) {
        return itens.stream()
                .filter(it -> it.getProduto().getId() == id)
                .map(ItemDeEstoqueModel::getQuantidade)
                .findAny()
                .orElse(-1);
    }

    @Override
    public void baixaEstoque(long id, int qtdade) {
        ItemDeEstoqueModel item = itens.stream()
                .filter(it -> it.getProduto().getId() == id)
                .findAny()
                .orElse(null);

        if (item == null) {
            throw new IllegalArgumentException("Produto inexistente");
        }

        if (item.getQuantidade() < qtdade) {
            throw new IllegalArgumentException("Quantidade em estoque insuficiente");
        }

        int novaQuantidade = item.getQuantidade() - qtdade;
        item.setQuantidade(novaQuantidade);
    }

    @Override
    public void entradaEstoque(long id, int qtdade) { // <--- NOVO MÉTODO
        ItemDeEstoqueModel item = itens.stream()
                .filter(it -> it.getProduto().getId() == id)
                .findAny()
                .orElse(null);

        if (item == null) {
            throw new IllegalArgumentException("Produto inexistente no estoque. Adicione-o ao catálogo primeiro.");
        }

        int novaQuantidade = item.getQuantidade() + qtdade;
        item.setQuantidade(novaQuantidade);
    }
}