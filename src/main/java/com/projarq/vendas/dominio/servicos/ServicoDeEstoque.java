package com.projarq.vendas.dominio.servicos;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.projarq.vendas.dominio.entidades.ItemDeEstoqueModel;
import com.projarq.vendas.dominio.entidades.ProdutoModel;
import com.projarq.vendas.dominio.interfRepositorios.IEstoqueRepositorio;
import com.projarq.vendas.dominio.interfRepositorios.IProdutoRepositorio;

@Service
public class ServicoDeEstoque{
    private final IProdutoRepositorio produtos;
    private final IEstoqueRepositorio estoque;
    
    //@Autowired
    public ServicoDeEstoque(IProdutoRepositorio produtos,IEstoqueRepositorio estoque){
        this.produtos = produtos;
        this.estoque = estoque;
    }
 
    public List<ProdutoModel> produtosDisponiveis(){
        return estoque.findByQuantidadeGreaterThan(0)
                .stream()
                .map(ItemDeEstoqueModel::getProduto)
                .toList();
    }

    public ProdutoModel produtoPorCodigo(long id){
        Optional<ProdutoModel> produto = produtos.findById(id);
        return produto.orElse(null);
    }

    public int qtdadeEmEstoque(long id){
        ItemDeEstoqueModel item = estoque.findByProdutoId(id);
        return item != null ? item.getQuantidade() : 0;
    }

    @Transactional
    public void baixaEstoque(long id,int qtdade){
        ItemDeEstoqueModel item = estoque.findByProdutoId(id);
        if (item == null) throw new IllegalArgumentException("Produto inexistente");
        if (item.getQuantidade() < qtdade) throw new IllegalArgumentException("Quantidade em estoque insuficiente");
        item.setQuantidade(item.getQuantidade() - qtdade);
        estoque.save(item);
    }  
}
