package com.projarq.vendas.interfaceAdaptadora.controladores;

import java.util.List;

import com.projarq.vendas.aplicacao.casosDeUso.*;
import com.projarq.vendas.aplicacao.dtos.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class Controller {
    private ListarOrcamentosEfetivadosUC listarOrcamentosEfetivados;
    private ProdutosDisponiveisUC produtosDisponiveis;
    private CriaOrcamentoUC criaOrcamento;
    private EfetivaOrcamentoUC efetivaOrcamento;
    private DispListaUC dispListaUC;

    //@Autowired
    public Controller(ProdutosDisponiveisUC produtosDisponiveis,
                      CriaOrcamentoUC criaOrcamento,
                      EfetivaOrcamentoUC efetivaOrcamento,
                      DispListaUC dispListaUC,
                      ListarOrcamentosEfetivadosUC listarOrcamentosEfetivados){

        this.produtosDisponiveis = produtosDisponiveis;
        this.criaOrcamento = criaOrcamento;
        this.efetivaOrcamento = efetivaOrcamento;
        this.dispListaUC = dispListaUC;
        this.listarOrcamentosEfetivados = listarOrcamentosEfetivados;
    }

    @GetMapping("")
    @CrossOrigin(origins = "*")
    public String welcomeMessage(){
        return("Bem vindo as lojas Projarq!");
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

    //chegada de produtos
    @GetMapping("produtos/chegada")
    @CrossOrigin(origins = "*")
    public void chegadaProdutos(){
        //retornar produtos novos 
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