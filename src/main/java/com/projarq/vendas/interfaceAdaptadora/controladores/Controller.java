package com.projarq.vendas.interfaceAdaptadora.controladores;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.projarq.vendas.aplicacao.casosDeUso.CriaOrcamentoUC;
import com.projarq.vendas.aplicacao.casosDeUso.DispListaUC;
import com.projarq.vendas.aplicacao.casosDeUso.EfetivaOrcamentoUC;
import com.projarq.vendas.aplicacao.casosDeUso.ListarOrcamentosEfetivadosUC;
import com.projarq.vendas.aplicacao.casosDeUso.ProdDispQnt;
import com.projarq.vendas.aplicacao.casosDeUso.ProdutosDisponiveisUC;
import com.projarq.vendas.aplicacao.dtos.IntervaloDatasDTO;
import com.projarq.vendas.aplicacao.dtos.NovoOrcamentoRequest;
import com.projarq.vendas.aplicacao.dtos.OrcamentoDTO;
import com.projarq.vendas.aplicacao.dtos.ProdutoDTO;


@RestController
public class Controller {
    private ListarOrcamentosEfetivadosUC listarOrcamentosEfetivados;
    private ProdutosDisponiveisUC produtosDisponiveis;
    private CriaOrcamentoUC criaOrcamento;
    private EfetivaOrcamentoUC efetivaOrcamento;
    private DispListaUC dispListaUC;
    private ProdDispQnt prodDispQnt;

    //@Autowired
    public Controller(ProdutosDisponiveisUC produtosDisponiveis,
                      CriaOrcamentoUC criaOrcamento,
                      EfetivaOrcamentoUC efetivaOrcamento,
                      DispListaUC dispListaUC,
                      ListarOrcamentosEfetivadosUC listarOrcamentosEfetivados,
                      ProdDispQnt prodDispQnt){

        this.produtosDisponiveis = produtosDisponiveis;
        this.criaOrcamento = criaOrcamento;
        this.efetivaOrcamento = efetivaOrcamento;
        this.dispListaUC = dispListaUC;
        this.listarOrcamentosEfetivados = listarOrcamentosEfetivados;
        this.prodDispQnt = prodDispQnt;
    }

    @GetMapping("")
    @CrossOrigin(origins = "*")
    public String welcomeMessage(){
        return("Bem vindo as lojas Projarq!");
    }

    @GetMapping("produtos")
    @CrossOrigin(origins = "*")
    public List<String> produtos(){
        return dispListaUC.run();
    }

    @GetMapping("estoque")
    @CrossOrigin(origins = "*")
    public List<ProdutoDTO> produtosDisponiveis(){
        return produtosDisponiveis.run();
    }

    @PostMapping("novoOrcamento")
    @CrossOrigin(origins = "*")
    public OrcamentoDTO novoOrcamento(@RequestBody NovoOrcamentoRequest request) {
        return criaOrcamento.run(request.getItens(), request.getEstado());
    }

    @GetMapping("efetivaOrcamento/{id}")
    @CrossOrigin(origins = "*")
    public OrcamentoDTO efetivaOrcamento(@PathVariable(value="id") long idOrcamento){
        return efetivaOrcamento.run(idOrcamento);
    }

    //retornar a quantidade disponível para uma lista de itens em estoque
    @GetMapping("estoqueLista")
    @CrossOrigin(origins = "*")
    public List<ProdutoDTO> produtosEstoque(@RequestParam List<Long> ids){
        return dispListaUC.run(ids);
    }

    //Retornar a lista de orçamentos efetivados em um determinado período (informar data inicial e data final)
    @PostMapping("orcamentos/periodoPeriodo")
    @CrossOrigin(origins = "*")
    public List<OrcamentoDTO> orcamentosPeriodo(@RequestBody IntervaloDatasDTO datas) {
        return listarOrcamentosEfetivados.run(datas);
    }

}