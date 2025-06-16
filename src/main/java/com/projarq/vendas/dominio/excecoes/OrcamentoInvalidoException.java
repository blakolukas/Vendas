package com.projarq.vendas.dominio.excecoes;

public class OrcamentoInvalidoException extends DominioException {
    
    public OrcamentoInvalidoException(String message) {
        super(message);
    }
    
    public OrcamentoInvalidoException(String message, Throwable cause) {
        super(message, cause);
    }
}

