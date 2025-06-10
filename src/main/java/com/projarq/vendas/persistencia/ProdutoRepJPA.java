package com.projarq.vendas.persistencia;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.projarq.vendas.dominio.entidades.ProdutoModel;
import com.projarq.vendas.dominio.interfRepositorios.IProdutoJpaRepositorio;
import com.projarq.vendas.dominio.interfRepositorios.IProdutoRepositorio;

import jakarta.annotation.PostConstruct;

@Repository
public class ProdutoRepJPA implements IProdutoRepositorio {

    private final IProdutoJpaRepositorio produtoJpaRepositorio;

    public ProdutoRepJPA(IProdutoJpaRepositorio produtoJpaRepositorio) {
        this.produtoJpaRepositorio = produtoJpaRepositorio;
    }

    @PostConstruct
    public void init() {
        // Seed data para Produtos
        if (produtoJpaRepositorio.count() == 0) { // Garante que só adicione se o banco estiver vazio
            produtoJpaRepositorio.save(new ProdutoModel("Televisor", 2000.0)); // <--- Alterado: sem ID
            produtoJpaRepositorio.save(new ProdutoModel("Geladeira", 3500.0)); // <--- Alterado: sem ID
            produtoJpaRepositorio.save(new ProdutoModel("Fogao", 1200.0)); // <--- Alterado: sem ID
            produtoJpaRepositorio.save(new ProdutoModel("Lava-louça", 1800.0)); // <--- Alterado: sem ID
            produtoJpaRepositorio.save(new ProdutoModel("lava-roupas", 2870.0)); // <--- Alterado: sem ID
            produtoJpaRepositorio.save(new ProdutoModel("Farinha*", 10.0)); // <--- Alterado: sem ID
            produtoJpaRepositorio.save(new ProdutoModel("Arroz*", 12.5)); // <--- Alterado: sem ID
            System.out.println("Produtos de seed data adicionados.");
        }
    }

    @Override
    public List<ProdutoModel> todos() {
        return produtoJpaRepositorio.findAll();
    }

    @Override
    public ProdutoModel consultaPorId(long codigo) {
        return produtoJpaRepositorio.findById(codigo).orElse(null);
    }
}