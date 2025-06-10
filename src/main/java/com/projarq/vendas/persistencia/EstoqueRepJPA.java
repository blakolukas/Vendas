package com.projarq.vendas.persistencia;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.projarq.vendas.dominio.entidades.ItemDeEstoqueModel;
import com.projarq.vendas.dominio.entidades.ProdutoModel;
import com.projarq.vendas.dominio.interfRepositorios.IEstoqueJpaRepositorio;
import com.projarq.vendas.dominio.interfRepositorios.IEstoqueRepositorio;
import com.projarq.vendas.dominio.interfRepositorios.IProdutoJpaRepositorio;

import jakarta.annotation.PostConstruct;

@Repository
public class EstoqueRepJPA implements IEstoqueRepositorio {

    private final IEstoqueJpaRepositorio estoqueJpaRepositorio;
    private final IProdutoJpaRepositorio produtoJpaRepositorio;

    // @Autowired // <--- Anotação removida
    public EstoqueRepJPA(IEstoqueJpaRepositorio estoqueJpaRepositorio, IProdutoJpaRepositorio produtoJpaRepositorio) {
        this.estoqueJpaRepositorio = estoqueJpaRepositorio;
        this.produtoJpaRepositorio = produtoJpaRepositorio;
    }

    @PostConstruct
    public void init() {
        // Seed data para Estoque (anteriormente em EstoqueRepMem)
        if (estoqueJpaRepositorio.count() == 0) {
            ProdutoModel p10 = produtoJpaRepositorio.findById(10L).orElse(null);
            ProdutoModel p20 = produtoJpaRepositorio.findById(20L).orElse(null);
            ProdutoModel p40 = produtoJpaRepositorio.findById(40L).orElse(null);
            ProdutoModel p50 = produtoJpaRepositorio.findById(50L).orElse(null);
            ProdutoModel p60 = produtoJpaRepositorio.findById(60L).orElse(null);
            ProdutoModel p70 = produtoJpaRepositorio.findById(70L).orElse(null);

            if (p10 != null) estoqueJpaRepositorio.save(new ItemDeEstoqueModel(0L, p10, 20, 5, 50));
            if (p20 != null) estoqueJpaRepositorio.save(new ItemDeEstoqueModel(0L, p20, 15, 5, 50));
            if (p40 != null) estoqueJpaRepositorio.save(new ItemDeEstoqueModel(0L, p40, 25, 10, 100));
            if (p50 != null) estoqueJpaRepositorio.save(new ItemDeEstoqueModel(0L, p50, 23, 5, 40));
            if (p60 != null) estoqueJpaRepositorio.save(new ItemDeEstoqueModel(0L, p60, 30, 10, 50));
            if (p70 != null) estoqueJpaRepositorio.save(new ItemDeEstoqueModel(0L, p70, 20, 8, 40));
            System.out.println("Itens de estoque de seed data adicionados.");
        }
    }

    @Override
    public List<ProdutoModel> todos() {
        return estoqueJpaRepositorio.findAll().stream()
                .map(ItemDeEstoqueModel::getProduto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProdutoModel> todosComEstoque() {
        return estoqueJpaRepositorio.findAll().stream()
                .filter(it -> it.getQuantidade() > 0)
                .map(ItemDeEstoqueModel::getProduto)
                .collect(Collectors.toList());
    }

    @Override
    public int quantidadeEmEstoque(long id) {
        return estoqueJpaRepositorio.findByProduto_Id(id)
                .map(ItemDeEstoqueModel::getQuantidade)
                .orElse(-1);
    }

    @Override
    public void baixaEstoque(long codProd, int qtdade) {
        Optional<ItemDeEstoqueModel> itemOpt = estoqueJpaRepositorio.findByProduto_Id(codProd);
        if (itemOpt.isEmpty()) {
            throw new IllegalArgumentException("Produto inexistente no estoque.");
        }
        ItemDeEstoqueModel item = itemOpt.get();

        if (item.getQuantidade() < qtdade) {
            throw new IllegalArgumentException("Quantidade em estoque insuficiente para o produto " + item.getProduto().getDescricao());
        }

        int novaQuantidade = item.getQuantidade() - qtdade;
        item.setQuantidade(novaQuantidade);
        estoqueJpaRepositorio.save(item);
    }

    @Override
    public void entradaEstoque(long codProd, int qtdade) {
        Optional<ItemDeEstoqueModel> itemOpt = estoqueJpaRepositorio.findByProduto_Id(codProd);
        if (itemOpt.isEmpty()) {
            throw new IllegalArgumentException("Produto inexistente no estoque. Adicione-o ao catálogo e ao estoque primeiro.");
        }
        ItemDeEstoqueModel item = itemOpt.get();

        int novaQuantidade = item.getQuantidade() + qtdade;
        item.setQuantidade(novaQuantidade);
        estoqueJpaRepositorio.save(item);
    }
}