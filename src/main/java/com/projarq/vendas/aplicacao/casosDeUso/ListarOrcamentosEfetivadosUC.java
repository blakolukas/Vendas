package com.projarq.vendas.aplicacao.casosDeUso;

import com.projarq.vendas.aplicacao.dtos.IntervaloDatasDTO;
import com.projarq.vendas.aplicacao.dtos.OrcamentoDTO;
import com.projarq.vendas.dominio.entidades.OrcamentoModel;
import com.projarq.vendas.dominio.interfRepositorios.IOrcamentoRepositorio;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class ListarOrcamentosEfetivadosUC {
    private final IOrcamentoRepositorio orcamentos;

    public ListarOrcamentosEfetivadosUC(IOrcamentoRepositorio orcamentos) {
        this.orcamentos = orcamentos;
    }

    public List<OrcamentoDTO> run(IntervaloDatasDTO datas) {
        LocalDate inicio = LocalDate.parse(datas.getDataInicial());
        LocalDate fim = LocalDate.parse(datas.getDataFinal());

        return orcamentos.todos().stream()
                .filter(OrcamentoModel::isEfetivado)
                .filter(o -> {
                    LocalDate data = o.getDataCriacao().toLocalDate();
                    return (!data.isBefore(inicio)) && (!data.isAfter(fim));
                })
                .map(OrcamentoDTO::fromModel)
                .toList();
    }
}
