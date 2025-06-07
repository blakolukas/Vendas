package com.projarq.vendas.dominio.entidades;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

/**
 * Entidade de domínio que representa um orçamento composto por itens de pedido.
 */
@Entity
@Table(name = "orcamentos")
public class OrcamentoModel {

    /* ---------- Campos ---------- */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Data em que o orçamento foi criado (usada para validade de 21 dias). */
    @Column(nullable = false)
    private LocalDate dataCriacao = LocalDate.now();

    /** Itens que compõem o orçamento. Cascade.ALL simplifica persistência. */
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemPedidoModel> itens = new LinkedList<>();

    private double custoItens;
    private double imposto;
    private double desconto;
    private double custoConsumidor;

    @Column(nullable = false)
    private boolean efetivado = false;

    /* ---------- Construtores ---------- */

    public OrcamentoModel() {
        // JPA precisa do construtor vazio
    }

    public OrcamentoModel(long id) {
        this.id = id;
    }

    /* ---------- Métodos de negócio ---------- */

    public void addItensPedido(PedidoModel pedido) {
        itens.addAll(pedido.getItens());
    }

    /** Marca o orçamento como efetivado. */
    public void efetiva() {
        this.efetivado = true;
    }

    /* ---------- Getters / Setters ---------- */

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public List<ItemPedidoModel> getItens() {
        return itens;
    }

    public void setItens(List<ItemPedidoModel> itens) {
        this.itens = itens;
    }

    public double getCustoItens() {
        return custoItens;
    }

    public void setCustoItens(double custoItens) {
        this.custoItens = custoItens;
    }

    public double getImposto() {
        return imposto;
    }

    public void setImposto(double imposto) {
        this.imposto = imposto;
    }

    public double getDesconto() {
        return desconto;
    }

    public void setDesconto(double desconto) {
        this.desconto = desconto;
    }

    public double getCustoConsumidor() {
        return custoConsumidor;
    }

    public void setCustoConsumidor(double custoConsumidor) {
        this.custoConsumidor = custoConsumidor;
    }

    public boolean isEfetivado() {
        return efetivado;
    }

    public void setEfetivado(boolean efetivado) {
        this.efetivado = efetivado;
    }
}
