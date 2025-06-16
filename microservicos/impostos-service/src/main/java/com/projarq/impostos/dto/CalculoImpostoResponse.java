package com.projarq.impostos.dto;

public class CalculoImpostoResponse {
    private double impostoEstadual;
    private double impostoFederal;
    private double impostoTotal;
    private String descricaoEstadual;
    private String descricaoFederal;
    private String instancia;

    public CalculoImpostoResponse() {}

    public CalculoImpostoResponse(double impostoEstadual, double impostoFederal, 
                                 String descricaoEstadual, String descricaoFederal, String instancia) {
        this.impostoEstadual = impostoEstadual;
        this.impostoFederal = impostoFederal;
        this.impostoTotal = impostoEstadual + impostoFederal;
        this.descricaoEstadual = descricaoEstadual;
        this.descricaoFederal = descricaoFederal;
        this.instancia = instancia;
    }

    public double getImpostoEstadual() {
        return impostoEstadual;
    }

    public void setImpostoEstadual(double impostoEstadual) {
        this.impostoEstadual = impostoEstadual;
    }

    public double getImpostoFederal() {
        return impostoFederal;
    }

    public void setImpostoFederal(double impostoFederal) {
        this.impostoFederal = impostoFederal;
    }

    public double getImpostoTotal() {
        return impostoTotal;
    }

    public void setImpostoTotal(double impostoTotal) {
        this.impostoTotal = impostoTotal;
    }

    public String getDescricaoEstadual() {
        return descricaoEstadual;
    }

    public void setDescricaoEstadual(String descricaoEstadual) {
        this.descricaoEstadual = descricaoEstadual;
    }

    public String getDescricaoFederal() {
        return descricaoFederal;
    }

    public void setDescricaoFederal(String descricaoFederal) {
        this.descricaoFederal = descricaoFederal;
    }

    public String getInstancia() {
        return instancia;
    }

    public void setInstancia(String instancia) {
        this.instancia = instancia;
    }
}

