package com.projarq.vendas.dominio.entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "itens_pedido")
public class ItemPedidoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // <--- Novo ID para o item do pedido
    private Long id; // Use Long para IDs de JPA

    @ManyToOne
    @JoinColumn(name = "produto_id", nullable = false)
    private ProdutoModel produto;
    private int quantidade;

    // Relacionamento com OrcamentoModel (adicionaremos no OrcamentoModel também)
    // @ManyToOne
    // @JoinColumn(name = "orcamento_id")
    // private OrcamentoModel orcamento; // Será preenchido quando adicionado a um
    // orçamento

    // Construtor padrão exigido pelo JPA
    public ItemPedidoModel() {
    }

    public ItemPedidoModel(ProdutoModel produto, int quantidade) {
        this.produto = produto;
        this.quantidade = quantidade;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) { // <--- Setter para ID
        this.id = id;
    }

    public ProdutoModel getProduto() {
        return produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setProduto(ProdutoModel produto) { // <--- Setter para Produto
        this.produto = produto;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    /*
     * public OrcamentoModel getOrcamento() {
     * return orcamento;
     * }
     * 
     * public void setOrcamento(OrcamentoModel orcamento) {
     * this.orcamento = orcamento;
     * }
     */

    @Override
    public String toString() {
        return "ItemPedido [id=" + id + ", produto=" + produto.getDescricao() + ", quantidade=" + quantidade + "]";
    }
}