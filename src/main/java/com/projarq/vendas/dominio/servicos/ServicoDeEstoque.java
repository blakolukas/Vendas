package com.projarq.vendas.dominio.servicos;

import java.util.List;

import org.springframework.stereotype.Service;

import com.projarq.vendas.dominio.entidades.ProdutoModel;
import com.projarq.vendas.dominio.interfRepositorios.IEstoqueRepositorio;
import com.projarq.vendas.dominio.interfRepositorios.IProdutoRepositorio;

@Service
public class ServicoDeEstoque {
    private final IProdutoRepositorio produtos;
    private final IEstoqueRepositorio estoque;

    // @Autowired
    public ServicoDeEstoque(IProdutoRepositorio produtos, IEstoqueRepositorio estoque) {
        this.produtos = produtos;
        this.estoque = estoque;
    }

    public List<ProdutoModel> produtosDisponiveis() {
        return estoque.todosComEstoque();
    }

    public ProdutoModel produtoPorCodigo(long id) {
        return this.produtos.consultaPorId(id);
    }

    public int qtdadeEmEstoque(long id) {
        return estoque.quantidadeEmEstoque(id);
    }

    public void baixaEstoque(long id, int qtdade) {
        estoque.baixaEstoque(id, qtdade);
    }

    public void entradaEstoque(long id, int qtdade) { // <--- NOVO MÉTODO
        estoque.entradaEstoque(id, qtdade);
    }
}