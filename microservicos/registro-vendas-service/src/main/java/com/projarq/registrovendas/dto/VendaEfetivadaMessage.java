package com.projarq.registrovendas.dto;

import java.time.LocalDateTime;

public class VendaEfetivadaMessage {
    private Long orcamentoId;
    private LocalDateTime dataEfetivacao;
    private Double valorCobrado;
    private Double valorImpostos;
    private String estado;

    public VendaEfetivadaMessage() {}

    public VendaEfetivadaMessage(Long orcamentoId, LocalDateTime dataEfetivacao, 
                               Double valorCobrado, Double valorImpostos, String estado) {
        this.orcamentoId = orcamentoId;
        this.dataEfetivacao = dataEfetivacao;
        this.valorCobrado = valorCobrado;
        this.valorImpostos = valorImpostos;
        this.estado = estado;
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

