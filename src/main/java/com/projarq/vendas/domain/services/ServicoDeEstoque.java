package com.projarq.vendas.domain.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.projarq.vendas.domain.model.Produto;
import com.projarq.vendas.domain.repository.IEstoqueRepositorio;
import com.projarq.vendas.domain.repository.IProdutoRepositorio;

@Service
public class ServicoDeEstoque{
    private final IProdutoRepositorio produtos;
    private final IEstoqueRepositorio estoque;
    
    //@Autowired
    public ServicoDeEstoque(IProdutoRepositorio produtos,IEstoqueRepositorio estoque){
        this.produtos = produtos;
        this.estoque = estoque;
    }
 
    public List<Produto> produtosDisponiveis(){
        return estoque.todosComEstoque();
    }

    public Produto produtoPorCodigo(long id){
        return this.produtos.consultaPorId(id);
    }

    public int qtdadeEmEstoque(long id){
        return estoque.quantidadeEmEstoque(id);
    }

    public void baixaEstoque(long id,int qtdade){
        estoque.baixaEstoque(id,qtdade);
    }  
}
