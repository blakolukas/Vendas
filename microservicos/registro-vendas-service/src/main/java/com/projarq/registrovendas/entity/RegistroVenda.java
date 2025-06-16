package com.projarq.registrovendas.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "registro_vendas")
public class RegistroVenda {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "orcamento_id", nullable = false)
    private Long orcamentoId;
    
    @Column(name = "data_efetivacao", nullable = false)
    private LocalDateTime dataEfetivacao;
    
    @Column(name = "valor_cobrado", nullable = false)
    private Double valorCobrado;
    
    @Column(name = "valor_impostos", nullable = false)
    private Double valorImpostos;
    
    @Column(name = "estado")
    private String estado;

    public RegistroVenda() {}

    public RegistroVenda(Long orcamentoId, LocalDateTime dataEfetivacao, 
                        Double valorCobrado, Double valorImpostos, String estado) {
        this.orcamentoId = orcamentoId;
        this.dataEfetivacao = dataEfetivacao;
        this.valorCobrado = valorCobrado;
        this.valorImpostos = valorImpostos;
        this.estado = estado;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrcamentoId() {
        return orcamentoId;
    }

    public void setOrcamentoId(Long orcamentoId) {
        this.orcamentoId = orcamentoId;
    }

    public LocalDateTime getDataEfetivacao() {
        return dataEfetivacao;
    }

    public void setDataEfetivacao(LocalDateTime dataEfetivacao) {
        this.dataEfetivacao = dataEfetivacao;
    }

    public Double getValorCobrado() {
        return valorCobrado;
    }

    public void setValorCobrado(Double valorCobrado) {
        this.valorCobrado = valorCobrado;
    }

    public Double getValorImpostos() {
        return valorImpostos;
    }

    public void setValorImpostos(Double valorImpostos) {
        this.valorImpostos = valorImpostos;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}

