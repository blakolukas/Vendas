package com.projarq.impostos.dto;

public class CalculoImpostoRequest {
    private double valorBase;
    private String estado;
    private int quantidadeItens;

    public CalculoImpostoRequest() {}

    public CalculoImpostoRequest(double valorBase, String estado, int quantidadeItens) {
        this.valorBase = valorBase;
        this.estado = estado;
        this.quantidadeItens = quantidadeItens;
    }

    public double getValorBase() {
        return valorBase;
    }

    public void setValorBase(double valorBase) {
        this.valorBase = valorBase;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getQuantidadeItens() {
        return quantidadeItens;
    }

    public void setQuantidadeItens(int quantidadeItens) {
        this.quantidadeItens = quantidadeItens;
    }
}

