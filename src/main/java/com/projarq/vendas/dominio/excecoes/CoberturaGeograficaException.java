package com.projarq.vendas.dominio.excecoes;

public class CoberturaGeograficaException extends DominioException {
    
    public CoberturaGeograficaException(String message) {
        super(message);
    }
    
    public CoberturaGeograficaException(String message, Throwable cause) {
        super(message, cause);
    }
}

