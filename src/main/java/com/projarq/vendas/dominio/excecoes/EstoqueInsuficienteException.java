package com.projarq.vendas.dominio.excecoes;

public class EstoqueInsuficienteException extends DominioException {
    
    public EstoqueInsuficienteException(String message) {
        super(message);
    }
    
    public EstoqueInsuficienteException(String message, Throwable cause) {
        super(message, cause);
    }
}

