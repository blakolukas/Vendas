package com.projarq.vendas.dominio.portas;

import java.util.List;

import com.projarq.vendas.dominio.entidades.OrcamentoModel;

/**
 * Porta de saída (outbound) do domínio para acesso/persistência de orçamentos.
 * Camadas externas (Infra) fornecerão a implementação concreta — por exemplo,
 * via JPA, JDBC ou outro mecanismo — enquanto as regras de negócio dependem
 * apenas desta interface.
 */
public interface IOrcamentoRepositorio {

    /** Retorna todos os orçamentos (efetivados ou não). */
    List<OrcamentoModel> todos();

    /** Persiste um novo orçamento ou atualiza um existente. */
    OrcamentoModel cadastra(OrcamentoModel orcamento);

    /** Recupera um orçamento pelo ID ou {@code null} se não existir. */
    OrcamentoModel recuperaPorId(long id);

    /** Marca o orçamento como efetivado. */
    void marcaComoEfetivado(long id);
}
