package com.projarq.vendas.dominio.entidades.enums;

public enum Pais {
    BRASIL("BR", "Brasil"),
    ARGENTINA("AR", "Argentina"),
    URUGUAI("UY", "Uruguai"),
    PARAGUAI("PY", "Paraguai");

    private final String codigo;
    private final String nome;

    Pais(String codigo, String nome) {
        this.codigo = codigo;
        this.nome = nome;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public boolean temCobertura() {
        return this == BRASIL; // Por enquanto só temos cobertura no Brasil
    }
}

