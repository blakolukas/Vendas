package com.projarq.vendas.persistencia;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.projarq.vendas.dominio.entidades.ItemPedidoModel;
import com.projarq.vendas.dominio.entidades.OrcamentoModel;
import com.projarq.vendas.dominio.entidades.PedidoModel;
import com.projarq.vendas.dominio.entidades.ProdutoModel;
import com.projarq.vendas.dominio.interfRepositorios.IOrcamentoJpaRepositorio;
import com.projarq.vendas.dominio.interfRepositorios.IOrcamentoRepositorio;
import com.projarq.vendas.dominio.interfRepositorios.IProdutoJpaRepositorio;

import jakarta.annotation.PostConstruct;

@Repository
public class OrcamentoRepJPA implements IOrcamentoRepositorio {

    private final IOrcamentoJpaRepositorio orcamentoJpaRepositorio;
    private final IProdutoJpaRepositorio produtoJpaRepositorio;

    // @Autowired // <--- Anotação removida
    public OrcamentoRepJPA(IOrcamentoJpaRepositorio orcamentoJpaRepositorio,
            IProdutoJpaRepositorio produtoJpaRepositorio) {
        this.orcamentoJpaRepositorio = orcamentoJpaRepositorio;
        this.produtoJpaRepositorio = produtoJpaRepositorio;
    }

    @PostConstruct
    public void init() {
        // Seed data para Orçamentos (anteriormente em OrcamentoRepMem)
        if (orcamentoJpaRepositorio.count() == 0) {
            ProdutoModel p10 = produtoJpaRepositorio.findById(10L).orElse(null);
            ProdutoModel p20 = produtoJpaRepositorio.findById(20L).orElse(null);
            ProdutoModel p40 = produtoJpaRepositorio.findById(40L).orElse(null);
            ProdutoModel p50 = produtoJpaRepositorio.findById(50L).orElse(null);

            if (p10 != null && p20 != null && p40 != null && p50 != null) {
                // Cria Orcamento 1
                OrcamentoModel orcamento1 = new OrcamentoModel();
                PedidoModel pedido1 = new PedidoModel(1, "RS");
                pedido1.addItem(new ItemPedidoModel(p10, 2));
                pedido1.addItem(new ItemPedidoModel(p20, 3));
                orcamento1.addItensPedido(pedido1);
                orcamento1.setEstado(pedido1.getEstado());
                orcamento1.setDataCriacao(LocalDateTime.of(1999, 1, 1, 0, 0)); // Data antiga para teste de validade
                // Setar outros campos necessários (custoItens, imposto, desconto,
                // custoConsumidor)
                // Estes campos devem ser calculados pelo ServicoDeVendas antes de serem
                // persistidos
                // Para o seed, podemos setar valores dummy ou rodar o calculo manual:
                orcamento1.setCustoItens(p10.getPrecoUnitario() * 2 + p20.getPrecoUnitario() * 3);
                // imposto e desconto seriam calculados pelo serviço, não setados aqui
                // Para simplificar o seed, podemos colocar 0 ou um valor fixo.
                orcamento1.setImposto(0);
                orcamento1.setDesconto(0);
                orcamento1.setCustoConsumidor(orcamento1.getCustoItens()); // Apenas para seed
                orcamentoJpaRepositorio.save(orcamento1);

                // Cria Orcamento 2
                OrcamentoModel orcamento2 = new OrcamentoModel();
                PedidoModel pedido2 = new PedidoModel(2, "SP");
                pedido2.addItem(new ItemPedidoModel(p40, 1));
                pedido2.addItem(new ItemPedidoModel(p50, 2));
                pedido2.addItem(new ItemPedidoModel(p20, 1));
                orcamento2.addItensPedido(pedido2);
                orcamento2.setEstado(pedido2.getEstado());
                orcamento2.setDataCriacao(LocalDateTime.of(2001, 1, 1, 0, 0)); // Data antiga
                orcamento2.setCustoItens(
                        p40.getPrecoUnitario() * 1 + p50.getPrecoUnitario() * 2 + p20.getPrecoUnitario() * 1);
                orcamento2.setImposto(0);
                orcamento2.setDesconto(0);
                orcamento2.setCustoConsumidor(orcamento2.getCustoItens());
                orcamentoJpaRepositorio.save(orcamento2);
                System.out.println("Orçamentos de seed data adicionados.");
            } else {
                System.out.println("Produtos de seed data não encontrados para inicializar orçamentos.");
            }
        }
    }

    @Override
    public List<OrcamentoModel> todos() {
        return orcamentoJpaRepositorio.findAll();
    }

    @Override
    public OrcamentoModel cadastra(OrcamentoModel orcamento) {
        // Ao usar JPA, o ID será gerado pelo banco. Não precisamos de idCount
        return orcamentoJpaRepositorio.save(orcamento);
    }

    @Override
    public OrcamentoModel recuperaPorId(long id) {
        return orcamentoJpaRepositorio.findById(id).orElse(null);
    }

    @Override
    public void marcaComoEfetivado(long id) {
        Optional<OrcamentoModel> orcamentoOpt = orcamentoJpaRepositorio.findById(id);
        if (orcamentoOpt.isPresent()) {
            OrcamentoModel orcamento = orcamentoOpt.get();
            orcamento.efetiva();
            orcamentoJpaRepositorio.save(orcamento);
        } else {
            throw new IllegalArgumentException("Orcamento não encontrado");
        }
    }
}