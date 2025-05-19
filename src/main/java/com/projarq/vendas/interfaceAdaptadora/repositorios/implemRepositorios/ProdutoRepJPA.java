package com.projarq.vendas.interfaceAdaptadora.repositorios.implemRepositorios;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.projarq.vendas.dominio.entidades.ProdutoModel;
import com.projarq.vendas.dominio.interfRepositorios.IProdutoRepositorio;
import com.projarq.vendas.interfaceAdaptadora.repositorios.entidades.Produto;
import com.projarq.vendas.interfaceAdaptadora.repositorios.interfaceJPA.ProdutoJPA_ItfRep;

@Repository
@Primary
public class ProdutoRepJPA implements IProdutoRepositorio {

    private static final Logger logger = LoggerFactory.getLogger(ProdutoRepJPA.class);
    private final ProdutoJPA_ItfRep produtoRepository;

    public ProdutoRepJPA(ProdutoJPA_ItfRep produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @Override
    public List<ProdutoModel> todos() {
        List<Produto> produtos = StreamSupport
                .stream(produtoRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());

        if (produtos.isEmpty()) {
            return new LinkedList<>();
        } else {
            return produtos.stream()
                    .map(Produto::toProdutoModel)
                    .toList();
        }
    }

    @Override
    public ProdutoModel consultaPorId(long id) {
        Produto produto = produtoRepository.findById(id);
        logger.info("Produto de código {}: {}", id, produto);
        return produto == null ? null : Produto.toProdutoModel(produto);
    }
}
