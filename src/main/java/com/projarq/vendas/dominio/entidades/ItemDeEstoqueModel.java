package com.projarq.vendas.dominio.entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne; // <--- Importação adicionada
import jakarta.persistence.JoinColumn; // <--- Importação adicionada

@Entity
@Table(name = "itens_de_estoque")
public class ItemDeEstoqueModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne // <--- Relação muitos-para-um com Produto
    @JoinColumn(name = "produto_id", nullable = false) // <--- Coluna de junção
    private ProdutoModel produto;

    private int quantidade;
    private int estoqueMin;
    private int estoqueMax;

    // Construtor padrão exigido pelo JPA
    public ItemDeEstoqueModel() {
    }

    public ItemDeEstoqueModel(long id, ProdutoModel produto, int quantidade, int estoqueMin, int estoqueMax) {
        this.id = id;
        this.produto = produto;
        this.quantidade = quantidade;
        this.estoqueMin = estoqueMin;
        this.estoqueMax = estoqueMax;
    }

    public long getId() {
        return id;
    }

    public ProdutoModel getProduto() {
        return produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public int getEstoqueMin() {
        return estoqueMin;
    }

    public int getEstoqueMax() {
        return estoqueMax;
    }

    public void setId(long id) { // <--- Setter para ID adicionado
        this.id = id;
    }

    public void setProduto(ProdutoModel produto) { // <--- Setter para Produto adicionado
        this.produto = produto;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public void setEstoqueMin(int estoqueMin) {
        this.estoqueMin = estoqueMin;
    }

    public void setEstoqueMax(int estoqueMax) {
        this.estoqueMax = estoqueMax;
    }

    @Override
    public String toString() {
        return "ItemDeEstoque [id=" + id + ", produto=" + produto.getDescricao() + ", quantidade=" + quantidade
                + ", estoqueMin="
                + estoqueMin + ", estoqueMax=" + estoqueMax + "]";
    }
}