package com.projarq.vendas.dominio.entidades.enums;

public enum Estado {
    // Região Norte
    AC("Acre", "AC", Pais.BRASIL),
    AP("Amapá", "AP", Pais.BRASIL),
    AM("Amazonas", "AM", Pais.BRASIL),
    PA("Pará", "PA", Pais.BRASIL),
    RO("Rondônia", "RO", Pais.BRASIL),
    RR("Roraima", "RR", Pais.BRASIL),
    TO("Tocantins", "TO", Pais.BRASIL),
    
    // Região Nordeste
    AL("Alagoas", "AL", Pais.BRASIL),
    BA("Bahia", "BA", Pais.BRASIL),
    CE("Ceará", "CE", Pais.BRASIL),
    MA("Maranhão", "MA", Pais.BRASIL),
    PB("Paraíba", "PB", Pais.BRASIL),
    PE("Pernambuco", "PE", Pais.BRASIL),
    PI("Piauí", "PI", Pais.BRASIL),
    RN("Rio Grande do Norte", "RN", Pais.BRASIL),
    SE("Sergipe", "SE", Pais.BRASIL),
    
    // Região Centro-Oeste
    GO("Goiás", "GO", Pais.BRASIL),
    MT("Mato Grosso", "MT", Pais.BRASIL),
    MS("Mato Grosso do Sul", "MS", Pais.BRASIL),
    DF("Distrito Federal", "DF", Pais.BRASIL),
    
    // Região Sudeste
    ES("Espírito Santo", "ES", Pais.BRASIL),
    MG("Minas Gerais", "MG", Pais.BRASIL),
    RJ("Rio de Janeiro", "RJ", Pais.BRASIL),
    SP("São Paulo", "SP", Pais.BRASIL),
    
    // Região Sul
    PR("Paraná", "PR", Pais.BRASIL),
    RS("Rio Grande do Sul", "RS", Pais.BRASIL),
    SC("Santa Catarina", "SC", Pais.BRASIL);

    private final String nome;
    private final String sigla;
    private final Pais pais;

    Estado(String nome, String sigla, Pais pais) {
        this.nome = nome;
        this.sigla = sigla;
        this.pais = pais;
    }

    public String getNome() {
        return nome;
    }

    public String getSigla() {
        return sigla;
    }

    public Pais getPais() {
        return pais;
    }

    public boolean temCobertura() {
        return pais.temCobertura();
    }

    public static Estado fromSigla(String sigla) {
        for (Estado estado : values()) {
            if (estado.getSigla().equalsIgnoreCase(sigla)) {
                return estado;
            }
        }
        throw new IllegalArgumentException("Estado não encontrado para a sigla: " + sigla);
    }
}

