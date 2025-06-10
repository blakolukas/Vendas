
## Vendas - T2projarc

é a refatoração do T1, quebrando o sistema de vendas em microserviços


#### Retorna os produtos cadastrados

```
  GET http://localhost:8080/produtos
```

#### Retorna o estoque de produtos

```
  GET http://localhost:8080/estoque
```

#### Cria um novo orçamento

```
  POST http://localhost:8080/novoOrcamento
```

#### Efetiva um orçamento existente

```
  GET http://localhost:8080/efetivaOrcamento{id}
```

#### Retorna a quandidade disponível para uma lista

```
  GET http://localhost:8080/estoqueLista
```

#### Retorna os orcamentos efetivados em um determinado período

```
  POST http://localhost:8080/orcamentos/periodoPeriodo
```


