package com.projarq.vendas.dominio.entidades;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "pedido_id")
    private PedidoModel pedido;
    
    @ManyToOne
    @JoinColumn(name = "produto_id")
    private ProdutoModel produto;
    
    @ManyToOne
    @JoinColumn(name = "orcamento_id")
    private OrcamentoModel orcamento;
    
    @Column(name = "quantidade")
    private int quantidade;
    
    public ItemPedidoModel() {}
    
    public ItemPedidoModel(ProdutoModel produto, int quantidade) {
        this.produto = produto;
        this.quantidade = quantidade;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProdutoModel getProduto() {
        return produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public PedidoModel getPedido() {
        return pedido;
    }

    public void setPedido(PedidoModel pedido) {
        this.pedido = pedido;
    }

    public OrcamentoModel getOrcamento() {
        return orcamento;
    }

    public void setOrcamento(OrcamentoModel orcamento) {
        this.orcamento = orcamento;
    }

    @Override
    public String toString() {
        return "ItemPedido [id=" + id + ", produto=" + produto + ", quantidade=" + quantidade + "]";
    }
}
