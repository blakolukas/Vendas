package com.projarq.vendas.dominio.servicos;

import java.util.List;

import org.springframework.stereotype.Service;

import com.projarq.vendas.dominio.entidades.ItemPedidoModel;
import com.projarq.vendas.dominio.entidades.OrcamentoModel;
import com.projarq.vendas.dominio.entidades.PedidoModel;
import com.projarq.vendas.dominio.entidades.ProdutoModel;
import com.projarq.vendas.dominio.entidades.enums.Estado;
import com.projarq.vendas.dominio.excecoes.CoberturaGeograficaException;
import com.projarq.vendas.dominio.excecoes.EstoqueInsuficienteException;
import com.projarq.vendas.dominio.excecoes.OrcamentoInvalidoException;
import com.projarq.vendas.dominio.interfRepositorios.IEstoqueRepositorio;
import com.projarq.vendas.dominio.interfRepositorios.IOrcamentoRepositorio;
import com.projarq.vendas.dominio.servicos.descontos.DescontoComposite;
import com.projarq.vendas.dominio.servicos.descontos.DescontoMaisDezItensStrategy;
import com.projarq.vendas.dominio.servicos.descontos.DescontoMaisTresItensStrategy;
import com.projarq.vendas.dominio.servicos.impostos.ImpostoEstadualStrategy;
import com.projarq.vendas.dominio.servicos.impostos.ImpostoFederalFixoStrategy;

@Service
public class ServicoDeVendas {
    private final IOrcamentoRepositorio orcamentos;
    private final IEstoqueRepositorio estoque;
    private final ImpostoEstadualStrategy impostoEstadual;
    private final ImpostoFederalFixoStrategy impostoFederal;
    private final DescontoComposite descontoComposite;

    public ServicoDeVendas(IOrcamentoRepositorio orcamentos, IEstoqueRepositorio estoque) {
        this.orcamentos = orcamentos;
        this.estoque = estoque;
        this.impostoEstadual = new ImpostoEstadualStrategy();
        this.impostoFederal = new ImpostoFederalFixoStrategy();
        
        // Configurar estratégias de desconto
        this.descontoComposite = new DescontoComposite();
        this.descontoComposite.adicionarEstrategia(new DescontoMaisTresItensStrategy());
        this.descontoComposite.adicionarEstrategia(new DescontoMaisDezItensStrategy());
    }
     
    public List<ProdutoModel> produtosDisponiveis() {
        return estoque.todosComEstoque();
    }

    public OrcamentoModel recuperaOrcamentoPorId(long id) {
        return this.orcamentos.recuperaPorId(id);
    }

    public OrcamentoModel criaOrcamento(PedidoModel pedido) {
        // Verificar cobertura geográfica
        verificarCoberturaGeografica(pedido.getEstado());
        
        var novoOrcamento = new OrcamentoModel();
        novoOrcamento.addItensPedido(pedido);
        novoOrcamento.setEstado(pedido.getEstado());
        
        double custoItens = novoOrcamento.getItens().stream()
            .mapToDouble(it -> it.getProduto().getPrecoUnitario() * it.getQuantidade())
            .sum();
        novoOrcamento.setCustoItens(custoItens);
        
        // Calcular impostos usando as novas estratégias
        double impostoEstadualValor = impostoEstadual.calcularImposto(custoItens, novoOrcamento);
        double impostoFederalValor = impostoFederal.calcularImposto(custoItens, novoOrcamento);
        double impostoTotal = impostoEstadualValor + impostoFederalValor;
        novoOrcamento.setImposto(impostoTotal);

        // Calcular desconto usando composite
        double desconto = descontoComposite.calcularDesconto(novoOrcamento);
        novoOrcamento.setDesconto(desconto);
        
        novoOrcamento.setCustoConsumidor(custoItens + impostoTotal - desconto);
        
        return this.orcamentos.cadastra(novoOrcamento);
    }
 
    public OrcamentoModel efetivaOrcamento(long id) {
        var orcamento = this.orcamentos.recuperaPorId(id);
        
        // Verificar se o orçamento é válido (menos de 21 dias)
        if (!orcamento.isValido()) {
            throw new OrcamentoInvalidoException("Orçamento com mais de 21 dias não pode ser efetivado.");
        }
        
        // Verificar se tem quantidade em estoque para todos os itens
        for (ItemPedidoModel itemPedido : orcamento.getItens()) {
            int qtdade = estoque.quantidadeEmEstoque(itemPedido.getProduto().getId());
            if (qtdade < itemPedido.getQuantidade()) {
                throw new EstoqueInsuficienteException(
                    String.format("Estoque insuficiente para o produto %s. Disponível: %d, Solicitado: %d",
                        itemPedido.getProduto().getNome(), qtdade, itemPedido.getQuantidade())
                );
            }
        }

        // Se chegou até aqui, pode efetivar
        for (ItemPedidoModel itemPedido : orcamento.getItens()) {
            estoque.baixaEstoque(itemPedido.getProduto().getId(), itemPedido.getQuantidade());
        }
        
        orcamentos.marcaComoEfetivado(id);
        return orcamentos.recuperaPorId(id);
    }
    
    private void verificarCoberturaGeografica(String siglaEstado) {
        try {
            Estado estado = Estado.fromSigla(siglaEstado);
            if (!estado.temCobertura()) {
                throw new CoberturaGeograficaException(
                    String.format("Não temos cobertura para o estado %s (%s)", 
                        estado.getNome(), estado.getSigla())
                );
            }
        } catch (IllegalArgumentException e) {
            throw new CoberturaGeograficaException(
                String.format("Estado não reconhecido: %s", siglaEstado)
            );
        }
    }
}

