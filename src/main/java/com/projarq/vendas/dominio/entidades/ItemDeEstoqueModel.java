package com.projarq.vendas.dominio.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "itens_estoque")
public class ItemDeEstoqueModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "produto_id")
    private ProdutoModel produto;

    @Column(name = "quantidade")
    private int quantidade;
    
    @Column(name = "estoque_min")
    private int estoqueMin;
    
    @Column(name = "estoque_max")
    private int estoqueMax;

    public ItemDeEstoqueModel() {}

    public ItemDeEstoqueModel(Long id, ProdutoModel produto, int quantidade, int estoqueMin, int estoqueMax) {
        this.id = id;
        this.produto = produto;
        this.quantidade = quantidade;
        this.estoqueMin = estoqueMin;
        this.estoqueMax = estoqueMax;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public ProdutoModel getProduto() { return produto; }
    public void setProduto(ProdutoModel produto) { this.produto = produto; }
    public int getQuantidade() { return quantidade; }
    public void setQuantidade(int quantidade) { this.quantidade = quantidade; }
    public int getEstoqueMin() { return estoqueMin; }
    public void setEstoqueMin(int estoqueMin) { this.estoqueMin = estoqueMin; }
    public int getEstoqueMax() { return estoqueMax; }
    public void setEstoqueMax(int estoqueMax) { this.estoqueMax = estoqueMax; }

    @Override
    public String toString() {
        return "ItemDeEstoque [id=" + id + ", produto=" + produto + ", quantidade=" + quantidade + ", estoqueMin="
                + estoqueMin + ", estoqueMax=" + estoqueMax + "]";
    }
}