package com.projarq.registrovendas.dto;

public class RelatorioMensalResponse {
    private int mes;
    private int ano;
    private Double totalVendido;
    private Double totalImpostos;
    private int quantidadeVendas;

    public RelatorioMensalResponse() {}

    public RelatorioMensalResponse(int mes, int ano, Double totalVendido, 
                                 Double totalImpostos, int quantidadeVendas) {
        this.mes = mes;
        this.ano = ano;
        this.totalVendido = totalVendido != null ? totalVendido : 0.0;
        this.totalImpostos = totalImpostos != null ? totalImpostos : 0.0;
        this.quantidadeVendas = quantidadeVendas;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public Double getTotalVendido() {
        return totalVendido;
    }

    public void setTotalVendido(Double totalVendido) {
        this.totalVendido = totalVendido;
    }

    public Double getTotalImpostos() {
        return totalImpostos;
    }

    public void setTotalImpostos(Double totalImpostos) {
        this.totalImpostos = totalImpostos;
    }

    public int getQuantidadeVendas() {
        return quantidadeVendas;
    }

    public void setQuantidadeVendas(int quantidadeVendas) {
        this.quantidadeVendas = quantidadeVendas;
    }
}

